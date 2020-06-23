package com.webcheckers.model;

/**
 * Space entity that holds space data in game board.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class Space {
    private int cellIdx;
    private Piece piece;
    private boolean valid;

    public Space(int cellIdx,Piece piece,boolean valid) {
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.valid = valid;
    }

    public boolean isValid() {
        return valid && (this.getPiece() == null);
    }

    public int getCellIdx() {
        return cellIdx;
    }

    public Piece getPiece() {
        return piece;
    }
}
