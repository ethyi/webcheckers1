package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the GameView class
 */
public class BoardTest {

    final Board CuT = new Board();
    private List<Position> positions = new ArrayList<>();
    private Position p = new Position(1,1);

    /**
     * Checks if the board is null
     */
    @Test
    void testBoard() {
        assertNotNull(CuT);
    }

    @Test
    void getPositions(){
        assertEquals(positions, CuT.getPositions());
    }

    @Test
    void getLastReplaced(){
        CuT.setLastReplaced(p);
        assertEquals(p, CuT.getLastReplaced());
    }
    @Test
    void setLastReplaced(){
        CuT.setLastReplaced(p);
        assertEquals(p, CuT.getLastReplaced());
    }
    @Test
    void hasPiecesLeft(){
        assertTrue(CuT.hasPiecesLeft(Piece.Color.RED));
    }

    /*@Test
    void canJump(){
        assertFalse(CuT.canJump(p));
    }*/

    @Test
    void iteratorTest(){
    }

}
