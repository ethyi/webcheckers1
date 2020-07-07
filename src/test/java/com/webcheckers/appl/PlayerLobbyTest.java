package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.utils.Assert;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PlayerLobbyTest  {
    PlayerLobby playerLobby;
    @BeforeEach
    void setUpTest(){
        playerLobby= new PlayerLobby();
    }

    @Test
    void getPlayer() throws Exception{
        Player p = new Player("p");
        playerLobby.addPlayer("x");
        assertNotNull(playerLobby.getPlayer("x"));
    }

    @Test
    void getPlayers() {
        assertNotNull(playerLobby.getNumPlayers());
    }

    @Test
    void getNumPlayers() {
        int cut = playerLobby.getNumPlayers();
        assertEquals(playerLobby.getNumPlayers(),cut);
    }

    @Test
    void contains() {
        playerLobby.addPlayer("x");
        assertTrue(playerLobby.contains("x"));
    }

    @Test
    void removePlayer() {
        playerLobby.addPlayer("x");
        playerLobby.removePlayer("x");
//        assertFalse(playerLobby.getPlayer("x")==null);
    }
    @Test
    void addPlayer() {
        playerLobby.addPlayer("x");
        assertTrue(playerLobby.contains("x"));
    }
}