package com.webcheckers.model;

/**
 * Player entity that holds player name
 * @Ethan Yi ehy5032@rit.edu
 */
public class Player {
    private boolean isChallenged;

    // Attributes

    private String name;
    private Player challenger;

    /**
     * Create Player with username
     * @param name username
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Return username
     */
    public String getName() {
        return name;
    }

    public void setChallenged(boolean challenged, Player challenger) {
        this.isChallenged = challenged;
        this.challenger = challenger;
    }

    public boolean isChallenged() {
        return this.isChallenged;
    }

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
