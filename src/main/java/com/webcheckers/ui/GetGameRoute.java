package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import spark.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import static spark.Spark.halt;


/**
 *The UI controller to post the game route.
 * @author Tony Jiang
 * @author Ethan Yi
 * @author Aubrey Tarmu
 *
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(com.webcheckers.ui.PostSigninRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby lobby;
    private final GameCenter gameCenter;
    private final Gson gson;
    private BoardView boardView;
    private final Map<String, Object> modeOptions = new HashMap<>(2);

    String gameID;
    static int totalGames=1;
    public GetGameRoute(final TemplateEngine templateEngine, final Gson gson, final GameCenter gameCenter) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        this.gson = gson;
        this.gameCenter = gameCenter;
        this.lobby = gameCenter.getLobby();
        LOG.config("GetGameRoute is initialized.");
    }

    public enum mode {
        PLAY,
        SPECTATOR,
        REPLAY
    }
    /**
     * Render the WebCheckers Game page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the game page
     */

    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");
        final Session session = request.session();
        final Player player = session.attribute(GetHomeRoute.CURRENT_PLAYER);
        gameID = player.getGameID();
        if (player!=null&&player.isChallenged()==false){//make new checkers
            String otherString = request.queryParams("challenger");
            final Player otherPlayer = lobby.getPlayer(otherString);
            session.attribute(GetHomeRoute.IN_GAME, null);


            if (otherPlayer.isChallenged()){
                player.setID(otherPlayer.getGameID());
                session.attribute(GetHomeRoute.IN_GAME,"true");
                response.redirect(WebServer.SPECTATE_URL);
                halt();
                return null;
            }
            if(player.getGameID()==null) {
                totalGames++;
                gameID = Integer.toString(totalGames);

            }
            CheckersGame temp = new CheckersGame(gameID,player, otherPlayer);

            gameCenter.addGame(temp);
            gameCenter.setGameId((gameID));
            //TODO make gameView synchronous, figure out iterator
            player.setID(gameID);
            otherPlayer.setID(gameID);
            player.setChallenged(true, otherPlayer);
            otherPlayer.setChallenged(true,player);

        }
        final CheckersGame checkersGame = gameCenter.getGame(gameID);

        if (checkersGame.getRedPlayer().equals(player)){
            boardView = new BoardView(checkersGame.getBoard(), Piece.Color.RED);

        }
        else{
            boardView = new BoardView(checkersGame.getBoard(), Piece.Color.WHITE);

        }
        final Player opponent = player.getChallenger();
        if (opponent.isResign()) {
            modeOptions.put("isGameOver", true);
            modeOptions.put("gameOverMessage", opponent.getName() + " has resigned");
            //gameCenter.removeGame(player.getGameID());
            opponent.setResign(false);
            player.setChallenged(false,null);
            player.setID(null);
        }

        if (checkersGame.gameState()!= CheckersGame.GameOver.ONGOING){
            modeOptions.put("isGameOver", true);
            if (checkersGame.gameState()== CheckersGame.GameOver.RED_WIN){
                if (checkersGame.getRedPlayer().equals(player)){
                    modeOptions.put("gameOverMessage", "You have won!");
                }
                else{
                    modeOptions.put("gameOverMessage", "You have lost.");
                }
            }
            else{
                if (checkersGame.getRedPlayer().equals(player)){
                    modeOptions.put("gameOverMessage", "You have lost.");
                }
                else{
                    modeOptions.put("gameOverMessage", "You have won!");
                }
            }
            // HAVENT REMOVED GAME FROM GAMECENTER
            player.setChallenged(false,null);
            player.setID(null);
        }


        Map<String, Object> vm = new HashMap<>();
        vm.put("title","Checkers");
        vm.put("gameID", gameID);
        vm.put("currentUser",player);
        vm.put("board", boardView);
        vm.put("viewMode",mode.PLAY);
        vm.put("modeOptionsAsJSON", gson.toJson(modeOptions));
        vm.put("redPlayer", checkersGame.getRedPlayer());
        vm.put("whitePlayer", checkersGame.getWhitePlayer());
        vm.put("activeColor", checkersGame.getActiveColor());

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));

    }
}