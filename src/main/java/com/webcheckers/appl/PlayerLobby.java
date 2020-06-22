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
    private Player currentUser;

    public PlayerLobby(){
    }

    public void storeCurrentPlayer(String name, Session session){
        session.attribute("currentUser", new Player(name));
        List<String> names = session.attribute("names");
        if (names ==null){
            names = new ArrayList<>();
            session.attribute("names",names);
        }
        names.add(name);
        if (session.attribute("numPlayers")!=null){
            int numPlayers = session.attribute("numPlayers");
            numPlayers+=1;
        }
        else{
            session.attribute("numPlayers",0);
        }

    }



}
