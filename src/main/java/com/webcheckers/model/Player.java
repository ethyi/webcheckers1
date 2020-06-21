package com.webcheckers.model;

/**
 * Player entity that holds player name
 * @Ethan Yi ehy5032@rit.edu
 */
public class Player {

    // Attributes

    private String playerName;

    /**
     * Create Player with username
     * @param user username
     */
    public Player(String user){
        this.playerName = user;
    }

    /**
     * Return username
     */
    public String getPlayerUser() {
        return playerName;
    }

}
