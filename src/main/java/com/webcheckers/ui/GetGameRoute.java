package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.model.GameView;
import com.webcheckers.model.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;


public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());

    private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
    private static final Message OTHER_PLAYERS_MSG = Message.info("Click on a player to start a game");
    private final PlayerLobby lobby;

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetGameRoute(final TemplateEngine templateEngine, PlayerLobby lobby) {
        this.lobby = lobby;
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");

        final Session httpSession = request.session();



    final       Player player = httpSession.attribute(GetHomeRoute.CURRENT_PLAYER);
        final Player otherPlayer = lobby.getPlayer("dsa");
//     final    Player otherPlayer = httpSession.attribute("dsa");
        //final    Player otherPlayer = lobby.getPlayer("dsa");
        otherPlayer.setChallenged(true, player);
        Map<String, Object> vm = new HashMap<>();
        GameView board =new GameView(player, otherPlayer);
        vm.put("board", board);

        vm.put("currentUser",player);
        vm.put("title", "Checkers");
        vm.put("gameID","1");
        vm.put("redPlayer",player);
        vm.put("whitePlayer",otherPlayer);
        vm.put("activeColor","red");
        vm.put("viewMode","player");



        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
