package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {
    Space CuT= new Space(0,new Piece(Piece.PieceType.SINGLE, Piece.Color.RED),false);

    @Test
    void isValid() {
        assertFalse(CuT.isValid());
    }

    @Test
    void getCellIdx() {
        assertNotNull(CuT.getCellIdx());
    }

    @Test
    void isEmpty() {
        assertFalse(CuT.isEmpty());
    }


}