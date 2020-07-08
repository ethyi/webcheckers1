package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import jdk.nashorn.internal.runtime.JSONFunctions;
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

        final String data = request.queryParams("actionData");
        Move move = new Move(data);
        if (true){
            response.type("INFO");
        }
        else{
            response.type("ERROR");
            response.body("TODO INVALID MOVE REASON");
        }
        return gson.toJson(data);


    }

}
