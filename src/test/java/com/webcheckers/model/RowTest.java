package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    private List<Space> space = new ArrayList<>();
    Row CuT= new Row(0,false, Piece.Color.RED,null);
    @BeforeEach
    void setUp(){
//        for(int i= 0;i<8;i++) {
//              space.add(new Space(i,null,false,CuT));
//        }
    }
    @Test
    void iterator() {
        assertNotNull(CuT.iterator());
    }

    @Test
    void getIndex() {
        assertEquals(0,CuT.getIndex());
    }

    @Test
    void getSpaces() {
        space = CuT.getSpaces();
     assertEquals(space,CuT.getSpaces());
    }
}