package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.List;

public class PostValidateMove implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;

    public PostValidateMove(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        String id = session.attribute("gameID");

        final String data = request.queryParams("actionData");
        final String color = request.queryParams("activeColor");

        System.out.println(data);
        Validator v = gameCenter.getGame(player.getGameID()).getValidator();


        Move move = new Move(data);


        Enum Validity = v.validateMove(move);// pass move into validity object

        if (Validity == Validator.moveType.INVALID){ //invalid cases with appropriate message
            Message m = Message.error("Invalid Move");
            return gson.toJson(m);
        }


        if (Validity == Validator.moveType.NEED_TO_JUMP){ //invalid cases with appropriate message
            Message m = Message.error("Jump available");
            return gson.toJson(m);
        }


        if (Validity == Validator.moveType.WRONG_DIRECTION) { //invalid cases with appropriate message
            Message m = Message.error("Wrong direction");
            return gson.toJson(m);
        }

        CheckersGame game = gameCenter.getGame(player.getGameID());
        game.getBoard().MovePiece(move);
        if (Validity == Validator.moveType.MULTI_JUMP){
            move.setMultiJump(true);
            Message m = Message.info("Multiple jump");
            return gson.toJson(m);
        }
        return gson.toJson(Message.info("Valid Move"));


    }

}
