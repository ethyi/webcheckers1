package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    GameView boardView = new GameView(Piece.Color.RED);


    final Validator validator = new Validator();
    String data1 ="{\"start\":{\"row\":0,\"cell\":0},\"end\":{\"row\":1,\"cell\":1}}";
    String data2 ="{\"start\":{\"row\":5,\"cell\":6},\"end\":{\"row\":1,\"cell\":7}}";
    String data3 ="{\"start\":{\"row\":1,\"cell\":1},\"end\":{\"row\":3,\"cell\":3}}";

    Move goodMove = new Move(data1);
    Move badMove = new Move(data2);
    Move jumpMove = new Move(data3);




    @Test
    public void nullTest() {
        assertNotNull(validator, "validator is not null");
    }

    @Test
    public void moveTestGood() {

        assertEquals(validator.validateMove(boardView.getBoard(), goodMove), "True");
    }

    @Test
    public void moveTestBad() {
        assertEquals(validator.validateMove(boardView.getBoard(), badMove), "False");
    }

    @Test
    public void jumpTest() {
        assertEquals(validator.validateMove(boardView.getBoard(), badMove), "True");
    }



}
