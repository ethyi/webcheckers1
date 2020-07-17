package com.webcheckers.appl;

import com.webcheckers.model.Player;

import java.util.HashMap;
/**
 * Application tier Player Lobby that holds a database of all players and their attributes.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class PlayerLobby{
    // list of all players signed in
    private HashMap<String, Player> players;
    // list of players waiting for a game

    public PlayerLobby(){
        this.players = new HashMap<>();
    }

    public Player getPlayer(String username){
        return players.get(username);
    }

    public synchronized   HashMap<String, Player> getPlayers() {
        return players;
    }

    public synchronized int getNumPlayers(){
        return this.players.size();
    }

    public synchronized boolean contains(String name) {
        return players.containsKey(name);
    }
    public synchronized void removePlayer(String name ){
        players.remove(name);
    }

    /**
     * Creates new Player object with username and puts into hashmap with username as key.
     * @param username player name to be added
     */
    public synchronized void addPlayer(String username){
        Player newPlayer = new Player(username);
        this.players.put(username, newPlayer);
    }
}