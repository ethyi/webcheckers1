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
    private Checkers checkers;
    boolean isChallenger;
    /**
     * Create Player with username
     * @param name username
     */
    public Player(String name){
        this.name = name;
        this.checkers=null;
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

    public Checkers getCheckers(){
        return checkers;
    }
    public void updateCheckers(Checkers checkers){
        this.checkers = checkers;
    }
    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
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
