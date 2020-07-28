package com.webcheckers.model;

//import javafx.geometry.Pos;

import java.util.List;

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

    /**
     * creates a Piece object that holds piece type and color
     * @param type Piece type (Single, King)
     * @param color Piece Color (Red, White)
     */
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

    public boolean isAKing(){
        return type==PieceType.KING;
    }

    public void promote() {
        this.type = PieceType.KING;
    }

    @Override
    public boolean equals( Object obj){
        if(obj==this) {
            return true;
        }
        else if (obj == null){
            return false;
        }
        Piece object = (Piece) obj;
        Boolean type = (this.type==object.type);
        Boolean color = (this.color==object.color);
        return type&&color;
    }

    @Override
    public String toString() {
        return this.type.toString() + ", " +  this.getColor().toString();
    }




}
