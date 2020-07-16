package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
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

class GetGameRouteTest {
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private GetGameRoute CuT;
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
        currentPlayer=  request.session().attribute(GetHomeRoute.CURRENT_PLAYER);
        currentPlayer = new Player("x");
//        request.session().attribute(GetHomeRoute.CURRENT_PLAYER,currentPlayer);
        GetHomeRoute getHomeRoute = new GetHomeRoute(engine,gameCenter);

        getHomeRoute.handle(request,response);

        Player c =session.attribute(CURRENT_PLAYER);


        System.out.println(c);
        CuT = new GetGameRoute(engine,gameCenter);
        assertEquals(request.session(),session);

    }

    @Test
    public void newSession(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
//

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


//        assertEquals(CuT.getLobby(),playerLobby);
        p= session.attribute(CURRENT_PLAYER);
        testHelper.assertViewModelAttribute("currentPlayer",p);
        assertEquals(GetGameRoute.mode.PLAY, GetGameRoute.mode.valueOf(String.valueOf(GetHomeRoute.mode.PLAY)));
        assertEquals(GetGameRoute.mode.SPECTATOR,GetGameRoute.mode.valueOf(String.valueOf(GetHomeRoute.mode.SPECTATOR)));
        assertEquals(GetGameRoute.mode.REPLAY,GetGameRoute.mode.valueOf(String.valueOf(GetHomeRoute.mode.REPLAY)));
//
//
//        // Analyze the results:
//        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
//        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title", "Welcome!");
        if(p!=null&&p.isChallenged()){
            testHelper.assertViewModelAttribute("board",board);

            testHelper.assertViewModelAttribute("gameID", "1");
            testHelper.assertViewModelAttribute("currentUser", p);
            testHelper.assertViewModelAttribute("redPlayer", c);
            testHelper.assertViewModelAttribute("activeColor", "white");

            testHelper.assertViewModelAttribute("viewMode", GetGameRoute.mode.PLAY);
            testHelper.assertViewName("game.ftl");

        }

    }
}