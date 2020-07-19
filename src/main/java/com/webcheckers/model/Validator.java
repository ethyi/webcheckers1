package com.webcheckers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Validator {

    public Piece.Color activeColor = Piece.Color.RED;

    public enum moveType {
        VALID, WRONG_DIRECTION, FORCED_JUMP
    }

    public static boolean hasPiecesLeft(List<Row> board, Piece.Color color) {
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

    private static boolean isMoveEmptySpace(List<Row> board, Move move){
        Space valid = board.get(move.getEnd().getRow()).getSpaces().get(move.getEnd().getCell()) ;
        return valid.isEmpty() ;
    }

    private static boolean isRightDirection(List<Row> board, Move move) {
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
    private static boolean isJumpingOpponentPiece(List<Row> board, Move move) {
        boolean valid = false;
        if(move.isJumpMove()) {
            Position position = move.getJumped();
            Space space = board.get(position.getRow()).getSpaces().get(position.getCell());
            Piece piece = board.get(move.getStart().getRow()).getSpaces().get(move.getStart().getCell()).getPiece();
            if (!space.isEmpty()) {
                if (space.getPiece().getColor() != piece.getColor()) {
                    valid = true;
                }
            }
        }
        return valid;

    }

    public boolean validateMove(List<Row> board, Move move) {
        //boolean valid = hasPiecesLeft(board, Piece.Color.RED) && move.moveOnBoard() && isRightDirection(board, move) && isMoveEmptySpace(board, move) &&
               // (move.isRegularMove() || (move.isJumpMove() && isJumpingOpponentPiece(board, move)));
        //boolean valid1 =  hasPiecesLeft(board, Piece.Color.RED);
        move.setColor(activeColor);
        boolean validMoveType = move.isJumpMove() || move.isRegularMove();
        if(!validMoveType) return false;

        boolean validDirection = isRightDirection(board, move);
        if(!validDirection) {
            System.out.println(this.activeColor);
            System.out.println("Wrong direction");
            return false;
        }

        boolean legalMove;

        if(move.isJumpMove()) {
            legalMove = isJumpingOpponentPiece(board, move) && isMoveEmptySpace(board, move);

        } else {
            legalMove = isMoveEmptySpace(board, move);
        }
        if(activeColor == Piece.Color.RED) {
            activeColor = Piece.Color.WHITE;
        } else {
            activeColor = Piece.Color.RED;
        }
        return legalMove;

    }

}

