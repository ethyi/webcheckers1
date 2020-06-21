package com.webcheckers.ui;

import spark.*;

import java.util.Objects;
import java.util.logging.Logger;

public class GetSigninRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
    private final TemplateEngine templateEngine;

    public GetSigninRoute(final TemplateEngine templateEngine){
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
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
        LOG.finer("GetSigninRoute is invoked.");


        // render the View
        return templateEngine.render(new ModelAndView(null , "home.ftl"));
    }
}
