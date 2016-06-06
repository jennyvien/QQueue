package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import 	android.support.v7.widget.RecyclerView;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;


// https://github.com/plattysoft/SnappingList/blob/master/SnappingList/src/main/java/com/plattysoft/snappinglist/MainActivity.java
public class CurrentQueueActivity extends BaseActivity {

    private final String BROWSE_QUESTIONS = "BROWSE_QUESTIONS";
    private final String SAVED_QUESTIONS = "SAVED_QUESTIONS";
    private final String PREFERENCES = "PREFERENCES";
    private final String DEBUG = "CQA_DEBUG";

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private String[] questions;

    private Button viewQueue;

    private int currentPosition;
    private int finalPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.activity_current_queue);
        Log.d(DEBUG, "in onCreate");
        setContentView(R.layout.activity_card_queue);
        setupNavigationDrawer();
        resetQuestions();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new QuestionCardsAdapter(questions);
        recyclerView.setAdapter(adapter);
        
        viewQueue = ( Button ) findViewById( R.id.viewQueue);
//        final Activity activity = this;
//        Button viewQueue = ( Button )findViewById(R.id.viewQueue);
//        viewQueue.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                YourQueueFragment yourQueue = new YourQueueFragment();
//                Log.d(DEBUG, " " + questions.length);
//                Intent intent = new Intent( activity, CurrentQueueActivity.class);
//                ArrayList<String> questionsAL = new ArrayList<>( Arrays.asList(questions));
//                intent.putExtra("questions", questionsAL);
//                Toast.makeText(
//                        activity,
//                        "length: "+questions.length,
//                        Toast.LENGTH_SHORT
//                ).show();
//                Toast.makeText(
//                        activity,
//                        "Size: "+questionsAL.size(),
//                        Toast.LENGTH_SHORT
//                ).show();
//
//
////                Bundle bundle = new Bundle();
////                ArrayList<String> questionsAL = new ArrayList<>( Arrays.asList(questions));
////                Log.d(DEBUG, "+"+questionsAL.size());
////
////                bundle.putStringArrayList("questions", questionsAL);
////                yourQueue.setArguments(bundle);
//
//                yourQueue.setQueueListener(new YourQueueFragment.setQueueListener() {
//                    @Override
//                    public void onQueueSet() {
//
//                    }
//                });
//                yourQueue.show(getFragmentManager(), "yourQueue");
//            }
//        });

    }
    public void updateContent( ArrayList<String> array ){
        String[] temp = new String[array.size()];
        for(int i = 0; i < array.size(); i++) temp[i] = array.get(i);
        questions = temp;
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
        } else if( questions.length == 0 )
        {
            Log.d(DEBUG, "Setting questions");
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

    public void refresh(){
        adapter = new QuestionCardsAdapter(questions);
        recyclerView.setAdapter(adapter);

    }

}
