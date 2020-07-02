package com.webcheckers.ui.model;

import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.GameView;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameViewTest {
    final Player p1 = new Player("p1");
    final Player p2 = new Player("p2");
    final GameView CuT = new GameView(p1, p2, Piece.Color.RED);

    public void testBoard() {
        assertNotNull(CuT);
    }

    public void testSetUp() {
        CuT.setupBoard();
        final List board = CuT.getBoard();
        assertTrue(board.size() != 0);

    }

    public void testColor() {
        assertTrue(p1.getColor() == CuT.getPlayerColor());
    }
}
