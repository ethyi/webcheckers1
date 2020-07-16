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

        Message m = Message.info("TEST SUBMIT");

        return gson.toJson(m);
    }

}
