package com.webcheckers.model;

/**
 * Player entity that holds each player's data
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 */
public class Player {
    private boolean isChallenged;
    private String name;
    private Player challenger;
    private String gameID;

    /**
     * Create Player with username
     * @param name username
     */
    public Player(String name){
        this.name = name;
        this.isChallenged = false;
    }

    /**
     * Set player challenge state and challenger
     */
    public void setChallenged(boolean challenged, Player challenger) {
        this.isChallenged = challenged;
        this.challenger = challenger;
    }

    /**
     * Return if challenged
     */
    public boolean isChallenged() {
        return this.isChallenged;
    }
    /**
     * Return challenger
     */
    public Player getChallenger(){
        return this.challenger;
    }

    public String getName(){
        return this.name;
    }

    public void setID(String ID){
        this.gameID = ID;
    }

    public String getGameID(){
        return this.gameID;
    }

    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
        }
        else if (obj == null){
            return false;
        }
        Player object = (Player) obj;
        return getName().equals(object.getName());
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode(){
        return name.hashCode();
    }


}
