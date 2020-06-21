package com.webcheckers.model;

/**
 * Player entity that holds player name
 */
public class Player {

    // Attributes

    private String playerUser;
    private String playerPass;

    /**
     * Create Player with username and password
     * @param user username
     * @param pass password
     */
    public Player(String user, String pass){
        this.playerUser = user;
        this.playerPass = pass;
    }

    /**
     * Return username
     */
    public String getPlayerUser() {
        return playerUser;
    }

    /**
     * Return password
     */
    public String getPlayerPass() {
        return playerPass;
    }
}
