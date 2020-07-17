package com.webcheckers.model;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

/**
 * GameBoard entity that holds board view data.\
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class BoardView implements Iterable<Row>{
    private List<Row> board;
    private Piece.Color bottomColor;

    /**
     * Creates a board that can be iterated through
     * @param board checkers board
     * @param bottomColor board perspective
     */
    public BoardView(Board board, Piece.Color bottomColor) {
        this.board = board.getBoard();
        this.bottomColor = bottomColor;
    }

    public Piece.Color getBottomColor() {
        return bottomColor;
    }

    /**
     * returns Iterable board
     * @return board as List of rows
     */
    public List<Row> getBoard() {
        return board;
    }

    /**
     * creates an inverse board that maintains row and space indexes
     * @return inverse board as List of rows
     */
    public List<Row> inverseBoard(){
        List<Row> inverseRowList = new ArrayList<>();
        for (int i = board.size()-1; i>=0; i--){
            List<Space> SpaceList = board.get(i).getSpaces();
            List<Space> inverseSpaceList = new ArrayList<>();
            for (int j = SpaceList.size()-1; j>=0; j--){
                inverseSpaceList.add(SpaceList.get(j));
            }
            Row tempRow = new Row(i, inverseSpaceList);
            inverseRowList.add(tempRow);
        }

        return inverseRowList;
    }

    @Override
    public Iterator<Row> iterator() {
        if (bottomColor.equals(Piece.Color.RED)){
            return board.iterator();
        }
        else{
            return inverseBoard().iterator();
        }

    }

}
