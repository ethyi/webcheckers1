package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetSpectateTest {
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Gson gson;
    private GetSpectate CuT;
    static final String CURRENT_PLAYER = "currentPlayer";
    Player currentPlayer;

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        playerLobby = new PlayerLobby();
        gameCenter = mock(GameCenter.class);
        playerLobby.addPlayer("p");
        playerLobby.addPlayer("c");
        currentPlayer =playerLobby.getPlayer("c");
        currentPlayer=  request.session().attribute(GetHomeRoute.CURRENT_PLAYER);
//        request.session().attribute(GetHomeRoute.CURRENT_PLAYER,currentPlayer);
        GetSpectate getSpectate = new GetSpectate(engine,gameCenter);

        getSpectate.handle(request,response);

        Player c =session.attribute(CURRENT_PLAYER);


        CuT = new GetSpectate(engine, gameCenter);
        assertEquals(request.session(),session);

    }

    @Test
    public void newSession(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
//

        CuT.handle(request, response);
    }
}