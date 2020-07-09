package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.utils.Assert;

import java.util.List;

/**
 * Tests the GameView class
 */
public class GameViewTest {
    private  final Piece.Color bottomColor = Piece.Color.RED;
    final GameView CuT = new GameView(bottomColor);
@BeforeEach
    void setUp(){
        for(int i=0;i<8;i++){
            CuT.setupBoard();
        }
    }

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
    @Test
    public static void getSpaceTest(int row, int coll){

         assertNotNull(GameView.getSpace(row,coll));
    }
    @Test
    static   void  getSpace(Position position){
        assertNotNull(GameView.getSpace(position));
    }
    @Test
    void getBottomColorTest(){
        assertEquals(Piece.Color.RED, CuT.getBottomColor());
    }

    @Test
    void iteratorTest(){
    }

}
