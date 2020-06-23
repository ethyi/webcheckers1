package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.model.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

public class PostSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;


    public PostSignOutRoute(final TemplateEngine templateEngine, PlayerLobby lobby){

        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

        this.lobby = lobby;

        LOG.config("PostSignOutRoute is initialized.");

    }

    @Override
    public Object handle(Request request, Response response){

        final Session session = request.session();

        LOG.finer("GetHomeRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();

        vm.put("title", "Sign Out");
        Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);
        session.attribute(GetHomeRoute.CURRENT_PLAYER,null);
        lobby.removePlayer(player.getName());

        vm.put("currentUser",null);

        response.redirect(WebServer.HOME_URL);
        halt();
        return null;

    }


}
