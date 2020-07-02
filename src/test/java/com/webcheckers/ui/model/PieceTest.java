package com.webcheckers.ui.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Piece;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model-tier")
public class PieceTest {
    final Piece CuT = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
    final Piece white = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);


    public void makePieceTest() {
        assertNotNull(CuT, "Piece is not null");
        assertNotNull(white);
    }

    public void colorTest() {
        assertEquals(CuT.getColor(), Piece.Color.RED);
        assertEquals(white.getColor(), Piece.Color.WHITE);
    }

    public void typeTest() {
        assertEquals(CuT.getType(), Piece.PieceType.SINGLE);
        assertEquals(white.getColor(), Piece.PieceType.KING);
    }


}
