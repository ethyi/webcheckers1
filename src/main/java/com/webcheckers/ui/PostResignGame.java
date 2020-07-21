package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostResignGame implements Route {

    private final Gson gson;
    public PostResignGame(final Gson gson){
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        player.setChallenged(false,null);
        player.setResign(true);
        Message m = Message.info("resign successful");

        return gson.toJson(m);
    }

}
