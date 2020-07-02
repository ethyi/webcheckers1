package com.webcheckers.model;

import java.lang.reflect.Array;
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
    private List<Row> p1board;
    private List<Row> p2board;
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
        this.p1board = new ArrayList<>();
        this.p2board = new ArrayList<>();
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

            board.add(new Row(i, valid, playerColor));
            valid = !valid;
        }

        for(int j = 3; j < 5; j++) {
            board.add(new Row(j, valid));
            valid = !valid;
        }

        for(int i =5; i<8; i++) {
            if(playerColor== Piece.Color.RED){
                board.add(new Row(i, valid, Piece.Color.WHITE));
            }
            else{
                board.add(new Row(i, valid, Piece.Color.RED));
            }
            valid = !valid;
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
