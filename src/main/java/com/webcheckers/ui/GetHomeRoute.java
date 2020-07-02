package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.model.GameView;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

   static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
   static final Message OTHER_PLAYERS_MSG = Message.info("Select a player to start a game");
  static final String VIEW_NAME = "home.ftl";
  static final String CURRENT_PLAYER = "currentPlayer";
  static final String OTHER_PLAYERS = "names";

  private final TemplateEngine templateEngine;


  private final PlayerLobby lobby;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, final PlayerLobby lobby) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.lobby = lobby;
    LOG.config("GetHomeRoute is initialized.");
  }

  public enum mode {
    PLAY,
    SPECTATOR,
    REPLAY
  }
  public PlayerLobby getLobby() {
    return lobby;
  }


  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */

  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    //
    final Session session = request.session();

    Map<String, Object> vm = new HashMap<>();
    Object title = vm.put("title", "Welcome!");

    vm.put("numPlayers", lobby.getNumPlayers());
    final Player player = session.attribute(CURRENT_PLAYER);

    if(player != null) { // displays opponents
        vm.remove("message", WELCOME_MSG);
        vm.put("message", OTHER_PLAYERS_MSG);
        vm.put(OTHER_PLAYERS, lobby.getPlayers().keySet());
        vm.put("currentUser", player);

    } else {
      vm.put("message", WELCOME_MSG);
    }

    if(player!=null&&player.isChallenged()){ // if challenged, render gameboard

      Map<String, Object> map = new HashMap<>();
      Player challenger = player.getChallenger();
      GameView board =new GameView(challenger, player, Piece.Color.WHITE);
      player.setColor(Piece.Color.WHITE);

      map.put("board", board);

      map.put("currentUser",player);
      map.put("title", "Checkers");
      map.put("gameID","1");
      map.put("redPlayer",challenger);
      map.put("whitePlayer",player);
      map.put("activeColor","white");
      map.put("viewMode",mode.PLAY);

      return templateEngine.render(new ModelAndView(map , "game.ftl"));
    }
    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
