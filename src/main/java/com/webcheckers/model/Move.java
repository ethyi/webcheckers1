package com.webcheckers.model;

import javafx.geometry.Pos;

public class Move {
    private Position start;
    private Position end;

    private Piece.Color color;
    private Player player;

    public Move(Position start, Position end) {
        this.start = start;
        this.end = end;
    }

    public Position getStart() {
        return this.start;
    }

    public Position getEnd() {
        return this.end;
    }

    public void setColor(Piece.Color color) {
        this.color = color;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Piece.Color getColor() {
        return this.color;
    }

    public boolean moveOnBoard() {
        return this.start.withinBoard() && this.end.withinBoard();
    }

    public boolean isRegularMove() {
        int xshift = Math.abs(this.start.getRow() - this.end.getRow());
        int yshift = Math.abs(this.start.getCell()- this.end.getCell());
        return (xshift == yshift && xshift == 1);

    }

    public boolean isJumpMove() {
        int xshift = Math.abs(this.start.getRow() - this.end.getRow());
        int yshift = Math.abs(this.start.getCell()- this.end.getCell());
        return (xshift == yshift && xshift == 2);

    }

    /**
     * Gets the position of the piece that's jumped in the case of a jump.
     * @return the position of the captured piece.a
     */
    public Position getMid() {
        if(isJumpMove()) {
            int midRow = start.getRow() + (end.getRow() - start.getRow())/2;
            int midCell = start.getCell() + (end.getCell() - start.getCell())/2;

            return new Position(midRow, midCell);

        } else {
            return start;
        }
    }

    public String toString() {
        return String.format("Player wants to move piece from (%d, %d) to (%d, %d)", start.getRow(),
                start.getCell(), end.getRow(), end.getCell());
    }

}
