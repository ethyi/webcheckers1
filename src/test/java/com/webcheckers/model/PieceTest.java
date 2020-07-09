
package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import javafx.geometry.Pos;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model-tier")
/**
 * Tests the Piece Class
 */
public class PieceTest {
    final GameView gameView = new GameView(Piece.Color.RED);
    //TODO create mock
    final Piece CuT = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED,gameView);
    final Piece white = new Piece(Piece.PieceType.KING, Piece.Color.WHITE,gameView);


    /**
     * Were the pieces successfully made?
     */
    @Test
    public void makePieceTest() {
        assertNotNull(CuT, "Piece is not null");
        assertNotNull(white);
    }

    /**
     * Tests the pieces' colors.
     */
    @Test
    public void colorTest() {
        assertEquals(CuT.getColor(), Piece.Color.RED);
        assertEquals(white.getColor(), Piece.Color.WHITE);
    }

    /**
     * Tests the pieces' types.
     */
    @Test
    public void typeTest() {
//        assertEquals(CuT.getType(), Piece.PieceType.SINGLE);
//        assertEquals(white.getColor(), Piece.PieceType.KING);
    }

    @Test
    public void isAKing(){

        assertFalse(CuT.isAKing());
    }
    @Test
    public void promote(){
        CuT.promote();
        assertTrue(CuT.isAKing());
    }
    @Test
    public void normalMoveTest(){
        Position nextPos = new Position(0,7);
        gameView.setupBoard();
        GameView.getSpace(5,5).setPiece(CuT);
        CuT.normalMove(nextPos);

    }
    @Test
    public void getSpace(){
        gameView.setupBoard();
        GameView.getSpace(5,5).setPiece(CuT);
        Space s = GameView.getSpace(5,5);
        assertEquals(s,CuT.getSpace());
    }
    @Test
    void JumpMoveTest(){
        Position endPos = new Position(0,0);
        gameView.setupBoard();
        GameView.getSpace(5,5).setPiece(CuT);

        CuT.normalMove(endPos);
        Piece targetPiece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE,gameView);
        GameView.getSpace(5,7).setPiece(targetPiece);
        System.out.println("SDFSDFSD: "+ GameView.getSpace(5,7).getPiece());
        System.out.println( GameView.getSpace(5,7).getPiece()==targetPiece);
        System.out.println(targetPiece.getSpace());
        targetPiece.removePiece();
    }
    @Test
    public void  getType(){
            assertEquals(Piece.PieceType.SINGLE,CuT.getType());
    }
}

