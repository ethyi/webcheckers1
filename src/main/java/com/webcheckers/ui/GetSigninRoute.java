package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;


/**
 * The UI Controller to GET the Sign in page.
 *
 * @author Ethan Yi ehy5032@rit.edu
 * @author Tony Jiang tj2561@rit.edu
 * @author Aubrey Tarmu
 */
public class GetSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());
    private final TemplateEngine templateEngine;

    public GetSigninRoute(final TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSigninRoute is initialized.");
    }

    /**
     * Render the WebCheckers Sign in page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Sign in page
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        LOG.finer("GetSigninRoute is invoked.");
        Map<String, Object> vm = new HashMap<>();
        Player player = httpSession.attribute(GetHomeRoute.CURRENT_PLAYER);

        vm.put("title", "Sign In");

        if(player == null) {
            return templateEngine.render(new ModelAndView(vm, "signin.ftl"));
        } else {
            response.redirect(WebServer.HOME_URL);
            halt();
            return null;
        }

        // render the View
    }
}
