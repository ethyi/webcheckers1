package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
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

            Move lastMove = game.getBoard().getLastMove();
            if(lastMove.isJumpMove()) {
                game.getBoard().removePiece(lastMove.getJumped());
            }
            game.switchActiveColor();
        }
        else{// more conditions of invalid turns
            m = Message.error("INVALID TURN");
        }

        return gson.toJson(m);
    }

}
