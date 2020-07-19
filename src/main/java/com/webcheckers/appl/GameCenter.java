package com.webcheckers.appl;

import com.webcheckers.model.CheckersGame;
import com.webcheckers.model.Validator;

import java.util.HashMap;

/**
 * Application tier GameCenter that holds a database of all games being played
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class GameCenter {

    private HashMap<String, CheckersGame> games;
    private PlayerLobby lobby;
    private Validator validator;

    public GameCenter(){
        this.games = new HashMap<>();
        this.lobby = new PlayerLobby();
        this.validator = new Validator();
    }
    public PlayerLobby getLobby(){
        return this.lobby;
    }
    public CheckersGame getGame(String id){
        return games.get(id);
    }

    public Validator getValidator() {
        return this.validator;
    }

    public synchronized   HashMap<String, CheckersGame> getGames() {
        return this.games;
    }

    public synchronized boolean contains(String id) {
        return games.containsKey(id);
    }

    /**
     * Puts Checkers game into hashmap into hashmap with id as key.
     * @param game checkers game to be added
     */
    public synchronized void addGame(CheckersGame game){
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
