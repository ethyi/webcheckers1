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
    private Piece lastCaptured;

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

    public Piece getLastCaptured() {
        return lastCaptured;
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

    public boolean jumpHelperTest(Space destination, Space jumped, Piece.Color color) {

        if (destination == null || jumped ==  null) {
            return false;
        }
        return(destination.isEmpty() && !jumped.isEmpty()
                && jumped.getPiece().getColor() != color);

    }

    public boolean canJump(Position p) {
        Space startSpace = getSpace(p);
        int row = p.getRow();
        int cell = p.getCell();
        Piece piece = startSpace.getPiece();
        Piece.Color pieceColor = startSpace.getPiece().getColor();

        Space upperLeftSpace = null;
        Space upperLeftMiddle = null;
        Space upperRightSpace = null;
        Space upperRightMiddle = null;
        Space lowerLeftSpace = null;
        Space lowerLeftMiddle = null;
        Space lowerRightSpace = null;
        Space lowerRightMiddle = null;

        if (row - 2 >= 0 && cell - 2 >= 0) {
            upperLeftSpace = getSpace(new Position(row - 2, cell - 2));
            upperLeftMiddle = getSpace(new Position(row - 1, cell - 1));
        }
        if (row - 2 >= 0 && cell + 2 <= 7) {
            upperRightSpace = getSpace(new Position(row - 2, cell + 2));
            upperRightMiddle = getSpace(new Position(row - 1, cell + 1));
        }
        if (row + 2 <= 7 && cell - 2 >= 0) {
            lowerLeftSpace = getSpace(new Position(row + 2, cell - 2));
            lowerLeftMiddle = getSpace(new Position(row + 1, cell - 1));
        }
        if (row + 2 <= 7 && cell + 2 <= 7) {
            lowerRightSpace = getSpace(new Position(row + 2, cell + 2));
            lowerRightMiddle = getSpace(new Position(row + 1, cell + 1));
        }

        if (!piece.isAKing()) {
            if (pieceColor == Piece.Color.RED) {
                if (jumpHelperTest(upperLeftSpace, upperLeftMiddle, Piece.Color.RED) || jumpHelperTest(upperRightSpace,
                        upperRightMiddle, Piece.Color.RED)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (jumpHelperTest(lowerLeftSpace, lowerLeftMiddle, Piece.Color.WHITE) || jumpHelperTest(lowerRightSpace,
                        lowerRightMiddle, Piece.Color.WHITE)) {
                    return true;
                } else {
                    return false;
                }
            }

        } else {
            return jumpHelperTest(lowerLeftSpace, lowerLeftMiddle, pieceColor) ||
                    jumpHelperTest(upperLeftSpace, upperLeftMiddle, pieceColor) ||
                    jumpHelperTest(lowerRightSpace, lowerRightMiddle, pieceColor) ||
                    jumpHelperTest(upperRightSpace, upperRightMiddle, pieceColor);
        }
    }

        public void removePiece(Position position) {
            int row = position.getRow();
            int col = position.getCell();
            board.get(row).getASpace(col).setSpaceEmpty();
        }

        public Piece getPiece(Position position) {
            int row = position.getRow();
            int col = position.getCell();
            return board.get(row).getASpace(col).getPiece();
        }


    }




