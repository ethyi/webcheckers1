package com.webcheckers.model;

/**
 * Checkers Entity that holds each game's attribute data
 *  * @author Tony Jiang
 *  * @author Ethan Yi
 *  * @author Aubrey Tarmu
 */
public class CheckersGame {

    private String id;
    private Player redPlayer;
    private Player whitePlayer;
    private Piece.Color activeColor;
    private Board board;
    private Validator validator;
    boolean isGameOver;
    /**
     * Create checkers data object
     * @param id game id
     * @param red red Player
     * @param white white Player
     */
    public CheckersGame(String id, Player red, Player white){
        this.id = id;
        this.redPlayer = red;
        this.whitePlayer = white;
        this.activeColor = Piece.Color.RED;
        this.board = new Board();
        this.validator = new Validator(this.board);

    }
    public enum GameOver {
        ONGOING, RED_WIN, RED_LOSS
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

    public Validator getValidator() {
        return validator;
    }

    public void switchActiveColor(){
        if (activeColor.equals(Piece.Color.RED)){
            activeColor = Piece.Color.WHITE;
        }
        else{
            activeColor = Piece.Color.RED;
        }
        validator.switchActiveColor();
    }
    public GameOver gameState(){
        if (!board.hasPiecesLeft(Piece.Color.RED)){
            isGameOver =true;
            return GameOver.RED_LOSS;
        }
        else if(!board.hasPiecesLeft(Piece.Color.WHITE)){
            isGameOver =true;
            return GameOver.RED_WIN;
        }
        else{
            return GameOver.ONGOING;
        }

    }

    public boolean isGameOver() {
        return isGameOver;
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
