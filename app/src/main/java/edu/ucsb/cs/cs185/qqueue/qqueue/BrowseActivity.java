package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

public class BrowseActivity extends BaseActivity {

    private final String QUESTIONS = "QUESTIONS";
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    private ArrayList<BrowseItem> browseItems;
    private Button buttonUse;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        setupNavigationDrawer();
        getSupportActionBar().setTitle(R.string.activity_browse);

        recyclerView = (RecyclerView) findViewById(R.id.browse_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        browseItems = new ArrayList<BrowseItem>();
//        for (int i = 0; i < MyData.browseQueueNames.length; i++) {
//            browseItems.add(new BrowseItem(
//                    MyData.browseQueueNames[i],
//                    MyData.questions_2
//            ));
//        }

        browseItems.add(new BrowseItem(
                    MyData.browseQueueNames[0],
                    MyData.shuffle(MyData.questions_2)
            ));
        browseItems.add(new BrowseItem(
                MyData.browseQueueNames[1],
                MyData.shuffle(MyData.questions_3)
        ));

        browseItems.add(new BrowseItem(
                MyData.browseQueueNames[2],
                MyData.shuffle(MyData.questions_4)
        ));
        browseItems.add(new BrowseItem(
                MyData.browseQueueNames[3],
                MyData.shuffle(MyData.questions_5)
        ));


        adapter = new ItemAdapter(browseItems, false);
        recyclerView.setAdapter(adapter);

    }


}
