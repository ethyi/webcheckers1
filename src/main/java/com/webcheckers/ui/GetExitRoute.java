package com.webcheckers.ui;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetExitRoute implements Route {
    private final Gson gson;
    public GetExitRoute(final Gson gson){
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response){
        //TODO
        response.redirect(WebServer.HOME_URL);

        return "PostBackupMove";
    }

}
