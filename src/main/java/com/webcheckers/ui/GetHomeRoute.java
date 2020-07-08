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

import static spark.Spark.halt;

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
  static final Message INGAME_MSG = Message.info("The Opponent you selected is already in a game");
  static final Message NO_PLAYERS = Message.info("There are no players to challenge");
  static final String VIEW_NAME = "home.ftl";
  static final String CURRENT_PLAYER = "currentPlayer";
  static final String OTHER_PLAYERS = "names";
  static final String IN_GAME = "ingame";

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
    final Player player = session.attribute(CURRENT_PLAYER);
    String ingame = session.attribute(IN_GAME);
    vm.put("title", "Welcome!");

    if (player==null){
      vm.put("message", WELCOME_MSG);
      vm.put("numPlayers", lobby.getNumPlayers());
    }

    else{
      vm.put(OTHER_PLAYERS, lobby.getPlayers().keySet());
      vm.put("currentUser", player);
      vm.put("message", OTHER_PLAYERS_MSG);
      if (lobby.getNumPlayers()==1){
        vm.put("message",NO_PLAYERS);
      }

      if(ingame!=null){
        //condition when player challenges an opponent ingame
        if(ingame.equals("true")){
          vm.put("message",INGAME_MSG);
        }
      }

      if(player.isChallenged()){
        //condition when player is challenged
        response.redirect(WebServer.GAME_URL);
        halt();
        return null;
      }
    }
    // render the View
    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }
}
