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

    private enum mode {
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
        String otherString = request.queryParams("challenger");
        final Session session = request.session();
        final Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);
        final Player otherPlayer = lobby.getPlayer(otherString);
        session.attribute(GetHomeRoute.IN_GAME, null);

        if (otherPlayer.inGame()){
            session.attribute(GetHomeRoute.IN_GAME,"true");
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

        otherPlayer.setChallenged(true, player);
        player.setGame(true);
        otherPlayer.setGame(true);
        player.setP1();
        GameView board =new GameView(player, otherPlayer, Piece.Color.WHITE);
        player.setColor(Piece.Color.RED);
        Map<String, Object> vm = new HashMap<>();
        vm.put("board", board);

        vm.put("currentUser",player);
        vm.put("title", "Checkers");
        vm.put("redPlayer",player);
        vm.put("whitePlayer",otherPlayer);
        vm.put("activeColor","RED");
        vm.put("viewMode",mode.PLAY);
        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}