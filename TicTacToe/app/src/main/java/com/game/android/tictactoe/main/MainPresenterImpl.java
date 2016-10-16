package com.game.android.tictactoe.main;

import android.support.annotation.NonNull;

import com.game.android.tictactoe.R;

import java.util.List;

import simple_engine.GameController;
import simple_engine.GameInteractor;
import util.GameUtils;

/**
 * Presenter Implementation for Main.
 */
public class MainPresenterImpl implements MainPresenter, GameController.GameResponseHandler {
    private MainView view;
    private GameInteractor gameInteractor;

    public MainPresenterImpl(@NonNull MainView view) {
        this.view = view;
        gameInteractor = new GameController(this);
    }

    @Override
    public void onInitRequested() {
        gameInteractor.init();
        beginGame();
    }

    @Override
    public void onMoveRequested(int pos) {
        gameInteractor.makePosAsVisited(pos);
    }

    @Override
    public void beginGame() {
        view.updatePlayer1Style();
        view.showGameStartedMsg();
    }

    @Override
    public void onLoadContent(List<Character> contentList) {
        view.populateGameList(contentList);
    }

    @Override
    public void invalidMove() {
        view.showInvalidMoveErr();
    }

    @Override
    public void resetPlayer2() {
        view.resetPlayer2Style();
    }

    @Override
    public void playerMoves(boolean isEven) {
        if (isEven) {
            view.updatePlayer2Style();
            view.resetPlayer1Style();
        } else {
            view.updatePlayer1Style();
            view.resetPlayer2Style();
        }
    }

    @Override
    public void declareResult(char player, int win,int lose) {
        int msg = R.string.label_player1;
        if (player == GameUtils.PLAYER2) {
            msg = R.string.label_player2;
            view.updatePlayer2State(msg, win, lose);
        } else {
            view.updatePlayer1State(msg, win, lose);
        }
        view.showResultMsg(msg);
    }

//    @Override
//    public void showActivePlayer(String player) {
//        view.updatePlayer1Style();
//        view.showGameStartedMsg();
//    }

    @Override
    public void onStartRequested() {
        gameInteractor.startGame();
    }

    @Override
    public void onStopRequested() {
        gameInteractor.resetGame();
    }
}
