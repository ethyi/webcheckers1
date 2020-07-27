package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.*;

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
            CheckersGame game = gameCenter.getGame(player.getGameID());
            Validator Validity = game.getValidator();
            Move lastMove = game.getBoard().getLastMove();
            Position end = lastMove.getEnd();

            Space endSpace = game.getBoard().getSpace(lastMove.getEnd());

            Piece piece = endSpace.getPiece();

            if (end.getRow() == 0 && piece.getColor() == Piece.Color.RED ||
                    end.getRow() == 7 && piece.getColor() == Piece.Color.WHITE) {
                piece.promote();
            }

            if(lastMove.isJumpMove()) {
                game.getBoard().removePiece(lastMove.getJumped());
            }

            if (!lastMove.isMultiJump()) {
                game.switchActiveColor();
            }
        }
        else{// more conditions of invalid turns
            m = Message.error("INVALID TURN");
        }

        return gson.toJson(m);
    }

}
