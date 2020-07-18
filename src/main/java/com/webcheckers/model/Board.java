package com.webcheckers.model;

import java.util.ArrayList;
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
    private Move lastMove;

    /**
     * creates a new checkers board by calling setupBoard()
     */
    public Board() {
        board = new ArrayList<>();
        setupBoard();
    }

    /**
     * creates a default checkers board as a list of rows
     */
    public void setupBoard() {
        boolean valid = false;
        for(int i =0; i<3; i++) {
            board.add(new Row(i, valid, Piece.Color.WHITE));
            valid = !valid;
        }

        for(int j = 3; j < 5; j++) {
            board.add(new Row(j, valid));
            valid = !valid;
        }

        for(int i =5; i<8; i++) {
            board.add(new Row(i, valid, Piece.Color.RED));
            valid = !valid;
        }
    }

    /**
     * this will get a piece at a specific Position
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
     * returns the last move made
     * @return the last move
     */
    public Move getLastMove(){
        return lastMove;
    }

    /**
     * Changes board according to move being made.
     * @param move Move to be made
     */
    public void MovePiece(Move move){
        this.lastMove = move;
        Position start = move.getStart();
        Position end = move.getEnd();
        Space startSpace = getSpace(start);
        Space endSpace = getSpace(end);
        endSpace.setPiece(startSpace.getPiece());
        startSpace.setPiece(null);
    }

    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
        }
        else if (obj == null){
            return false;
        }
        Board object = (Board) obj;
        Boolean list = (this.board.equals(object.board));
        return list;
    }


}
