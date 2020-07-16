package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostCheckTurnTest {
        private Request request;
        private Session session;
        private TemplateEngine engine;
        private Response response;
        private GameCenter gameCenter;
        private PlayerLobby lobby;
        private PostCheckTurn CuT;
        static final String CURRENT_PLAYER = "currentPlayer";
        Player currentPlayer;
        private Gson gson;

        @BeforeEach
        void setup() {
            request = mock(Request.class);
            session = mock(Session.class);
            when(request.session()).thenReturn(session);
            response = mock(Response.class);
            engine = mock(TemplateEngine.class);
            gameCenter = new GameCenter();
            lobby = gameCenter.getLobby();
            lobby.addPlayer("p");
            lobby.addPlayer("c");
            currentPlayer=  request.session().attribute(GetHomeRoute.CURRENT_PLAYER);
            currentPlayer = new Player("x");
//        request.session().attribute(GetHomeRoute.CURRENT_PLAYER,currentPlayer);
            GetHomeRoute getHomeRoute = new GetHomeRoute(engine,gameCenter);

            getHomeRoute.handle(request,response);

            Player c =session.attribute(CURRENT_PLAYER);


            System.out.println(c);
            CuT = new PostCheckTurn(gson,gameCenter);
            assertEquals(request.session(),session);

        }
        @Test
        void newSession(){
            CuT.handle(request,response);
        }

    }