package com.webcheckers.model;

import java.util.List;

/**
 * Piece entity that holds each piece data
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class Piece {

    public enum PieceType {
        SINGLE, KING
    }

    public enum Color {
        RED, WHITE
    }
    private Color color;
    private PieceType type;
    Space space;
    private List<Row> board;
    GameView gameView;
    private List<Row> validSpaces;
    public Piece(PieceType type, Color color, GameView gameView) {
        this.type = type;
        this.color = color;
        this.gameView = gameView;
        board = gameView.getBoard();
    }

    public boolean isAKing(){
        return type==PieceType.KING;
    }

    public void promote() {
        this.type = PieceType.KING;
    }



    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
    public boolean isMoveValid(int n_row,int n_coll) {
        boolean spotIsValid =gameView.getSpace(n_row,n_coll).isValid();
        boolean spotIsEmpty =gameView.getSpace(n_row,n_coll).getPiece()==null;
        boolean notMovingToSameRow = n_row!=space.getRow().getIndex();
        boolean canMoveToRow = isAKing() ?  (space.getRow().getIndex() ==n_row+1 ||
                space.getRow().getIndex() ==n_row-1) :
                space.getRow().getIndex() ==n_row+1;
        return spotIsValid&&spotIsEmpty&&notMovingToSameRow&&canMoveToRow;
    }

}
