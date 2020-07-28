package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI controller to get the spectate route
 * @author Aubrey Tarmu
 * @author Tony Jiang
 * @author Ethan Yi
 */

public class GetSpectate implements Route {
    private static final Logger LOG = Logger.getLogger(com.webcheckers.ui.PostSigninRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;
    private final GameCenter gameCenter;
    private BoardView boardView;
    public GetSpectate(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = gameCenter.getLobby();
        System.out.println("Game center id: "+gameCenter.getGameId());
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
        Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);


        session.attribute(GetHomeRoute.IN_GAME, null);

        System.out.println();
        System.out.println(player.getName());
        System.out.println(player.getGameID());
        final CheckersGame checkers = gameCenter.getGame(player.getGameID());


        if(checkers.isGameOver()){
            response.redirect(WebServer.HOME_URL);
        }
        if(!checkers.getRedPlayer().isChallenged()){
            response.redirect(WebServer.HOME_URL);
        }

        boardView =  new BoardView(checkers.getBoard(), Piece.Color.RED);

        Map<String, Object> vm = new HashMap<>();
        vm.put("title","Checkers");
        vm.put("gameID", gameCenter.getGameId());
        vm.put("currentUser",player);
        vm.put("board", boardView);
        vm.put("viewMode", GetGameRoute.mode.SPECTATOR);
        vm.put("redPlayer",checkers.getRedPlayer());
        vm.put("whitePlayer",checkers.getWhitePlayer());
        vm.put("activeColor",checkers.getActiveColor());

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}
