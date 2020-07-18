package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostBackupMove implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;
    private Message m;
    public PostBackupMove(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        CheckersGame game = gameCenter.getGame(player.getGameID());
        Move reverseMove = game.getBoard().getLastMove();
        reverseMove.reverseMove();
        game.getBoard().MovePiece(reverseMove);

        Boolean backupSuccess = true;
        if (backupSuccess){
            m = Message.info("Backup successful");
        }
        else{
            m = Message.error("Backup unsuccessful");
        }

        return gson.toJson(m);
    }

}
