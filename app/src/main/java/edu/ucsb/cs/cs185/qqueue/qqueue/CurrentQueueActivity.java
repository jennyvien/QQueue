package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;


// https://github.com/plattysoft/SnappingList/blob/master/SnappingList/src/main/java/com/plattysoft/snappinglist/MainActivity.java
public class CurrentQueueActivity extends BaseActivity {

    private final String BROWSE_QUESTIONS = "BROWSE_QUESTIONS";
    private final String SAVED_QUESTIONS = "SAVED_QUESTIONS";
    private final String PREFERENCES = "PREFERENCES";
    private final String DEBUG = "CQA_DEBUG";
    private final int TYPE_SERIOUS_NSFW = 0;
    private final int TYPE_NSFW = 1;
    private final int TYPE_SERIOUS = 2;
    private final int TYPE_NORMAL = 3;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private String[] questions;

    Button viewQueue;
    private int currentQuestionPos;

    int theme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        theme = getIntent().getIntExtra("theme", TYPE_NORMAL);

        switch(theme) {
            case TYPE_NORMAL :
                this.setTheme(R.style.AppThemeNormal);
                this.getTheme().applyStyle(R.style.AppThemeNormal, true);
                break;
            case TYPE_NSFW :
                this.setTheme(R.style.AppThemeNSFW);
                this.getTheme().applyStyle(R.style.AppThemeNSFW, true);
                break;
            case TYPE_SERIOUS :
                this.setTheme(R.style.AppThemeSerious);
                this.getTheme().applyStyle(R.style.AppThemeSerious, true);
                break;
            default:
                this.setTheme(R.style.AppThemeNormal);
                this.getTheme().applyStyle(R.style.AppThemeNormal, true);
        }
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

        adapter = new QuestionCardsAdapter(questions, true, theme);
        recyclerView.setAdapter(adapter);
        viewQueue = ( Button ) findViewById( R.id.viewQueue);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int direction;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        if (direction <= 0) {
                            if (currentQuestionPos > 0) {
                                recyclerView.smoothScrollToPosition(currentQuestionPos - 1);
                                currentQuestionPos--;
                            } else {
                                recyclerView.smoothScrollToPosition(0);
                            }
                        } else {
                            if (currentQuestionPos < questions.length - 1) {
                                recyclerView.smoothScrollToPosition(currentQuestionPos + 1);
                                currentQuestionPos++;
                            } else {
                                recyclerView.smoothScrollToPosition(questions.length - 1);
                            }
                        }
                        break;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                direction = dx;
            }
        });

        adapter = new QuestionCardsAdapter(questions, true, theme);
        recyclerView.setAdapter(adapter);
        setupBg();
        //initialize library items
        //String[] favorites = MyData.shuffle(MyData.favorites);
        if(YourLibraryActivity.libraryItems == null) {
            YourLibraryActivity.libraryItems = new ArrayList<BrowseItem>();
            for (int i = 0; i < MyData.yourLibraryQueueNames.length; i++) {
                YourLibraryActivity.libraryItems.add(new BrowseItem(
                        MyData.yourLibraryQueueNames[i],
//                        MyData.favorites
                        MyData.favorites
                ));
            }
        }

    }
                
    public static String[] convert(ArrayList<String> array){
        String[] temp = new String[array.size()];
        if(array.size() > 0) {
            for (int i = 0; i < array.size(); i++) temp[i] = array.get(i);
        }
        return temp;
    }

    public void updateContentWithSettings( ArrayList<String> array, int type ){
        String[] temp = new String[array.size()];
        for(int i = 0; i < array.size(); i++) temp[i] = array.get(i);
        questions = temp;

        switch(type) {
            case TYPE_NSFW :
                this.setTheme(R.style.AppThemeNSFW);
                break;
        }
    }

    public static ArrayList<String> convertFromString(String[] array){
        ArrayList<String> temp = new ArrayList<>();
        if(array.length > 0) {
            for (int i = 0; i < array.length; i++) temp.add(array[i]);
        }
        return temp;
    }

    public void setQuestions( String[] s){
        questions = s;
    }

    public void viewQueueClicked(View view){
        YourQueueFragment yourQueue = new YourQueueFragment();
        Log.d(DEBUG, " " + questions.length);
        ArrayList<String> questionsAL = new ArrayList<>( Arrays.asList(questions));

        Bundle bundle = new Bundle();
        bundle.putStringArrayList("questions", questionsAL);
        yourQueue.setArguments(bundle);

        yourQueue.show(getFragmentManager(), "yourQueue");
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
        } else if (questions.length == 0 ) {
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
            Log.d(DEBUG, "in onResume" + questions[i]);
        }
        resetQuestions();

        adapter = new QuestionCardsAdapter(questions, true, theme);
        recyclerView.setAdapter(adapter);
    }

    public void refresh() {
        adapter = new QuestionCardsAdapter(questions, true, theme);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
            switch (item.getItemId()) {
                case R.id.settings:
                launchSettingsFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void launchSettingsFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("theme_cur", theme);
        settingsFragment.setArguments(bundle);

        settingsFragment.show(getFragmentManager(), "settingsFragment");
    }

    private void setupBg() {
           LinearLayout cql = (LinearLayout) findViewById(R.id.cq_layout);

            switch (theme) {
                case TYPE_NORMAL:
                   cql.setBackgroundColor(getResources().getColor(R.color.colorNormalLight, null));

                    break;
                case TYPE_NSFW:
                    cql.setBackgroundColor(getResources().getColor(R.color.colorNSFWLight, null));

                    break;
                case TYPE_SERIOUS:
                   cql.setBackgroundColor(getResources().getColor(R.color.colorSeriousLight, null));

                    break;
                default:
                   cql.setBackgroundColor(getResources().getColor(R.color.colorNormalLight, null));

            }
        }


}
