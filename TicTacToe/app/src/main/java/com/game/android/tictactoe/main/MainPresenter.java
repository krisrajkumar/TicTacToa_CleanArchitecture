package com.game.android.tictactoe.main;

import com.game.android.tictactoe.main.common.BasePresenter;

/**
 * Presenter for Main.
 */
public interface MainPresenter extends BasePresenter {
    void onInitRequested();

    void onMoveRequested(int pos);

    void onStartRequested();

    void onStopRequested();
}
