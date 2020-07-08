package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.management.InstanceNotFoundException;

import static spark.Spark.halt;

public class PostSubmitTurn implements Route {

    private final Gson gson;
    public PostSubmitTurn(final Gson gson){
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response){
        //TODO

        if (response.type().equals("INFO")){
            //get game
        }
        else{
            //TODO explain why move cannot be valid
            return Message.info("Invalid Move");
        }
        return null;
    }

}
