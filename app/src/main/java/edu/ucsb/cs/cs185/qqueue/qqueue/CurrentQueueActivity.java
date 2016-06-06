package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ScrollingView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import 	android.support.v7.widget.RecyclerView;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

// https://github.com/plattysoft/SnappingList/blob/master/SnappingList/src/main/java/com/plattysoft/snappinglist/MainActivity.java
public class CurrentQueueActivity extends BaseActivity {

    private final String BROWSE_QUESTIONS = "BROWSE_QUESTIONS";
    private final String SAVED_QUESTIONS = "SAVED_QUESTIONS";
    private final String PREFERENCES = "PREFERENCES";
    private final String DEBUG = "CQA_DEBUG";
    private static final String BUNDLE_LIST_PIXELS = "allPixels";


    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private String[] questions;

    private int currentPosition;
    private int finalPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(DEBUG, "in onCreate");
        setContentView(R.layout.activity_card_queue);
        getSupportActionBar().setTitle(R.string.activity_current_queue);

        setupNavigationDrawer();
        resetQuestions();
        setupRecyclerView();

        Button viewQueue = ( Button )findViewById(R.id.viewQueue);
        viewQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YourQueueFragment yourQueue = new YourQueueFragment();
                yourQueue.setQueueListener(new YourQueueFragment.setQueueListener() {
                    @Override
                    public void onQueueSet() {

                    }
                });
                yourQueue.show(getFragmentManager(), "yourQueue");
            }
        });

        Button temp = (Button) findViewById(R.id.temp);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(1);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch(newState) {
                    case RecyclerView.SCROLL_STATE_IDLE :
                        Log.d(DEBUG, "IDLE");
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        Log.d(DEBUG, "DRAGGING");
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        Log.d(DEBUG, "SETTLING");
                        recyclerView.smoothScrollToPosition(1);
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(DEBUG, "onScrolled " + dx);

            }
        });
        adapter = new QuestionCardsAdapter(questions);
        recyclerView.setAdapter(adapter);
    }

    private void resetQuestions() {
        // If has incoming queue
        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                questions = intent.getExtras().getStringArray(BROWSE_QUESTIONS);
            }
        }

        // Otherwise it is initial launch so use default queue
        if(questions == null) {
            questions = MyData.questions_1;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstaneState) {
        savedInstaneState.putStringArray(SAVED_QUESTIONS, questions);
        super.onSaveInstanceState(savedInstaneState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState);
            questions = savedInstanceState.getStringArray(SAVED_QUESTIONS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(DEBUG, "in onPause");
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("questions_array_size", questions.length);
        for(int i=0;i<questions.length; i++)
            editor.putString("questions_saved_" + i, questions[i]);
        editor.commit();
    }

    @Override
    protected void onResume() {
        Log.d(DEBUG, "in onResume");
        super.onResume();

        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        int size = sharedPreferences.getInt("questions_array_size", 0);
        questions = new String[size];
        for(int i=0; i<size; i++) {
            questions[i] = sharedPreferences.getString("questions_saved_" + i, null);
        }

        resetQuestions();

        adapter = new QuestionCardsAdapter(questions);
        recyclerView.setAdapter(adapter);
    }


}
