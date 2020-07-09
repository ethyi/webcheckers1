package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.model.Checkers;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import javax.management.InstanceNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static spark.Spark.halt;

public class PostSubmitTurn implements Route {

    private final Gson gson;

    public PostSubmitTurn(final Gson gson){
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response){
        //TODO

        System.out.println("PORNOGRAPHY");
        System.out.println("here lies the body : " +request.body());
        System.out.println("here lies the request : " +request.contentType());

        System.out.println(response.type());
        System.out.println(response.body());
        Message m = Message.info("SHREK IS LOVE, SHREK IS LIFE");

        return gson.toJson(m);
    }

}
