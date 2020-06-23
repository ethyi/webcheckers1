package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Row implements Iterable<Space> {
    private int index;
    private List<Space> space;

    public Row(int index, boolean valid, Piece.Color color) {
        this.index = index;
        this.space = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            if(valid) {
                space.add(new Space(i, new Piece(Piece.PieceType.SINGLE, color), valid));
            } else {
                space.add(new Space(i, null, valid));
            }
            valid = !valid;
        }

    }

    public Row(int index, boolean valid) {
        this.index = index;
        this.space = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            space.add(new Space(i, null, valid));
            valid = !valid;

        }
    }

    @Override
    public Iterator<Space> iterator() {
        return space.iterator();
    }

    public int getIndex(){
        return index;
    }
}
