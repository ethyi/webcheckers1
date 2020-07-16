package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI controller to post the signout.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */

public class PostSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());

    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final PlayerLobby lobby;


    public PostSignOutRoute(final TemplateEngine templateEngine, GameCenter gameCenter){

        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = gameCenter.getLobby();

        LOG.config("PostSignOutRoute is initialized.");

    }


    /**
     * Render the WebCheckers Sign Out page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Sign out page
     */
    @Override
    public Object handle(Request request, Response response){

        final Session session = request.session();
        LOG.finer("GetSignOutRoute is invoked.");
        Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);
        session.attribute(GetHomeRoute.CURRENT_PLAYER,null);
        lobby.removePlayer(player.getName());
        response.redirect(WebServer.HOME_URL);
        halt();
        return null;

    }


}
