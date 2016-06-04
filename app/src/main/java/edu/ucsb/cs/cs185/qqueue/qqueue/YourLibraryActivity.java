package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class YourLibraryActivity extends BaseActivity {


    private final String QUESTIONS = "QUESTIONS";
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<BrowseItem> browseItems;
    private Button buttonUse;
    private Button buttonEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_library);
        setupNavigationDrawer();
        getSupportActionBar().setTitle(R.string.activity_your_library);

        recyclerView = (RecyclerView) findViewById(R.id.yl_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        browseItems = new ArrayList<BrowseItem>();
        for (int i = 0; i < MyData.browseQueueNames.length; i++) {
            browseItems.add(new BrowseItem(
                    MyData.browseQueueNames[i],
                    MyData.questions_3
            ));
        }

        adapter = new ItemAdapter(browseItems, true);
        recyclerView.setAdapter(adapter);
    }
}
