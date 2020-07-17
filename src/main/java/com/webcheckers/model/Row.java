package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Row entity that helps GameView store data
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class Row implements Iterable<Space> {
    private int index;
    private List<Space> space;

    public Row(int index, boolean valid, Piece.Color color, Board board) {
        this.index = index;
        this.space = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            if(valid) {
                space.add(new Space(i, new Piece(Piece.PieceType.SINGLE, color, board), valid,this));
            } else {
                space.add(new Space(i, null, valid,this));
            }
            valid = !valid;
        }

    }

    public Row(int index, boolean valid, Board board) {
        this.index = index;
        this.space = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            space.add(new Space(i, null, valid,this));
            valid = !valid;

        }
    }

    public Row(int index, List<Space> spaces){
        this.index = index;
        this.space = spaces;
    }
    public Row(){

    }

    public int getIndex(){
        return index;
    }

    public List<Space> getSpaces() {
        return this.space;
    }

    public Space getASpace(int index){
        return space.get(index);
    }
    @Override
    public Iterator<Space> iterator() {
        return space.iterator();
    }
}
