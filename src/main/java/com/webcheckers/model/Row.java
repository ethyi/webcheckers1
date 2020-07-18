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
    private List<Space> spaceList;

    /**
     * creates a default row with pieces
     * @param index row index in board
     * @param valid boolean if first cell is black
     * @param color color of single piece
     */
    public Row(int index, boolean valid, Piece.Color color) {
        this.index = index;
        this.spaceList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            if(valid) {
                spaceList.add(new Space(i, new Piece(Piece.PieceType.SINGLE, color), valid));
            }
            else {
                spaceList.add(new Space(i, null, valid));
            }
            valid = !valid;
        }

    }

    /**
     * creates a default row without pieces
     * @param index row index in board
     * @param valid boolean if first cell is black
     */
    public Row(int index, boolean valid) {
        this.index = index;
        this.spaceList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            spaceList.add(new Space(i, null, valid));
            valid = !valid;

        }
    }

    /**
     * creates a row with given Spaces
     * @param index row index in board
     * @param spaces list of spaces
     */
    public Row(int index, List<Space> spaces){
        this.index = index;
        this.spaceList = spaces;
    }

    public int getIndex(){
        return index;
    }

    public List<Space> getSpaces() {
        return this.spaceList;
    }

    public Space getASpace(int index){
        return spaceList.get(index);
    }
    @Override
    public Iterator<Space> iterator() {
        return spaceList.iterator();
    }
}
