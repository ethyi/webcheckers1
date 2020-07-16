package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.util.Message;
import spark.*;
//import sun.text.normalizer.NormalizerBase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * The UI Controller to POST the sign in route
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 */

public class PostSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(com.webcheckers.ui.PostSigninRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final PlayerLobby lobby;

    public PostSigninRoute(TemplateEngine templateEngine, GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gameCenter = gameCenter;
        this.lobby = gameCenter.getLobby();
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

        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign In");
        final Session session = request.session();
        Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);

        if (player == null) {
            String username = request.queryParams("username");
            if (username.contains("\"")) {
                Message quote_error = Message.error("Double quote is not a valid character in a Player's name.");
                vm.put("message", quote_error);
                return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
            } else if (lobby.contains(username)) {
                Message quote_error = Message.error("Username is in use.");
                vm.put("message", quote_error);
                return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
            } else {
                lobby.addPlayer(username);
                session.attribute(GetHomeRoute.CURRENT_PLAYER, lobby.getPlayer(username));
            }
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;

        } else {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }
    }
}