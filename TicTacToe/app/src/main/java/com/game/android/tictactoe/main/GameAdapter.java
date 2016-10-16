package com.game.android.tictactoe.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.game.android.tictactoe.R;

import java.util.List;

/**
 * Adapter for GameView.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private List<Character> items;

    public GameAdapter(OnItemClickListener itemClickListener, List<Character> items) {
        this.itemClickListener = itemClickListener;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = items.get(position);
        holder.itemTextView.setText(String.valueOf(character));
        holder.itemTextView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemTextView;

        public ViewHolder(View view) {
            super(view);
            itemTextView = (TextView) view.findViewById(R.id.item_textview);
            itemTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            itemClickListener.onItemClicked(pos);
        }
    }

    interface OnItemClickListener {
        void onItemClicked(int pos);
    }
}
