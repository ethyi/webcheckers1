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

public class PostSpectateCheckTurn implements Route {
    private final Gson gson;
    private final GameCenter gameCenter;
    public PostSpectateCheckTurn(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        System.out.println(WebServer.loremImpsum);
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        CheckersGame game = gameCenter.getGame(player.getGameID());
        Message m=Message.info("true");

        return gson.toJson(m);

    }

}
