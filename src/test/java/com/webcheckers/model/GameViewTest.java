package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests the GameView class
 */
public class GameViewTest {
    final Player p1 = new Player("p1");
    final Player p2 = new Player("p2");
    final GameView CuT = new GameView(p1, p2);

    /**
     * Checks if the board is null
     */
    @Test
    public void testBoard() {
        assertNotNull(CuT);
    }

    /**
     * Check if the board is properly set up.
     */
    @Test
    public void testSetUp() {
        CuT.setupBoard();
        final List board = CuT.getBoard();
        assertTrue(board.size() != 0);

    }

}
