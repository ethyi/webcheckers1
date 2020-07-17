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
    private Space space;
    private Board gameboard;
    private List<Row> board;
    private List<Row> validSpaces;
    public Piece(PieceType type, Color color, Board board) {
        this.type = type;
        this.color = color;
        this.gameboard = board;
        this.board = board.getBoard();
    }

    public boolean isAKing(){
        return type==PieceType.KING;
    }

    public void promote() {
        this.type = PieceType.KING;
    }


    public  void normalMove(Position endPos){
        Space nextSpace = gameboard.getSpace(endPos.getRow(),endPos.getCell());
        space.setSpaceEmpty();
        nextSpace.setPiece(this);
        //  System.out.println(nextSpace.getPiece());
        if(endPos.getRow()==0){
            promote();
        }

    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public  void jumpMove(Position endPos, Piece targetPiece){
        normalMove(endPos);
        targetPiece.removePiece();
    }
    public void removePiece(){
        space.setSpaceEmpty();
    }
    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

}
