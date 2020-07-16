package com.webcheckers.appl;

import com.webcheckers.model.Checkers;

import java.util.HashMap;

/**
 * Application tier GameCenter that holds a database of all games being played
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class GameCenter {

    private HashMap<String, Checkers> games;

    public GameCenter(){
        this.games = new HashMap<>();
    }

    public Checkers getGame(String id){
        return games.get(id);
    }

    public synchronized   HashMap<String, Checkers> getGames() {
        return this.games;
    }

    public synchronized boolean contains(String id) {
        return games.containsKey(id);
    }

    /**
     * Puts Checkers game into hashmap into hashmap with id as key.
     * @param game checkers game to be added
     */
    public synchronized void addGame(Checkers game){
        this.games.put(game.toString(), game);
    }

    /**
     * Removes checkers game from hashmap
     * @param id id of game to remove
     */
    public synchronized void removeGame(String id ){
        games.remove(id);
    }

}
