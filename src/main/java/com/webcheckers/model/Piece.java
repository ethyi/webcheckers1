package com.webcheckers.model;
/**
 * Piece entity that holds each piece data
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class Piece {
    public enum PieceType {
        SINGLE, KING
    }

    public enum Color {
        RED, WHITE
    }
    private Color color;
    private PieceType type;

    public Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
