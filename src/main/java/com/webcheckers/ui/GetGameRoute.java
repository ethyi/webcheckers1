package com.webcheckers.ui;

import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;

import static spark.Spark.halt;

/**
 *The UI controller to post the game route.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */

public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(com.webcheckers.ui.PostSigninRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;

    public GetGameRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
        LOG.config("GetGameRoute is initialized.");
    }

    public enum mode {
        PLAY,
        SPECTATOR,
        REPLAY
    }
    /**
     * Render the WebCheckers Game page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the game page
     */

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        final Session session = request.session();
        final Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);
        GameView gameView = new GameView(Piece.Color.RED);

        if (player!=null&&player.getCheckers()==null){//make new checkers
            String otherString = request.queryParams("challenger");
            final Player otherPlayer = lobby.getPlayer(otherString);
            session.attribute(GetHomeRoute.IN_GAME, null);
            if (otherPlayer.isChallenged()){
                session.attribute(GetHomeRoute.IN_GAME,"true");
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            }
            Checkers temp = new Checkers("1",player, otherPlayer, Piece.Color.RED);
            System.out.println("sdfs sinsdfgsdfg");
            gameView = new GameView(Piece.Color.WHITE);

            player.updateCheckers(temp);
            otherPlayer.updateCheckers(temp);
            player.setChallenged(true, otherPlayer);
            otherPlayer.setChallenged(true,player);

        }

        final Checkers checkers = player.getCheckers();
        Map<String, Object> vm = new HashMap<>();
        vm.put("title","Checkers");
        vm.put("currentUser",player);
        vm.put("board", gameView);
        vm.put("viewMode",mode.PLAY);
        vm.put("redPlayer",checkers.getRedPlayer());
        vm.put("whitePlayer",checkers.getWhitePlayer());
        vm.put("activeColor",checkers.getActiveColor());

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}