package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jenny on 6/4/2016.
 */
public class PlaylistItemAdapter extends RecyclerView.Adapter<PlaylistItemAdapter.MyViewHolder> {
    private final String DEBUG = "PIA_DEBUG";

    private String[] queueQuestions;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQueueName;
        ImageButton buttonMore;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewQueueName = (TextView) itemView.findViewById(R.id.playlist_item);
            this.buttonMore = (ImageButton) itemView.findViewById(R.id.button_more);
        }
    }

    public PlaylistItemAdapter(String[] queueQuestions) {
        this.queueQuestions = queueQuestions;
    }

    public int getItemCount() {
        return this.queueQuestions.length;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewQueueName = holder.textViewQueueName;
        ImageButton buttonMore = holder.buttonMore;

        textViewQueueName.setText(queueQuestions[listPosition]);

        buttonMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Log.d(DEBUG, "More clicked");
            }
        });
    }
}
