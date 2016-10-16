package simple_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.GameUtils;

/**
 * Main game engine responsible for flow of the game.
 */
public class GameController implements GameInteractor {
    private static final int NUMOFCOL = 3;
    private static final int NUMOFROW = 3;
    private int stepsCounter;
    private int totalGamesPlayed;

    private Map<Character, Integer> playerState;

    private GameResponseHandler gameHandler;

    public GameController(GameResponseHandler gameHandler) {
        this.gameHandler = gameHandler;
    }

    private char[][] gameArray;

    @Override
    public void init() {
        gameArray = new char[NUMOFCOL][NUMOFROW];
        gameHandler.onLoadContent(getDatas());
    }

    private List<Character> getDatas() {
        List<Character> arrayList = new ArrayList<>();
        for (int i = 0; i < NUMOFCOL; i++) {
            for (int j = 0; j < NUMOFROW; j++) {
                arrayList.add(gameArray[i][j]);
            }
        }
        return arrayList;
    }

    private boolean traverseCol(int col) {
        char prev = gameArray[0][col];
        for (int i = 1; i < NUMOFROW; i++) {
            char current = gameArray[i][col];
            if (prev != current || current == GameUtils.EMPTY_CONTENT) {
                return false;
            }
        }
        return true;
    }

    private boolean traverseRow(int row) {
        char prev = gameArray[row][0];
        for (int i = 1; i < NUMOFCOL; i++) {
            char current = gameArray[row][i];
            if (prev != current || current == GameUtils.EMPTY_CONTENT) {
                return false;
            }
        }
        return true;
    }

    private boolean traverseLeftDiagonal() {
        int j = 0;
        char prev = gameArray[0][j];
        for (int i = 1; i < NUMOFCOL; i++) {
            j++;
            char current = gameArray[i][j];
            if (prev != current || current == GameUtils.EMPTY_CONTENT) {
                return false;
            }
        }
        return true;
    }

    private boolean traverseRightDiagonal() {
        int i = 0;
        char prev = gameArray[i][NUMOFCOL - 1];
        for (int j = NUMOFCOL - 2; j >= 0; j--) {
            i++;
            char current = gameArray[i][j];
            if (prev != current || current == GameUtils.EMPTY_CONTENT) {
                return false;
            }
        }
        return true;
    }

    private void clearData() {
        for (int i = 0; i < NUMOFCOL; i++) {
            for (int j = 0; j < NUMOFROW; j++) {
                gameArray[i][j] = GameUtils.EMPTY_CONTENT;
            }
        }
        stepsCounter = 0;
    }

    @Override
    public void makePosAsVisited(int pos) {
        int col = GameUtils.getMatrixColCoordinates(pos, NUMOFCOL);
        int row = GameUtils.getMatrixRowCoordinates(pos, NUMOFROW);

        if (GameUtils.isAlreadyPlayedPos(gameArray, row, col)) {
            gameHandler.invalidMove();
        } else {
            boolean isEvenMove = GameUtils.isEvenMove(stepsCounter);
            char player = isEvenMove ? GameUtils.PLAYER_MAP.get(0) : GameUtils.PLAYER_MAP.get(1);
            gameArray[row][col] = player;
            stepsCounter++;
            gameHandler.onLoadContent(getDatas());
            gameHandler.playerMoves(isEvenMove);
            if (traverse(col, row)) {
                if (playerState == null) {
                    playerState = new HashMap<>();
                }

                int numOfMoves = 0;
                if (playerState.size() > 0 && playerState.containsKey(player)) {
                    numOfMoves = playerState.get(player);
                }
                numOfMoves++;
                playerState.put(player, numOfMoves);
                totalGamesPlayed++;
                gameHandler.declareResult(player, numOfMoves, Math.abs(totalGamesPlayed - numOfMoves));
            }
        }
    }

    private boolean traverse(int col, int row) {
        if (traverseCol(col)) {
            return true;
        } else if (traverseRow(row)) {
            return true;
        } else if (traverseLeftDiagonal()) {
            return true;
        } else if (traverseRightDiagonal()) {
            return true;
        }
        return false;
    }

    @Override
    public void startGame() {
        gameHandler.beginGame();
    }

    @Override
    public void resetGame() {
        clearData();
        gameHandler.onLoadContent(getDatas());
        startGame();
        gameHandler.resetPlayer2();
    }

    public interface GameResponseHandler {
        void onLoadContent(List<Character> contentList);

        void beginGame();

        void resetPlayer2();

        void playerMoves(boolean isEven);

        void invalidMove();

        void declareResult(char player, int win, int lose);
    }
}
