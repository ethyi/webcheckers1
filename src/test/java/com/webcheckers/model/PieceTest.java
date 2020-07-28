
package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model-tier")
/**
 * Tests the Piece Class
 */
public class PieceTest {
    final Board board = new Board();
    final Piece CuT = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
    final Piece white = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);


    /**
     * Were the pieces successfully made?
     */
    @Test
    public void makePieceTest() {
        assertNotNull(CuT, "Piece is not null");
        assertNotNull(white);
    }

    /**
     * Tests the pieces' colors.
     */
    @Test
    public void colorTest() {
        assertEquals(CuT.getColor(), Piece.Color.RED);
        assertEquals(white.getColor(), Piece.Color.WHITE);
    }

    /**
     * Tests the pieces' types.
     */
    @Test
    public void typeTest() {
        assertEquals(CuT.getType(), Piece.PieceType.SINGLE);
        assertEquals(white.getColor(), Piece.Color.WHITE);
    }

    @Test
    public void isAKing(){
        assertFalse(CuT.isAKing());
        assertTrue(white.isAKing());
        CuT.promote();
        assertTrue(CuT.isAKing());
    }
    @Test
    public void promote(){
        CuT.promote();
        assertTrue(CuT.isAKing());
    }

    @Test
    public void equalityTest() {
        assertFalse(CuT.equals(white));
        Piece n = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        assertFalse(n.equals(white));
        Piece w = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        assertFalse(n.equals(w));
    }

    @Test
    public void printTest() {
        assertEquals(white.toString(), "KING, WHITE");
    }

    @Test
    public void  getType(){
            assertEquals(Piece.PieceType.SINGLE,CuT.getType());
    }
}

