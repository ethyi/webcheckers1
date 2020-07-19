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
        final String color = session.attribute("activeColor");
        System.out.println(color);
        System.out.println(data);
        Validator v = gameCenter.getValidator();
        //System.out.println("id is " + id);
        //System.out.println(gameCenter.getGame("1").toString());
        List<Row> b = gameCenter.getGame("1").getBoard().getBoard();
        //System.out.println("this is null" + b.toString());

        Move move = new Move(data);

        //Piece piece = BoardView.getSpace(move.getStart()).getPiece();
        //piece.normalMove(move.getEnd());

        boolean Validity = v.validateMove(b, move);// pass move into validity object

        if (!Validity){ //invalid cases with appropriate message
            Message m = Message.error("INVALID MOVE");
            return gson.toJson(m);
        }
        CheckersGame game = gameCenter.getGame(player.getGameID());
        game.getBoard().MovePiece(move);

        return gson.toJson(Message.info("VALID MOVE"));


    }

}
