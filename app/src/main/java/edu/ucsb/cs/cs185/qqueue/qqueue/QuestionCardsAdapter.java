package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jenny on 6/3/2016.
 */
public class QuestionCardsAdapter extends RecyclerView.Adapter<QuestionCardsAdapter.MyViewHolder> {
    private String[] questions;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewQuestion = (TextView) itemView.findViewById(R.id.text_view_question);
        }
    }
    public QuestionCardsAdapter(String[] questions) {
        this.questions = questions;
    }

    public int getItemCount() {
        return questions.length;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewQuestion = holder.textViewQuestion;
        textViewQuestion.setText(questions[listPosition]);
    }
}
