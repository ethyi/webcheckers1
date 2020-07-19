package com.webcheckers.model;

/**
 * Checkers Entity that holds each game session's data
 */
public class CheckersGame {

    private String id;
    private Player redPlayer;
    private Player whitePlayer;
    private Piece.Color activeColor;
    private Board board;
    private Validator validator;
    /**
     * Create checkers data object
     * @param id game id
     * @param red red Player
     * @param white white Player
//     * @param bottomColor color that wil be at the bottom of this board
     */
    public CheckersGame(String id, Player red, Player white){
        this.id = id;
        this.redPlayer = red;
        this.whitePlayer = white;
        this.activeColor = Piece.Color.RED;
        this.board = new Board();
        this.validator = new Validator(this.board.getBoard());
    }

    public Validator getValidator() {
        return this.validator;
    }
    public Board getBoard(){
        return board;
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
        if(!(obj instanceof CheckersGame)){
            return false;
        }
        final CheckersGame object = (CheckersGame) obj;
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
