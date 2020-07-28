
/**
 * Validator class that checks moves made for validity
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 */

package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class Validator {

    public Piece.Color activeColor = Piece.Color.RED;
    public Board boardObj;
    public static List<Row> board;


    /**
     * Creates a new validator
     * @param board The board object necessary for validation checks
     */
    public Validator(Board board) {
        this.boardObj = board;
        this.board = board.getBoard();
    }

    /**
     * Types of moves the validator catches
     */
    public enum moveType {
        VALID, INVALID, WRONG_DIRECTION, NEED_TO_JUMP, MULTI_JUMP;
    }

    /**
     * Sets the active color to the other player's color.
     */
    public void switchActiveColor() {
        if (this.activeColor == Piece.Color.RED) {
            this.activeColor = Piece.Color.WHITE;
        } else {
            this.activeColor = Piece.Color.RED;
        }
    }

    /**
     * Gets the current player's color
     * @return The current player's color
     */
    public Piece.Color getActiveColor() {
        return this.activeColor;
    }

    /**
     * @return The Board object associated with the game this validator is a part of.
     */
    public Board getBoard() {
        return boardObj;
    }



    /**
     * Checks if the move is placed on an empty space
     * @param move
     * @return
     */
    private static boolean isMoveEmptySpace(Move move){
        Space valid = board.get(move.getEnd().getRow()).getSpaces().get(move.getEnd().getCell()) ;
        return valid.isEmpty() ;
    }

    /**
     * Checks if the move is going in the right condition
     * @param move
     * @return
     */
    private static boolean isRightDirection(Move move) {
        Piece piece = board.get(move.getStart().getRow()).getSpaces().get(move.getStart().getCell()).getPiece();
        boolean valid = false;
        if(!piece.isAKing()) {
            int startingRow = move.getStart().getRow();
            int endingRow = move.getEnd().getRow();

            if (move.getColor() == Piece.Color.RED) {
                valid = endingRow < startingRow;
            } else {
                valid = endingRow > startingRow;
            }
        } else {
            valid = true;
        }
        return valid;
    }

    /**
     * Checks if the jump move is jumping an opponents piece and not an empty space/your own piece
     * @param move
     * @return
     */
    private static boolean isJumpingOpponentPiece(Move move) {
        boolean valid = false;
        if(move.isJumpMove()) {
            Position position = move.getJumped();
            Space space = board.get(position.getRow()).getSpaces().get(position.getCell());
            Piece piece = board.get(move.getStart().getRow()).getSpaces().get(move.getStart().getCell()).getPiece();
            if (!space.isEmpty()) {
                if (space.getPiece().getColor() != piece.getColor()) {
                    valid = true;
                }
            } else {
                System.out.println("jumping air!");
            }
        }
        return valid;

    }

    /**
     * Checks if the player has a jump available.
     * @return is there a forced jump?
     */
    public boolean forceJump() {
        for(int row = 0; row <= 7; row++) {
            for(int col = 0; col <= 7; col++) {
                Position p = new Position(row, col);
                if (!boardObj.getSpace(p).isEmpty() && boardObj.getSpace(p).getPiece().getColor() == activeColor) {
                    if (boardObj.canJump(p)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Checks all the conditions.
     * @param move
     * @return the type of move that was made.
     */
    public moveType validateMove(Move move) {
        //boolean valid = hasPiecesLeft(board, Piece.Color.RED) && move.moveOnBoard() && isRightDirection(board, move) && isMoveEmptySpace(board, move) &&
        // (move.isRegularMove() || (move.isJumpMove() && isJumpingOpponentPiece(board, move)));
        //boolean valid1 =  hasPiecesLeft(board, Piece.Color.RED);
        move.setColor(activeColor);
        boolean validMoveType = move.isJumpMove() || move.isRegularMove();
        if(!validMoveType) {
            System.out.println("flying!");
            return moveType.INVALID;
        }

        boolean validDirection = isRightDirection(move);
        if(!validDirection) {
            System.out.println(this.activeColor);
            System.out.println("Wrong direction");
            return moveType.WRONG_DIRECTION;
        }

        boolean legalMove;

        if(move.isJumpMove()) {

            Position start = move.getStart();
            Position jumped = move.getJumped();
            Position end = move.getEnd();
            Space startSpace = boardObj.getSpace(start);
            Space jumpedSpace = boardObj.getSpace(jumped);

            Piece piece = startSpace.getPiece();
            System.out.println(piece);
            Piece ghost = new Piece(piece.getType(), piece.getColor());
            System.out.println(ghost);
            Piece jumpedPiece = jumpedSpace.getPiece();

            boardObj.placePiece(end, ghost);
            boardObj.removePiece(start);
            boardObj.removePiece(jumped);


            if(boardObj.canJump(move.getEnd())) {
                boardObj.removePiece(end);
                boardObj.placePiece(start, piece);
                boardObj.placePiece(jumped, jumpedPiece);

                System.out.println("multi jump");
                return moveType.MULTI_JUMP;
            }

            boardObj.removePiece(end);
            boardObj.placePiece(start, piece);
            boardObj.placePiece(jumped, jumpedPiece);


            legalMove = isJumpingOpponentPiece(move) && isMoveEmptySpace(move);


        } else {
            legalMove = isMoveEmptySpace(move);
        }

        if(!move.isJumpMove()) {
            if(forceJump()) {
                System.out.println("NEED TO JUMP");
                return moveType.NEED_TO_JUMP;
            }
        }

        if(legalMove) {
            boardObj.setFlag(false);
            return moveType.VALID;
        } else {
            return moveType.INVALID;
        }

    }

}

