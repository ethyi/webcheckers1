package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Tests the GameView class
 */
public class BoardViewTest {
    private  final Piece.Color bottomColor = Piece.Color.RED;
    final BoardView CuT = new BoardView(new Board(),bottomColor);


    /**
     * Checks if the board is null
     */
    @Test
    public void testBoard() {

        assertNotNull(CuT);
    }



    @Test
    void getBottomColorTest(){
        assertEquals(Piece.Color.RED, CuT.getBottomColor());
    }

    @Test
    void iteratorTest(){
    }

}
