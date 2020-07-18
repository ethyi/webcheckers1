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

    /**
     public  void jumpMove(Position endPos, Piece targetPiece){
     normalMove(endPos);
     targetPiece.removePiece();
     }
     */

    /**
     public  void normalMove(Position endPos){
     Space nextSpace = gameboard.getSpace(endPos.getRow(),endPos.getCell());
     space.setSpaceEmpty();
     nextSpace.setPiece(this);
     //  System.out.println(nextSpace.getPiece());
     if(endPos.getRow()==0){
     promote();
     }

     }
     */



}
