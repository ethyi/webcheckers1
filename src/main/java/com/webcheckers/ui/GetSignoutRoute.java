package com.webcheckers.ui;

import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


/**
 * The UI Controller to GET the Sign out page.
 *
 * @author Ethan Yi ehy5032@rit.edu
 */
public class GetSignoutRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetSigninRoute.class.getName());
    private final TemplateEngine templateEngine;

    public GetSignoutRoute(final TemplateEngine templateEngine){
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetSignoutRoute is initialized.");
    }

    /**
     * Render the WebCheckers Sign out page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Sign out page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetSignoutRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();


        vm.put("title", "Welcome!");
        // display a user message in the Home page
        vm.put("message", Message.info("Welcome to the world of online Checkers."));
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}
