package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BrowseActivity extends BaseActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<BrowseItem> browseItems;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_browse);
            setupNavigationDrawer();

        recyclerView = (RecyclerView) findViewById(R.id.browse_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        browseItems = new ArrayList<BrowseItem>();
        for (int i = 0; i < MyData.browseQueueNames.length; i++) {
            browseItems.add(new BrowseItem(
                    MyData.browseQueueNames[i],
                    MyData.questions
            ));
        }

        adapter = new ItemAdapter(browseItems);
        recyclerView.setAdapter(adapter);


        }

}
