package com.webcheckers.ui;

import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;

import static spark.Spark.halt;


public class PostGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(com.webcheckers.ui.PostSigninRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;

    public PostGameRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
        LOG.config("PostGameRoute is initialized.");
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
        LOG.finer("PostGameRoute is invoked.");
        String otherString = request.queryParams("challenger");
        final Session session = request.session();
        final Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);
        final Player otherPlayer = lobby.getPlayer(otherString);


        Map<String, Object> vm = new HashMap<>();

        if (!otherPlayer.inGame()) {
            otherPlayer.setChallenged(true, player);
            player.setGame(true);
            otherPlayer.setGame(true);

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

        } else {
            Message quote_error = Message.error("That person is already in a game. Choose someone else.");
            vm.put("message", quote_error);
            return templateEngine.render(new ModelAndView(vm, "home.ftl"));
        }

    }
}