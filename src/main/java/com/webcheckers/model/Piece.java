package com.webcheckers.model;

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
