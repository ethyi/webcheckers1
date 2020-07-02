/*
Tests Player model tier class
@author Ethan Yi ehy5032@rit.edu
 */
package com.webcheckers.ui;

import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTester {

    @Test
    public void test_ctor_withArg(){
        final Player ctor_withARG = new Player("name");
        assertEquals("name",ctor_withARG.toString());
    }

    @Test
    public void test_equals(){
        final Player p1 = new Player("swen");
        final Player p2 = new Player("swen");
        assertEquals(p1,p2);
    }
    @Test
    public void test_attributes(){
        final Player p1 = new Player("name");
        final Player p2 = new Player("player2");
        p1.setGame(true);
        p1.setP1();
        p1.setColor(Piece.Color.WHITE);
        p1.setChallenged(true, p2);

        assertTrue(p1.inGame());
        assertTrue(p1.isP1());
        assertEquals(Piece.Color.WHITE, p1.getColor());
        assertTrue(p1.isChallenged());
        assertEquals(p1.getChallenger(), p2);
    }

}
