package com.webcheckers.model;

/**
 * Position entity that holds position on board
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 */
public class Position {
    private int row;
    private int cell;

    /**
     * creates a position object and stores coordinates
     * @param row
     * @param cell
     */
    public Position(int row, int cell) {
        this.row = row;
        this.cell = cell;
    }

    public int getRow() {
        return this.row;

    }

    public int getCell() {
        return this.cell;

    }

    public boolean withinBoard() {
        boolean r = row >= 0 && row <= 7;
        boolean c = cell >= 0 && cell <= 7;
        return r && c;

    }

    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
        }
        else if (obj == null){
            return false;
        }
        Position object = (Position) obj;
        Boolean row = this.row==object.row;
        Boolean cell = this.cell==object.cell;
        return row&&cell;
    }

    @Override
    public String toString() {
        return ("(" + row + ", " + cell);
    }

}
