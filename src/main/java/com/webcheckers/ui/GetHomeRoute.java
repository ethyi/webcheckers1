package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.model.GameView;
import com.webcheckers.model.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  private static final Message OTHER_PLAYERS_MSG = Message.info("Select a player to start a game");

  static final String CURRENT_PLAYER = "currentPlayer";
  private final String OTHER_PLAYERS = "names";

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
    vm.put("title", "Welcome!");

    vm.put("numPlayers", lobby.getNumPlayers());
    final Player player = session.attribute(CURRENT_PLAYER);
    if(player != null) {
        vm.remove("message", WELCOME_MSG);
        vm.put("message", OTHER_PLAYERS_MSG);
        vm.put(OTHER_PLAYERS, lobby.getPlayers().keySet());
        vm.put("currentUser", player);

    } else {
      vm.put("message", WELCOME_MSG);
    }
    if(player!=null&&player.isChallenged()){

      Map<String, Object> map = new HashMap<>();
      Player challenger = player.getChallenger();
      GameView board =new GameView(challenger, player);
      map.put("board", board);

      map.put("currentUser",player);
      map.put("title", "Checkers");
      map.put("gameID","1");
      map.put("redPlayer",challenger);
      map.put("whitePlayer",player);
      map.put("activeColor","white");
      map.put("viewMode","player");

      return templateEngine.render(new ModelAndView(map , "game.ftl"));
    }
    // render the View
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }
}
