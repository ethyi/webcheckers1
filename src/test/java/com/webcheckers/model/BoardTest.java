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
    //private Positions


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

    //@Test
    //void getLastReplaced(){
    //    assertEquals();
    //}

    @Test
    void iteratorTest(){
    }

}
