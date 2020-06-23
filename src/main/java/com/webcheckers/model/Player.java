package com.webcheckers.model;

/**
 * Player entity that holds each player's data
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 */
public class Player {
    private boolean isChallenged;

    // Attributes

    private String name;
    private Player challenger;
    private boolean inGame;
    private boolean isP1;

    /**
     * Create Player with username
     * @param name username
     */
    public Player(String name){
        this.name = name;
        this.inGame = false;
        this.isP1 = false;
    }

    /**
     * Set if player is first player
     */
    public void setP1() {
        this.isP1 = true;
    }
    /**
     * Return if first player
     */
    public boolean isP1() {
        return this.isP1;
    }
    /**
     * Return username
     */
    public String getName() {
        return name;
    }
    /**
     * Return if in game
     */
    public boolean inGame() {
        return this.inGame;
    }
    /**
     * Set player game state
     */
    public void setGame(boolean status) {
        this.inGame = status;
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

    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
        }
        if(!(obj instanceof Player)){
            return false;
        }
        final Player object = (Player) obj;
        return this.name.equals(object.name);
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
