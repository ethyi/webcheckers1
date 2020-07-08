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
    private Row row;
    public Space(int cellIdx,Piece piece,boolean valid,Row row) {
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.valid = valid;
        if(this.piece!=null)
            this.piece.space = this;
        this.row = row;
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

    public Row getRow() {
        return row;
    }

    /**
     * This will set the piece that is in this space if there is any, w// also set the piece in this space to this
     * @param piece
     */
    public void setPiece(Piece piece){
        this.piece = piece;
        piece.space = this;
    }
    void setSpaceEmpty(){
        this.piece = null;
    }
}
