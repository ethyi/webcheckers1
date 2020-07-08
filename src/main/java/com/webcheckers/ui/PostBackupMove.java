package com.webcheckers.ui;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;
public class PostBackupMove implements Route {

    private final Gson gson;
    public PostBackupMove(final Gson gson){
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response){
        //TODO


        return "PostBackupMove";
    }

}
