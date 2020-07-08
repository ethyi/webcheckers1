package com.webcheckers.ui;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostValidateMove implements Route {

    private final Gson gson;
    public PostValidateMove(final Gson gson){
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response){
        //TODO

        return "PostValidateMove";
    }

}
