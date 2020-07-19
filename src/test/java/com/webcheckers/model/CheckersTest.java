package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersTest {
    CheckersGame checkers;
    String id ="1";
    Player redPlayer = new Player("y");
    Player whitePlayer = new Player("x");
    Piece.Color activeColor = Piece.Color.RED;

    @BeforeEach
    void setUp(){

        checkers = new CheckersGame(id,redPlayer,whitePlayer);
    }

    @Test
    void getRedPlayerTest() {
        assertEquals(redPlayer,checkers.getRedPlayer());
    }

    @Test
    void getWhitePlayer() {
        assertEquals(whitePlayer,checkers.getWhitePlayer());

    }

    @Test
    void getActiveColor() {
        assertEquals(activeColor,checkers.getActiveColor());

    }

    @Test
    void switchActiveColor() {
        checkers.switchActiveColor();
        assertEquals(Piece.Color.WHITE,checkers.getActiveColor());

        checkers.switchActiveColor();
        assertEquals(Piece.Color.RED,checkers.getActiveColor());
    }

    @Test
    void testEquals() {
        CheckersGame n_checkers = new CheckersGame(id,redPlayer,whitePlayer);
        assertTrue(checkers.equals(n_checkers));
        n_checkers = new CheckersGame("2",redPlayer,whitePlayer);
        assertFalse(checkers.equals(n_checkers));

    }

    @Test
    void testToString() {
        assertEquals(id,checkers.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(id.hashCode(),checkers.hashCode());

    }
}