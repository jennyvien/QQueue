package edu.ucsb.cs.cs185.qqueue.qqueue;

        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.ArrayList;

/**
 * Created by Jenny on 6/3/2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private ArrayList<BrowseItem> browseItems;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewQueueName;
        RecyclerView recyclerView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewQueueName = (TextView) itemView.findViewById(R.id.browse_queue_name);
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.browse_queue_items);
        }
    }
    public ItemAdapter(ArrayList<BrowseItem> browseItems) {
        this.browseItems = browseItems;
    }

    public int getItemCount() {
        return this.browseItems.size();
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

        textViewQueueName.setText(browseItems.get(listPosition).getQueueName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerViewQuestions.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewQuestions.setLayoutManager(layoutManager);
        recyclerViewQuestions.setItemAnimator(new DefaultItemAnimator());

        String[] questions = MyData.questions;

        QuestionCardsAdapter adapter = new QuestionCardsAdapter(questions);
        recyclerViewQuestions.setAdapter(adapter);
    }
}
