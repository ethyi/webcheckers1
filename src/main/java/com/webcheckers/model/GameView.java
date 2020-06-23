package com.webcheckers.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameView implements Iterable<Row>{
    private Player p1;
    private Player p2;
    private List<Row> board;

    public GameView(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new ArrayList<>();
        setupBoard();
    }
    public void setupBoard() {
        boolean valid = true;
        for(int i =0; i<8; i++) {
            board.add(new Row(i, valid));
            valid = !valid;
        }
    }

    @Override
    public Iterator<Row> iterator() {
        return board.iterator();
    }
}
