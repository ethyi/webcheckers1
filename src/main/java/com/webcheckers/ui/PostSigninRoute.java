package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;
//import sun.text.normalizer.NormalizerBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

public class PostSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(com.webcheckers.ui.PostSigninRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;

    public PostSigninRoute(TemplateEngine templateEngine, PlayerLobby lobby) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.lobby = lobby;
        LOG.config("PostSigninRoute is initialized.");
    }

    /**
     * Render the WebCheckers Sign in page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Sign in page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("PostSigninRoute is invoked.");
        final String username = request.queryParams("username");

        //if (playerLobby.validSignin(username)){
        //    return templateEngine.render(new ModelAndView(null, "game.ftl"));
        //}

        Map<String, Object> vm = new HashMap<>();
        if (username.contains("\"")){
            Message quote_error = Message.error("Double quote is not a valid character in a Player's name.");
            vm.put("message", quote_error);
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        }
        Session session = request.session();
        lobby.storeCurrentPlayer(username, session);
        vm.put("title", "Welcome "+username+"!");
        vm.put("message", Message.info("Welcome to the world of online Checkers."));
        vm.put("names", session.attribute("names"));
        vm.put("currentUser", username);

        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }
}