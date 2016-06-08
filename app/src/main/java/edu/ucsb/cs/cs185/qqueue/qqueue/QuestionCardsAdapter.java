package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Jenny on 6/3/2016.
 */
public class QuestionCardsAdapter extends RecyclerView.Adapter<QuestionCardsAdapter.MyViewHolder> {
    private final int TYPE_SERIOUS_NSFW = 0;
    private final int TYPE_NSFW = 1;
    private final int TYPE_SERIOUS = 2;
    private final int TYPE_NORMAL = 3;

    private String[] questions;
    boolean useMainLayout;
    int questionType;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQuestion;
        CardView cardView;
        LinearLayout cll;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewQuestion = (TextView) itemView.findViewById(R.id.text_view_question);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);
            this.cll = (LinearLayout) itemView.findViewById(R.id.card_linear_layout);
        }
    }
    public QuestionCardsAdapter(String[] questions, boolean useMainLayout, int questionsType) {
        this.questions = questions;
        this.useMainLayout = useMainLayout;
        this.questionType = questionsType;
    }

    public int getItemCount() {
        return questions.length;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(useMainLayout) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_layout, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.second_card_layout, parent, false);
        }
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewQuestion = holder.textViewQuestion;
        CardView cardView = holder.cardView;
        LinearLayout cll = holder.cll;
        textViewQuestion.setText(questions[listPosition]);
        Typeface font = Typeface.createFromAsset(textViewQuestion.getResources().getAssets(), "opensans.ttf");
        textViewQuestion.setTypeface(font);
        if (useMainLayout) {

            switch (questionType) {
                case TYPE_NORMAL:
                    cardView.setCardBackgroundColor(cardView.getContext().getResources().getColor(R.color.colorNormalCards));
//                    textViewQuestion.setTextColor(cardView.getContext().getResources().getColor(R.color.colorNormalCards));
                    break;
                case TYPE_NSFW:
                    cardView.setCardBackgroundColor(cardView.getContext().getResources().getColor(R.color.colorNSFWCards));
//                    textViewQuestion.setTextColor(cardView.getContext().getResources().getColor(R.color.colorNSFWCards));
                    break;
                case TYPE_SERIOUS:
                    cardView.setCardBackgroundColor(cardView.getContext().getResources().getColor(R.color.colorSeriousCards));
//                    textViewQuestion.setTextColor(cardView.getContext().getResources().getColor(R.color.colorSeriousCards));
                    break;
                default:
                    cardView.setCardBackgroundColor(cardView.getContext().getResources().getColor(R.color.colorNormalCards));
//                    textViewQuestion.setTextColor(cardView.getContext().getResources().getColor(R.color.colorNormalCards));
                    break;
            }
       }
    }
}
