package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    private Position start;
    private Position end;

    Move move;
    @BeforeEach
    void setUp(){
        String data ="{\"start\":{\"row\":5,\"cell\":6},\"end\":{\"row\":4,\"cell\":7}}";

        String[] temp = data.split(":");
        int start_row = Character.getNumericValue(temp[2].charAt(0));
        int start_col = Character.getNumericValue(temp[3].charAt(0));
        int end_row = Character.getNumericValue(temp[5].charAt(0));
        int end_col = Character.getNumericValue(temp[6].charAt(0));
        this.start = new Position(start_row,start_col);
        this.end = new Position(end_row,end_col);
        move = new Move(data);
    }

    @Test
    void getStart() {
        start =move.getStart();
        assertEquals(start,move.getStart());
    }

    @Test
    void getEnd() {
        end =move.getEnd();
        assertEquals(end,move.getEnd());
    }

    @Test
    void setColor() {
        move.setColor(Piece.Color.RED);
        assertEquals(move.getColor(), Piece.Color.RED);
    }

    @Test
    void setPlayer() {
        Player p = new Player("x");
        move.setPlayer(p);
        assertEquals(move.getPlayer(),p);
    }
    @Test
    void ReverseMove(){
        move.reverseMove();
        assertEquals(move.getStart(), this.end);
        assertEquals(move.getEnd(), this.start);
    }

    @Test
    void moveOnBoard() {

        assertTrue(move.moveOnBoard());
    }

    @Test
    void isRegularMove() {
        int xshift = Math.abs(this.start.getRow() - this.end.getRow());
        int yshift = Math.abs(this.start.getCell()- this.end.getCell());
        assertTrue( move.isRegularMove());
    }

    @Test
    boolean isJumpMove() {
        int xshift = Math.abs(this.start.getRow() - this.end.getRow());
        int yshift = Math.abs(this.start.getCell()- this.end.getCell());
        assertFalse (move.isJumpMove());
        return xshift == yshift && xshift == 2;
    }

    @Test
    void getJumped() {
        int midRow = start.getRow() + (end.getRow() - start.getRow())/2;
        int midCell = start.getCell() + (end.getCell() - start.getCell())/2;
        Position p = move.getJumped();
        if(isJumpMove()){

            assertEquals(p,move.getJumped());
            assertEquals(midRow,move.getJumped().getRow());
            assertEquals(midCell,move.getJumped().getCell());


        } else {
//            assertEquals(start.getCell(),move.getStart().getCell());

        }


    }

    @Test
    void testToString() {
        assertEquals(String.format("Player wants to move piece from (%d, %d) to (%d, %d)", start.getRow(),
                start.getCell(), end.getRow(), end.getCell()),move.toString());

    }
}