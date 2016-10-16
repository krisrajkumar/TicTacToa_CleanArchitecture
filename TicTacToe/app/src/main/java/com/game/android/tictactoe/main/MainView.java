package com.game.android.tictactoe.main;

import java.util.List;

import com.game.android.tictactoe.main.common.BaseView;

/**
 * View for Main.
 */
public interface MainView extends BaseView {
    void populateGameList(List<Character> gameList);

    void showInvalidMoveErr();

    void showResultMsg(int player);

    void updatePlayer1Style();

    void updatePlayer2Style();

    void resetPlayer1Style();

    void resetPlayer2Style();

    void showGameStartedMsg();

    void updatePlayer1State(int player, int win, int loss);

    void updatePlayer2State(int player, int win, int loss);
}
