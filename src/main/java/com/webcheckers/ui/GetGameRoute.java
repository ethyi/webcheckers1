package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
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
    private final GameCenter gameCenter;
    private BoardView boardView;

    public GetGameRoute(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = gameCenter.getLobby();
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

        if (player!=null&&player.isChallenged()==false){//make new checkers
            String otherString = request.queryParams("challenger");
            final Player otherPlayer = lobby.getPlayer(otherString);
            session.attribute(GetHomeRoute.IN_GAME, null);

            if (otherPlayer.isChallenged()){
                session.attribute(GetHomeRoute.IN_GAME,"true");
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            }

            CheckersGame temp = new CheckersGame("1",player, otherPlayer);
            gameCenter.addGame(temp);

            //TODO make gameView synchronous, figure out iterator
            player.setID("1");
            otherPlayer.setID("1");
            player.setChallenged(true, otherPlayer);
            otherPlayer.setChallenged(true,player);

        }

        final CheckersGame checkers = gameCenter.getGame(player.getGameID());
        if (checkers.getRedPlayer().equals(player)){
            boardView = new BoardView(checkers.getBoard(), Piece.Color.RED);
        }
        else{
            boardView = new BoardView(checkers.getBoard(), Piece.Color.WHITE);
        }

        Map<String, Object> vm = new HashMap<>();
        vm.put("title","Checkers");
        vm.put("gameID", 1);
        vm.put("currentUser",player);
        vm.put("board", boardView);
        vm.put("viewMode",mode.PLAY);
        vm.put("redPlayer",checkers.getRedPlayer());
        vm.put("whitePlayer",checkers.getWhitePlayer());
        vm.put("activeColor",checkers.getActiveColor());

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}