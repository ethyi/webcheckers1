package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * GameBoard entity that holds board data.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class BoardView implements Iterable<Row>{
    private List<Row> board;
    private Piece.Color bottomColor;

    public BoardView(Piece.Color bottomColor) {
        board = new ArrayList<Row>();
        this.bottomColor = bottomColor;
        setupBoard();
    }

    public void setupBoard() {
        boolean valid = false;
        for(int i =0; i<3; i++) {
            board.add(new Row(i, valid,bottomColor == Piece.Color.RED? Piece.Color.WHITE: Piece.Color.RED, this));
            valid = !valid;

        }

        for(int j = 3; j < 5; j++) {
            board.add(new Row(j, valid,this));
            valid = !valid;
        }

        for(int i =5; i<8; i++) {
            board.add(new Row(i, valid, bottomColor,this));
            valid = !valid;
            /**
            if(playerColor== Piece.Color.RED){
                board.add(new Row(i, valid, Piece.Color.WHITE,this));
            }
            else{
                board.add(new Row(i, valid, Piece.Color.RED,this));
            }
             */
        }
    }
    /**
     * this will get a piece at a specific row and Coll
     * @param row
     * @param col
     * @return
     */
    public Space getSpace(int row, int col){
       return board.get(row).getASpace(col);
    }

    public Piece.Color getBottomColor() {
        return bottomColor;
    }

    public void setBottomColor(Piece.Color bottomColor) {
        this.bottomColor = bottomColor;
    }

    /**
     * this will get a piece at a specific row and Coll
     * @param position
     * @return
     */
    public Space getSpace(Position position){
        return board.get(position.getRow()).getASpace(position.getCell());
    }

    /**
     * returns Iterable board
     * @return board as List of rows
     */
    public List<Row> getBoard() {
        return board;
    }

    /**
     * Changes board according to move being made.
     * @param move Move to be made
     */
    public void MovePiece(Move move){
        Position start = move.getStart();
        Position end = move.getEnd();
        Space startSpace = getSpace(start);
        Space endSpace = getSpace(end);
        endSpace.setPiece(startSpace.getPiece());
        startSpace.setSpaceEmpty();
    }

    @Override
    public Iterator<Row> iterator() {
        return board.iterator();
    }

}
