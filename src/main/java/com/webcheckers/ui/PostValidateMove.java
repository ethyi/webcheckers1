package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

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

        final String data = request.queryParams("actionData");
        Move move = new Move(data);

        //Piece piece = BoardView.getSpace(move.getStart()).getPiece();
        //piece.normalMove(move.getEnd());

        boolean Validity = true;// pass move into validity object

        if (!Validity){ //invalid cases with appropriate message
            Message m = Message.error("INVALID MOVE");
            return gson.toJson(m);
        }
        CheckersGame game = gameCenter.getGame(player.getGameID());
        game.getBoard().MovePiece(move);

        return gson.toJson(Message.info("VALID MOVE"));


    }

}
