package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

public class YourLibraryActivity extends BaseActivity {


    private final String QUESTIONS = "QUESTIONS";
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    static ArrayList<BrowseItem> libraryItems;
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

        libraryItems = new ArrayList<BrowseItem>();
        for (int i = 0; i < MyData.yourLibraryQueueNames.length; i++) {
            libraryItems.add(new BrowseItem(
                    MyData.yourLibraryQueueNames[i],
                    MyData.questions_3
            ));
        }

        adapter = new ItemAdapter(libraryItems, true);
        recyclerView.setAdapter(adapter);
    }

    static ArrayList<String> getPlaylistNames(){
        ArrayList<String> plItems = new ArrayList<>();
        for (int i = 0; i < libraryItems.size(); i++) {
            plItems.add(libraryItems.get(i).getQueueName());
        }
        return plItems;
    }

}
