package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.GameView;
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
        playerLobby = new PlayerLobby();
        playerLobby.addPlayer("p");
        playerLobby.addPlayer("c");
        CuT = new PostSignOutRoute(engine,playerLobby);
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
        Player c =playerLobby.getPlayer("c");
        p.setChallenged(true,c);
        c.setChallenged(true,p);
        assertEquals(p.getChallenger(),c);
        assertEquals(c.getChallenger(),p);
        GameView board =new GameView(Piece.Color.RED);
        assertNotNull(board);


        p= session.attribute(CURRENT_PLAYER);
        testHelper.assertViewModelAttribute("numPlayers",playerLobby.getNumPlayers());
        assertEquals(GetHomeRoute.mode.PLAY, GetHomeRoute.mode.valueOf(String.valueOf(GetHomeRoute.mode.PLAY)));
        assertEquals(GetHomeRoute.mode.SPECTATOR,GetHomeRoute.mode.valueOf(String.valueOf(GetHomeRoute.mode.SPECTATOR)));
        assertEquals(GetHomeRoute.mode.REPLAY,GetHomeRoute.mode.valueOf(String.valueOf(GetHomeRoute.mode.REPLAY)));


        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        testHelper.assertViewModelAttribute("message", GetHomeRoute.WELCOME_MSG);
        if(p!=null&&p.isChallenged()){
            testHelper.assertViewModelAttribute("board",board);

            testHelper.assertViewModelAttribute("gameID", "1");
            testHelper.assertViewModelAttribute("currentUser", p);
            testHelper.assertViewModelAttribute("redPlayer", c);
            testHelper.assertViewModelAttribute("activeColor", "white");

            testHelper.assertViewModelAttribute("viewMode", GetHomeRoute.mode.PLAY);
            testHelper.assertViewName("game.ftl");

        }
        testHelper.assertViewName(GetHomeRoute.VIEW_NAME);

    }
}