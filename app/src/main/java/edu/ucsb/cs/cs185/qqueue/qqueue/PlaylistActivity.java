package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class PlaylistActivity extends BaseActivity {
    private final String DEBUG = "PA_DEBUG";

    private String queueName;
    private String[] queueQuestions;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        setupNavigationDrawer();

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                queueName = extras.getString("playlist_name");
                queueQuestions = extras.getStringArray("playlist_questions");

                Log.d(DEBUG, queueName);
                Log.d(DEBUG, queueQuestions[0]);
            }
        }

        getSupportActionBar().setTitle(queueName);

        recyclerView = (RecyclerView) findViewById(R.id.playlist_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new PlaylistItemAdapter(queueQuestions);
        recyclerView.setAdapter(adapter);
    }
}
