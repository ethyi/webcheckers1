package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {
    Board board = new Board();
    Space CuT= new Space(0,new Piece(Piece.PieceType.SINGLE, Piece.Color.RED),false);
    Space mid = board.getSpace(new Position(4,4));
    @Test
    void isValid() {
        assertFalse(CuT.isValid());
        assertTrue(mid.isEmpty());
        assertFalse(mid.isValid());
    }

    @Test
    void getCellIdx() {
        assertNotNull(CuT.getCellIdx());
    }

    @Test
    void isEmpty() {
        assertFalse(CuT.isEmpty());
    }

    @Test
    void testEquals() {
        Space midCopy = board.getSpace(new Position(4, 4));
        Space midCopy2 = board.getSpace(new Position(4, 4));

        Space midCopy3 = new Space(4, null, true);

        midCopy2.setPiece(new Piece(Piece.PieceType.KING, Piece.Color.RED));
        midCopy3.setPiece(new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE));

        assertTrue(midCopy.equals(mid));
        assertFalse(midCopy.equals(CuT));
        assertFalse(midCopy2.equals(midCopy3));
        assertTrue(midCopy.equals(midCopy2));

    }



}