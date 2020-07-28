package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * The UI Controller that lets you resign a game
 *
 */

public class PostResignGame implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;
    public PostResignGame(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        Message m;
        if (player.getChallenger().isResign()){
            m = Message.error("Other Player has already resigned, submit turn to continue");
        }
        else{
            player.setChallenged(false,null);
            player.setID(null);
            player.setResign(true);
            m = Message.info("Resign successful");
        }

        return gson.toJson(m);
    }

}
