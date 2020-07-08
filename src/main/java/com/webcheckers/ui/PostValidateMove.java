package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
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
        if (move.isValid()){
            return Message.info("Valid Move");
        }
        else{
            //TODO Should say why it is invalid, utilize Move class to do this
            return Message.error("Invalid Move");
        }

    }

}
