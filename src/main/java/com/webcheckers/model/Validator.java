package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Validator {

    public Piece.Color activeColor = Piece.Color.RED;
    public Board boardObj;
    public static List<Row> board;


    public Validator(Board board) {
        this.boardObj = board;
        this.board = board.getBoard();
    }
    public enum moveType {
        VALID, INVALID, WRONG_DIRECTION, NEED_TO_JUMP
    }

    public void switchActiveColor() {
        if (this.activeColor == Piece.Color.RED) {
            this.activeColor = Piece.Color.WHITE;
        } else {
            this.activeColor = Piece.Color.RED;
        }
    }

    public Piece.Color getActiveColor() {
        return this.activeColor;
    }

    public Board getBoard() {
        return boardObj;
    }

    public static boolean hasPiecesLeft(Piece.Color color) {
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

    private static boolean isMoveEmptySpace(Move move){
        Space valid = board.get(move.getEnd().getRow()).getSpaces().get(move.getEnd().getCell()) ;
        return valid.isEmpty() ;
    }

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

    public boolean forceJump() {
        for(int row = 0; row <= 7; row++) {
            for(int col = 0; col <= 7; col++) {
                Position p = new Position(row, col);
                if (!boardObj.getSpace(p).isEmpty() && boardObj.getSpace(p).getPiece().getColor() == activeColor) {
                    if (boardObj.canJump(p)) {
                        //System.out.println(row + ", " + col + " can jump!");
                        return true;
                    } else {
                        //System.out.println(row + ", " + col + " cant jump!");

                    }
                }
            }
        }
        return false;
    }



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
        /**
        if(legalMove) {
            if (activeColor == Piece.Color.RED) {
                activeColor = Piece.Color.WHITE;
            } else {
                activeColor = Piece.Color.RED;
            }
        }
         */
        if(legalMove) {
            return moveType.VALID;
        } else {
            return moveType.INVALID;
        }

    }

}

