package com.webcheckers.model;

/**
 * Checkers Entity that holds each game session's data
 */
public class Checkers {

    private String id;
    private Player redPlayer;
    private Player whitePlayer;
    private Piece.Color activeColor;
    private BoardView boardView;
    /**
     * Create checkers data object
     * @param id game id
     * @param red red Player
     * @param white white Player
//     * @param bottomColor color that wil be at the bottom of this board
     */
    public Checkers(String id, Player red, Player white){
        this.id = id;
        this.redPlayer = red;
        this.whitePlayer = white;
        this.activeColor = Piece.Color.RED;
        this.boardView = new BoardView(Piece.Color.RED);
    }

    public BoardView getBoardView(){
        return this.boardView;
    }

    public Player getRedPlayer(){
        return redPlayer;
    }

    public Player getWhitePlayer(){
        return whitePlayer;
    }

    public Piece.Color getActiveColor(){
        return activeColor;
    }

    public void switchActiveColor(){
        if (activeColor.equals(Piece.Color.RED)){
            activeColor = Piece.Color.WHITE;
        }
        else{
            activeColor = Piece.Color.RED;
        }
    }


    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
        }
        if(!(obj instanceof Checkers)){
            return false;
        }
        final Checkers object = (Checkers) obj;
        return this.id.equals(object.id);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}
