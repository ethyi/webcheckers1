package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckersGameTest {
    CheckersGame checkersGame;
    String id ="1";
    Player redPlayer = new Player("y");
    Player whitePlayer = new Player("x");
    Piece.Color activeColor = Piece.Color.RED;

    @BeforeEach
    void setUp(){

        checkersGame = new CheckersGame(id,redPlayer,whitePlayer);
    }

    @Test
    void getRedPlayerTest() {
        assertEquals(redPlayer, checkersGame.getRedPlayer());
    }

    @Test
    void getWhitePlayer() {
        assertEquals(whitePlayer, checkersGame.getWhitePlayer());

    }

    @Test
    void getActiveColor() {
        assertEquals(activeColor, checkersGame.getActiveColor());

    }

    @Test
    void switchActiveColor() {
        checkersGame.switchActiveColor();
        assertEquals(Piece.Color.WHITE, checkersGame.getActiveColor());

        checkersGame.switchActiveColor();
        assertEquals(Piece.Color.RED, checkersGame.getActiveColor());
    }

    @Test
    void testEquals() {
        CheckersGame n_checkersGame = new CheckersGame(id,redPlayer,whitePlayer);
        assertTrue(checkersGame.equals(n_checkersGame));
        n_checkersGame = new CheckersGame("2",redPlayer,whitePlayer);
        assertFalse(checkersGame.equals(n_checkersGame));

    }

    @Test
    void testToString() {
        assertEquals(id, checkersGame.toString());
    }

    @Test
    void testHashCode() {
        assertEquals(id.hashCode(), checkersGame.hashCode());

    }
}