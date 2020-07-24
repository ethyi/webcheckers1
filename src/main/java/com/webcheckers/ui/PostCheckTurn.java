package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostCheckTurn implements Route {
    private final Gson gson;
    private final GameCenter gameCenter;
    public PostCheckTurn(final Gson gson, final GameCenter gameCenter){
            this.gson = gson;
            this.gameCenter = gameCenter;
        }

    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        CheckersGame game = gameCenter.getGame(player.getGameID());
        Message m;
        if(player.equals(game.getRedPlayer())){
            if(game.getActiveColor().equals(Piece.Color.RED)){
                m = Message.info("true");
            }
            else{
                m = Message.info("false");
            }
        }
        else{
            if(game.getActiveColor().equals(Piece.Color.WHITE)){
                m = Message.info("true");
            }
            else{
                m = Message.info("false");
            }
        }
        try {
            if (player.getChallenger().isResign()){
                m = Message.info("true");
            }
        } catch (java.lang.NullPointerException e){
            m = Message.info("true");
        }


        return gson.toJson(m);

    }

}
