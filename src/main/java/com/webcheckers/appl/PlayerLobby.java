package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Map;

public class PlayerLobby {
    private final HashSet userList = new HashSet<String>();
    public PlayerLobby(){
    }

    public boolean validSignin(String name){
        if (name.contains("\"")){
            return false;
        }
        return !userList.contains(name);
    }
}
