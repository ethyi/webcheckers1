package com.webcheckers.model;

public class PiecePair {
    private Position position;
    private Piece piece;

    public PiecePair(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;

    }

    public Piece getPiece() {
        return piece;
    }

    public Position getPosition() {
        return position;
    }
}

