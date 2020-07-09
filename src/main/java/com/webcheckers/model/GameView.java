package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * GameBoard entity that holds board data.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class GameView implements Iterable<Row>{
    private static List<Row> board;
    private Piece.Color bottomColor;

    public GameView(Piece.Color bottomColor) {
        board = new ArrayList<Row>();
        this.bottomColor = bottomColor;
        setupBoard();
    }

    public void setupBoard() {
        boolean valid = false;
        for(int i =0; i<3; i++) {
            board.add(new Row(i, valid,bottomColor == Piece.Color.RED? Piece.Color.WHITE: Piece.Color.RED, this));
            valid = !valid;

        }

        for(int j = 3; j < 5; j++) {
            board.add(new Row(j, valid,this));
            valid = !valid;
        }

        for(int i =5; i<8; i++) {
            board.add(new Row(i, valid, bottomColor,this));
            valid = !valid;
            /**
            if(playerColor== Piece.Color.RED){
                board.add(new Row(i, valid, Piece.Color.WHITE,this));
            }
            else{
                board.add(new Row(i, valid, Piece.Color.RED,this));
            }
             */
        }
    }
    /**
     * this will get a piece at a specific row and Coll
     * @param row
     * @param coll
     * @return
     */
    public static  Space getSpace(int row, int coll){
       return board.get(row).getASpace(coll);
    }

    public Piece.Color getBottomColor() {
        return bottomColor;
    }

    public void setBottomColor(Piece.Color bottomColor) {
        this.bottomColor = bottomColor;
    }

    /**
     * this will get a piece at a specific row and Coll
     * @param position
     * @return
     */
    public static  Space getSpace(Position position){
        return board.get(position.getRow()).getASpace(position.getCell());
    }

    public static List getBoard() {
        return board;
    }

    @Override
    public Iterator<Row> iterator() {
        return board.iterator();
    }

    public void putPiece() {
    }
}
