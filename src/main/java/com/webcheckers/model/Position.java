package com.webcheckers.model;

public class Position {
    private int row;
    private int cell;

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

}
