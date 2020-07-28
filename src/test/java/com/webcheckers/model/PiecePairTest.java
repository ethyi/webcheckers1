
package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model-tier")
/**
 * Tests the PiecePair Class
 */
public class PiecePairTest {
    Position position = new Position(1, 2);
    Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
    final PiecePair piecePair = new PiecePair(position, piece);


    @Test
    public void TestNotNull() {
        assertNotNull(piecePair);
    }

    @Test
    public void testPiece() {
        assertEquals(piecePair.getPiece(), piece);
    }

    @Test
    public void testPosition() {
        assertEquals(piecePair.getPosition(), position);
    }
}
