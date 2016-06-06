package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jenny on 6/4/2016.
 */
public class PlaylistItemAdapter extends RecyclerView.Adapter<PlaylistItemAdapter.MyViewHolder> {
    private static final String DEBUG = "PIA_DEBUG";

    private String[] queueQuestions;

    public Activity callback(){
        return null;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView textViewQueueName;
        ImageButton buttonMore;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewQueueName = (TextView) itemView.findViewById(R.id.playlist_item);
            this.buttonMore = (ImageButton) itemView.findViewById(R.id.button_more);
            //this.buttonMore.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
            Log.d(DEBUG, "Clicked context menu");
            menu.add(0, v.getId(), 0, "Edit");
            menu.add(0, v.getId(), 0, "Delete");
            menu.add(0, v.getId(), 0, "Add to Playlist");
        }

//        @Override
//        public boolean onContextItemSelected(MenuItem item) {
//
//            switch(item.getItemId()) {
//                case 0: Toast.makeText(, "Item selected 1", Toast.LENGTH_SHORT).show();
//
//                    break;
//                case 1: Toast.makeText(this, "Item selected 2", Toast.LENGTH_SHORT).show();
//
//                    break;
//                default:
//
//            }
//
//            return true;
//        }
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

        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), );
//                intent.putExtra("questions", queueQuestions[listPosition]);
//                intent.putExtra("question_pos", listPosition);
//                v.getContext().startActivity(intent);
//                //v.getContext().

                Bundle bundle = new Bundle();
                PlaylistItemDialog dialog = new PlaylistItemDialog();
                bundle.putString("question", queueQuestions[listPosition]);
                bundle.putInt("position", listPosition);
                bundle.putStringArray("questionList", queueQuestions);
                dialog.setArguments(bundle);
                Activity activity = (Activity) v.getContext();

                Toast.makeText(
                activity,
                "clicked",
                Toast.LENGTH_SHORT
                ).show();

                dialog.show(activity.getFragmentManager(), "dialog");

            }
        });



    }
}
