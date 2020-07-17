package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Checkers;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import javax.management.InstanceNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static spark.Spark.halt;

public class PostSubmitTurn implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;
    private Message m;
    private boolean turnValidity;

    public PostSubmitTurn(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");

        turnValidity = true;
        if (turnValidity){
            m = Message.info("VALID TURN");
            Checkers game = gameCenter.getGame(player.getGameID());
            game.switchActiveColor();
        }
        else{// more conditions of invalid turns
            m = Message.error("INVALID TURN");
        }

        return gson.toJson(m);
    }

}
