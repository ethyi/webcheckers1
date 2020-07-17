package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Board entity that holds board data.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class Board {
    private List<Row> board;

    public Board() {
        board = new ArrayList<Row>();
        setupBoard();
    }

    public void setupBoard() {
        boolean valid = false;
        for(int i =0; i<3; i++) {
            board.add(new Row(i, valid, Piece.Color.WHITE, this));
            valid = !valid;

        }

        for(int j = 3; j < 5; j++) {
            board.add(new Row(j, valid,this));
            valid = !valid;
        }

        for(int i =5; i<8; i++) {
            board.add(new Row(i, valid, Piece.Color.RED,this));
            valid = !valid;
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


}
