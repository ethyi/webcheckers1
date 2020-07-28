package com.webcheckers.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private List<Position> positions = new ArrayList<>();
    private Piece lastMovedPiece;
    private Position lastReplaced = null;
    private List<PiecePair> captured = new ArrayList<>();

    /**
     * creates a new checkers board by calling setupBoard()
     */
    public Board() {
        board = new ArrayList<>();
        setupBoard();
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Position getLastReplaced() {
        return this.lastReplaced;
    }

    public void setLastReplaced(Position p) {
        this.lastReplaced = p;
    }

    public Piece getLastMovedPiece() {
        return lastMovedPiece;
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

    public List<PiecePair> getCaptured() {
        return this.captured;
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

    public boolean hasPiecesLeft(Piece.Color color) {
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (!board.get(i).getSpaces().get(j).isEmpty() && board.get(i)
                        .getSpaces().get(j).getPiece().getColor() == color) {
                    return true;
                }
            }
        }
        return false;

    }
    /**
     * Changes board according to move being made.
     * @param move Move to be made
     */
    public void MovePiece(Move move) {
        this.lastMove = move;
        this.positions.add(move.getStart());

        Position start = move.getStart();
        Position end = move.getEnd();
        Space startSpace = getSpace(start);
        this.lastMovedPiece = startSpace.getPiece();
        Space endSpace = getSpace(end);

        if(move.isJumpMove()) {
            captured.add(new PiecePair(move.getJumped(), getSpace(move.getJumped()).getPiece()));
        }
        endSpace.setPiece(startSpace.getPiece());
        startSpace.setPiece(null);

    }

    public void placePiece(Position position, Piece piece) {
        Space space = getSpace(position);
        space.setPiece(piece);
    }

    public void reset() {
        this.lastMove = null;
        this.positions.clear();
        this.lastMovedPiece = null;
        this.lastReplaced = null;
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

    /**
     * Checks if the destination is clear and the jumped piece is an opposing piece.
     * @param destination the destination Space
     * @param jumped the piece that's jumped
     * @param color the color that the jumped piece has to be different from.
     * @return
     */
    public boolean jumpHelperTest(Space destination, Space jumped, Piece.Color color) {

        if (destination == null || jumped ==  null) {
            return false;
        }
        return(destination.isEmpty() && !jumped.isEmpty()
                && jumped.getPiece().getColor() != color);

    }

    /**
     * Checks if there as a jump at a position.
     * @param p Position to be checked
     * @return is there a jump available?
     */
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
            System.out.println(p);
            System.out.println(jumpHelperTest(lowerLeftSpace, lowerLeftMiddle, pieceColor));
            System.out.println(jumpHelperTest(upperLeftSpace, upperLeftMiddle, pieceColor));
            System.out.println(jumpHelperTest(lowerRightSpace, lowerRightMiddle, pieceColor));
            System.out.println(jumpHelperTest(upperRightSpace, upperRightMiddle, pieceColor));

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




