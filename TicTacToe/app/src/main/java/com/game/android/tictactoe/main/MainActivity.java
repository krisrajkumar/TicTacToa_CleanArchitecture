package com.game.android.tictactoe.main;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.game.android.tictactoe.R;

import java.util.ArrayList;
import java.util.List;

import com.game.android.tictactoe.main.common.BaseActivity;
import util.AppUtils;

public class MainActivity extends BaseActivity implements MainView, GameAdapter.OnItemClickListener {
    private static final int COL = 3;

    private MainPresenter presenter;

    private TextView player1TextView;
    private TextView player2TextView;
    private TextView player1StateTextView;
    private TextView player2StateTextView;
    private RecyclerView gameRecyclerView;

    private List<Character> gameItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);

        player1TextView = (TextView) findViewById(R.id.player1_textview);
        player2TextView = (TextView) findViewById(R.id.player2_textview);
        player1StateTextView = (TextView) findViewById(R.id.player1state_textview);
        player2StateTextView = (TextView) findViewById(R.id.player1state_textview);

        gameRecyclerView = (RecyclerView) findViewById(R.id.game_recyclerview);
        gameRecyclerView.setHasFixedSize(true);
        gameRecyclerView.setLayoutManager(new GridLayoutManager(this, COL));
        gameItemList = new ArrayList<>();
        gameRecyclerView.setAdapter(new GameAdapter(this, gameItemList));

        OnClickListener clickListener = new OnClickListener();
//        findViewById(R.id.start_button).setOnClickListener(clickListener);
        findViewById(R.id.stop_button).setOnClickListener(clickListener);
        presenter.onInitRequested();
    }

    @Override
    public void updatePlayer1Style() {
        player1TextView.setTextColor(Color.RED);
        player1TextView.setTypeface(null,Typeface.BOLD);
    }

    @Override
    public void updatePlayer2Style() {
        player2TextView.setTextColor(Color.RED);
        player2TextView.setTypeface(null,Typeface.BOLD);
    }

    @Override
    public void resetPlayer1Style() {
        player1TextView.setTextColor(Color.BLACK);
        player1TextView.setTypeface(null,Typeface.NORMAL);
    }

    @Override
    public void resetPlayer2Style() {
        player2TextView.setTextColor(Color.BLACK);
        player2TextView.setTypeface(null, Typeface.NORMAL);
    }

    @Override
    public void showGameStartedMsg() {
        AppUtils.displayLongSnackBar(findViewById(android.R.id.content), getString(R.string.msg_game_start, getString(R.string.label_player1)));
    }

    @Override
    public void showInvalidMoveErr() {
        AppUtils.displayLongSnackBar(findViewById(android.R.id.content), R.string.error_invalid_step);
    }

    @Override
    public void showResultMsg(int player) {
        Snackbar snackbar = AppUtils.displayIndefiniteSnackBar(findViewById(android.R.id.content), getString(R.string.msg_result, getString(player)));
        snackbar.setAction(R.string.action_ok,new OnClickListener());
        snackbar.show();
    }

    @Override
    public void updatePlayer1State(int player, int win, int lose) {
        String msg = getString(R.string.msg_player_state, getString(player), win, lose);
        player1StateTextView.setText(msg);
    }

    @Override
    public void updatePlayer2State(int player, int win, int lose) {
        String msg = getString(R.string.msg_player_state, getString(player), win, lose);
        player2StateTextView.setText(msg);
    }

    @Override
    public void onItemClicked(int pos) {
        presenter.onMoveRequested(pos);
    }

    @Override
    public void populateGameList(List<Character> gameItems) {
        if (gameItemList != null) {
            gameItemList.clear();
            gameItemList.addAll(gameItems);
            gameRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /*case R.id.start_button:
                    presenter.onStartRequested();
                    break;*/
                case R.id.stop_button:
                    presenter.onStopRequested();
                    break;
            }
        }
    }
}
