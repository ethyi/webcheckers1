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
    private Player p1;
    private Player p2;
    private List<Row> board;
//    private List<Row> p1board;
//    private List<Row> p2board;
    private Piece.Color playerColor;
//    boolean isPlayer1GameView;
    /**
     * init gameView, with the two players playing and
     * @param p1 player 1
     * @param p2 player 2
     */
    public GameView(Player p1, Player p2, Piece.Color playerColor) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new ArrayList<>();
//        this.p1board = new ArrayList<>();
//        this.p2board = new ArrayList<>();
        this.playerColor = playerColor;
        setupBoard();
    }

    public Piece.Color getPlayerColor() {
        return playerColor;
    }

    /**
     *
     */
    public void setupBoard() {
        boolean valid = false;
        for(int i =0; i<3; i++) {

            board.add(new Row(i, valid, playerColor,this));
            valid = !valid;
        }

        for(int j = 3; j < 5; j++) {
            board.add(new Row(j, valid,this));
            valid = !valid;
        }

        for(int i =5; i<8; i++) {
            if(playerColor== Piece.Color.RED){
                board.add(new Row(i, valid, Piece.Color.WHITE,this));
            }
            else{
                board.add(new Row(i, valid, Piece.Color.RED,this));
            }
            valid = !valid;
        }
    }
    /**
     * this will get a piece at a specific row and Coll
     * @param row
     * @param coll
     * @return
     */
    public  Space getSpace(int row, int coll){
       return board.get(row).getASpace(coll);
    }

    /**
     * this will remove a piece at one space then add it back at a new space
     * @param cur_row row of the piece that is currently selected
     * @param cur_coll coll of the piece that is currently selected
     * @param n_row row of the piece that is next
     * @param n_coll coll of the piece that is next
     */
   public void movePiece(int cur_row,int cur_coll,int n_row,int n_coll){
       //string of the move
       // json to gson

        Piece p = getSpace(cur_row,cur_coll).getPiece();
        if(p.isMoveValid(n_row,n_coll)){
            board.get(cur_row).getASpace(cur_coll).setPiece( null);
            board.get(n_row).getASpace(n_row).setPiece(p);
        }

    }

    public List getBoard() {
        return this.board;
    }

    @Override
    public Iterator<Row> iterator() {
        return board.iterator();
    }
}
