package com.webcheckers.ui;

import com.google.gson.Gson;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.List;

/**
 * The UI controller that allows the user to backup their moves.
 */
public class PostBackupMove implements Route {

    private final Gson gson;
    private final GameCenter gameCenter;
    private Message m;
    public PostBackupMove(final Gson gson, final GameCenter gameCenter){
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    /**
     *
     * @param request Object used to pass state.
     * @param response Object used to pass state.
     * @return
     */
    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();
        Player player = session.attribute("currentPlayer");
        CheckersGame game = gameCenter.getGame(player.getGameID());
        Board board = game.getBoard();
        Move lastMove = board.getLastMove();

        if(!lastMove.isJumpMove()) {
            lastMove.reverseMove();
            board.MovePiece(lastMove);
        } else {
            List<Position> positions = board.getPositions();
            List<PiecePair> captured = board.getCaptured();
            Position lastPosition = positions.get(positions.size() - 1);

            if (board.getLastReplaced() != null) {
                board.removePiece(board.getLastReplaced());
            }

            Piece lastPiece = board.getLastMovedPiece();
            board.removePiece(lastMove.getEnd());
            board.placePiece(lastPosition, lastPiece);
            board.setLastReplaced(lastPosition);

            positions.remove(positions.size() - 1);
            captured.remove(captured.size() - 1);
            if(captured.size() == 0) {
                board.setLastReplaced(null);
            }

            if(captured.size() == 0) {
                board.setLastReplaced(null);
            }
            board.setFlag(true);
        }



        Boolean backupSuccess = true;
        if (backupSuccess){
            board.printBoard();
            m = Message.info("Backup successful");
        }
        else{
            m = Message.error("Backup unsuccessful");
        }

        return gson.toJson(m);
    }

}
