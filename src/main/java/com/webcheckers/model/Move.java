package com.webcheckers.model;

import javafx.geometry.Pos;

public class Move {

    private Position Start;
    private Position End;

    public Move(String data){
        String[] temp = data.split(":");
        int start_row = Character.getNumericValue(temp[2].charAt(0));
        int start_cell = Character.getNumericValue(temp[3].charAt(0));
        int end_row = Character.getNumericValue(temp[5].charAt(0));
        int end_cell = Character.getNumericValue(temp[6].charAt(0));
        this.Start = new Position(start_row,start_cell);
        this.End = new Position(end_row, end_cell);
    }

    public Position getStart(){
        return Start;
    }

    public Position getEnd(){
        return End;
    }

    public boolean isValid(){
        // TODO returns position validity
        return true;
    }
}
