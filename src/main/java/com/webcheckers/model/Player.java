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
    private boolean inGame;

    /**
     * Create Player with username
     * @param name username
     */
    public Player(String name){
        this.name = name;
        this.inGame = false;
    }

    /**
     * Return username
     */
    public String getName() {
        return name;
    }

    public boolean inGame() {
        return this.inGame;
    }

    public void setGame(boolean status) {
        this.inGame = status;
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
