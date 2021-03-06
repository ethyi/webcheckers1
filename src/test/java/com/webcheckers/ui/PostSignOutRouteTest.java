package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
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

class PostSignOutRouteTest {

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private PostSignOutRoute CuT;
    static final String CURRENT_PLAYER = "currentPlayer";

    @BeforeEach
    void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        gameCenter = new GameCenter();
        playerLobby = gameCenter.getLobby();
        playerLobby.addPlayer("p");
        playerLobby.addPlayer("c");
        CuT = new PostSignOutRoute(engine,gameCenter);
        assertEquals(request.session(),session);

    }

    @Test
    public void newSession(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);
        Player p =playerLobby.getPlayer("p");
        if(p!=null){

        }

        testHelper.assertViewName(GetHomeRoute.VIEW_NAME);

    }
}