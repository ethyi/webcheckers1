package com.webcheckers.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {
    BoardView boardView = new BoardView(new Board(),Piece.Color.RED);
    final Validator validator = new Validator(new Board());
    Board board = validator.getBoard();




    @Test
    public void nullTest() {
        assertNotNull(validator, "validator is not null");
    }

    @Test
    public void moveTestGood() {
        System.out.println(board.getSpace(new Position(5, 2)).getPiece());
        String data1 = "{\"start\":{\"row\":5,\"cell\":2},\"end\":{\"row\":4,\"cell\":3}}";
        Move goodMove =  new Move(data1);
        assertEquals(validator.validateMove(goodMove), Validator.moveType.VALID);
    }

    @Test
    public void moveTestBad() {
        String data1 = "{\"start\":{\"row\":5,\"cell\":2},\"end\":{\"row\":4,\"cell\":7}}";
        Move badMove = new Move(data1);
        assertEquals(validator.validateMove(badMove), Validator.moveType.INVALID);
    }

    @Test
    public void jumpTest() {
        String data1 = "{\"start\":{\"row\":5,\"cell\":2},\"end\":{\"row\":4,\"cell\":3}}";
        String data2 = "{\"start\":{\"row\":2,\"cell\":5},\"end\":{\"row\":3,\"cell\":4}}";
        String data3 = "{\"start\":{\"row\":4,\"cell\":3},\"end\":{\"row\":2,\"cell\":5}}";

        Move move1 = new Move(data1);
        Move move2 = new Move(data2);
        board.MovePiece(move1);
        board.MovePiece(move2);
        Move move3 = new Move(data3);

        assertEquals(validator.validateMove(move3), Validator.moveType.VALID);
    }

    @Test
    public void backwardsTest() {
        String data1 = "{\"start\":{\"row\":5,\"cell\":2},\"end\":{\"row\":4,\"cell\":3}}";
        String data2 = "{\"start\":{\"row\":2,\"cell\":5},\"end\":{\"row\":3,\"cell\":6}}";
        String data3 = "{\"start\":{\"row\":4,\"cell\":3},\"end\":{\"row\":5,\"cell\":2}}";

        Move move1 = new Move(data1);
        Move move2 = new Move(data2);
        Move move3 = new Move(data3);
        board.MovePiece(move1);
        board.MovePiece(move2);

        assertEquals(validator.validateMove(move3), Validator.moveType.WRONG_DIRECTION);

    }

    @Test
    public void forceJumpTest() {
        String data1 = "{\"start\":{\"row\":5,\"cell\":2},\"end\":{\"row\":4,\"cell\":3}}";
        String data2 = "{\"start\":{\"row\":2,\"cell\":5},\"end\":{\"row\":3,\"cell\":4}}";
        String data3 = "{\"start\":{\"row\":4,\"cell\":3},\"end\":{\"row\":3,\"cell\":2}}";
        Move move1 = new Move(data1);
        Move move2 = new Move(data2);
        Move move3 = new Move(data3);
        board.MovePiece(move1);
        board.MovePiece(move2);

        assertEquals(validator.validateMove(move3), Validator.moveType.NEED_TO_JUMP);

    }
    @Test
    public void colorTest() {
        assertEquals(validator.getActiveColor(), Piece.Color.RED);
        validator.switchActiveColor();
        assertEquals(validator.getActiveColor(), Piece.Color.WHITE);
        validator.switchActiveColor();
        assertEquals(validator.getActiveColor(), Piece.Color.RED);

    }



}
