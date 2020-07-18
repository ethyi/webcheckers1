package com.webcheckers.model;

//import javafx.geometry.Pos;

import java.util.List;
/**
 * Move entity that holds a move being made
 *  * @author Tony Jiang
 *  * @author Ethan Yi
 *  * @author Aubrey Tarmu
 */
public class Move {
    private Position start;
    private Position end;
    private Piece.Color color;
    private Player player;

    /**
     * creates positions based on move data by player
     * @param data move data as a string
     */
    public Move(String data) {
        String[] temp = data.split(":");
        int start_row = Character.getNumericValue(temp[2].charAt(0));
        int start_col = Character.getNumericValue(temp[3].charAt(0));
        int end_row = Character.getNumericValue(temp[5].charAt(0));
        int end_col = Character.getNumericValue(temp[6].charAt(0));
        this.start = new Position(start_row,start_col);
        this.end = new Position(end_row,end_col);
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
        return this.start.withinBoard() &&
                this.end.withinBoard();
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
    public Position getJumped() {
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
