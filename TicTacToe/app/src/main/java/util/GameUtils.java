package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility for Game.
 */
public class GameUtils {
    public static final int EVEN_NUM_DIVISOR = 2;
    public static final char PLAYER1 = 'x';
    public static final char PLAYER2 = 'o';

    public static final char EMPTY_CONTENT = '\0';

    public static final Map<Integer, Character> PLAYER_MAP;

    static {
        PLAYER_MAP = new HashMap<>();
        PLAYER_MAP.put(0, PLAYER1);
        PLAYER_MAP.put(1, PLAYER2);
    }

    public static boolean isAlreadyPlayedPos(char[][] gameArray, int col, int row) {
        return gameArray[col][row] != EMPTY_CONTENT;
    }

    public static boolean isEvenMove(int value) {
        return value % EVEN_NUM_DIVISOR == 0;
    }

    /**
     * Returns the matrix equivalent coordinates for the give array position.
     *
     * @param pos The matrix coordinates.
     */
    public static int getMatrixRowCoordinates(int pos, int row) {
        return pos / row;
    }

    public static int getMatrixColCoordinates(int pos, int col) {
        return pos % col;
    }
}
