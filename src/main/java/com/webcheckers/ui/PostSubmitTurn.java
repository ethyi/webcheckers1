package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.halt;

public class PostSubmitTurn implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;
    private Message m;

    public PostSubmitTurn(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        CheckersGame game = gameCenter.getGame(player.getGameID());

        Move lastMove = game.getBoard().getLastMove();


        if (lastMove.isMultiJump() || game.getBoard().getFlag()){
            m = Message.error("You cannot stop in the middle of a multiple jump");
        }
        else{
            m = Message.info("Valid Turn");

            Position end = lastMove.getEnd();
            Space endSpace = game.getBoard().getSpace(lastMove.getEnd());
            Piece piece = endSpace.getPiece();

            if (end.getRow() == 0 && piece.getColor() == Piece.Color.RED ||
                    end.getRow() == 7 && piece.getColor() == Piece.Color.WHITE) {
                piece.promote();
            }

            if(lastMove.isJumpMove()) {
                List<PiecePair> captured = game.getBoard().getCaptured();
                for(int i = 0; i < captured.size(); i++) {
                    PiecePair pair = captured.get(i);
                    Position position = pair.getPosition();
                    game.getBoard().removePiece(position);
                }
                captured.clear();
            }
            game.switchActiveColor();
            game.getBoard().reset();
        }



        return gson.toJson(m);
    }

}
