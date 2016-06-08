package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jenny on 6/3/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private final String BROWSE_QUESTIONS = "BROWSE_QUESTIONS";
    private final int TYPE_NORMAL = 3;

    private ArrayList<BrowseItem> browseItems;
    private boolean isLibraryActivity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQueueName;
        RecyclerView recyclerView;
        Button buttonUse;
        Button buttonEdit;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewQueueName = (TextView) itemView.findViewById(R.id.browse_queue_name);
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.browse_queue_items);
            this.buttonUse = (Button) itemView.findViewById(R.id.button_use);
            this.buttonEdit = (Button) itemView.findViewById(R.id.button_edit);
        }
    }
    public ItemAdapter(ArrayList<BrowseItem> browseItems, boolean isLibraryActivity) {
        this.browseItems = browseItems;
        this.isLibraryActivity = isLibraryActivity;
    }

    public int getItemCount() {
//        return this.browseItems.size();
        if (this.browseItems == null) {
            return 0;
        }
        else return this.browseItems.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewQueueName = holder.textViewQueueName;
        RecyclerView recyclerViewQuestions = holder.recyclerView;
        Button buttonUse = holder.buttonUse;
        Button buttonEdit = holder.buttonEdit;

        textViewQueueName.setText(browseItems.get(listPosition).getQueueName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerViewQuestions.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewQuestions.setLayoutManager(layoutManager);
        recyclerViewQuestions.setItemAnimator(new DefaultItemAnimator());

        String[] questions = browseItems.get(listPosition).getQueueItems();

        QuestionCardsAdapter adapter = new QuestionCardsAdapter(questions, false, TYPE_NORMAL);
        recyclerViewQuestions.setAdapter(adapter);

        buttonUse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CurrentQueueActivity.class);
                intent.putExtra(BROWSE_QUESTIONS, browseItems.get(listPosition).getQueueItems());
//                intent.putExtra(BROWSE_QUESTIONS, MyData.shuffle(browseItems.get(listPosition).getQueueItems()));

                //
//                Activity temp = (Activity) v.getContext();

                v.getContext().startActivity(intent);
//                temp.overridePendingTransition(0,0);
               //
//                temp.finish();
            }
        });

        if(isLibraryActivity) {
            textViewQueueName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlaylistActivity.class);
                    intent.putExtra("playlist_name", browseItems.get(listPosition).getQueueName());
                    intent.putExtra("playlist_questions", browseItems.get(listPosition).getQueueItems());
//                    v.getContext().startActivity(intent);
                    intent.putExtra("type", "library");
                    Bundle bundle = new Bundle();
                    v.getContext().startActivity(intent, bundle);
                }
            });

//            buttonEdit.setVisibility(View.GONE);
//            buttonEdit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), PlaylistActivity.class);
//                    intent.putExtra("playlist_name", browseItems.get(listPosition).getQueueName());
//                    intent.putExtra("playlist_questions", browseItems.get(listPosition).getQueueItems());
////                    v.getContext().startActivity(intent);
//                    Bundle bundle = new Bundle();
//                    v.getContext().startActivity(intent, bundle);
//                }
//            });

        }

        if(!isLibraryActivity) {
            textViewQueueName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlaylistActivity.class);
                    intent.putExtra("playlist_name", browseItems.get(listPosition).getQueueName());
                    intent.putExtra("playlist_questions", browseItems.get(listPosition).getQueueItems());
//                    v.getContext().startActivity(intent);
                    intent.putExtra("type", "browse");
                    Bundle bundle = new Bundle();
                    v.getContext().startActivity(intent, bundle);
                }
            });
        }
    }
}
