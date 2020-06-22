package com.webcheckers.appl;

import com.webcheckers.model.Player;
import spark.Request;
import spark.Session;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PlayerLobby {
    private List playerList;
    private int numPlayers;


    public PlayerLobby(){
    }

    public void storeCurrentPlayer(String name, Session session) {
        session.attribute("currentUser", new Player(name));
        List<String> names = session.attribute("names");
        if (names == null) {
            names = new ArrayList<>();
        }
        names.add(name);
        session.attribute("names", names);

        this.playerList = session.attribute("names");

        if (session.attribute("numPlayers") != null) {
            int numPlayers = session.attribute("numPlayers");
            session.attribute("numPlayers", numPlayers+1);
        } else {
            session.attribute("numPlayers", 1);
        }
        this.numPlayers = session.attribute("numPlayers");
    }

    public void removeCurrentPlayer(Session session){
        Player player = session.attribute("currentUser");
        session.removeAttribute("currentUser");
        List<String> names = session.attribute("names");
        names.remove(player.name());
        session.attribute("names", names);

        int numPlayers = session.attribute("numPlayers");
        numPlayers-=1;
        session.attribute("numPlayers", numPlayers);

        this.playerList = session.attribute("names");
        this.numPlayers = session.attribute("numPlayers");
    }

    public boolean contains(String name) {
        if (playerList == null) {
            return false;
        } else if (playerList.contains(name)) {
            return true;
        }
        return false;
    }


    public int getNumPlayers(){
        return this.numPlayers;
    }


}
