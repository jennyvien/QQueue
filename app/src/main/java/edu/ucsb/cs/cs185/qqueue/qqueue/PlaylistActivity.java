package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;

import java.util.ArrayList;

public class PlaylistActivity extends BaseActivity {
//public class PlaylistActivity extends ListActivity implements SwipeActionAdapter.SwipeActionListener{

    protected SwipeActionAdapter mAdapter;



    private final String DEBUG = "PA_DEBUG";

    private String queueName;
    private String[] queueQuestions;
    private String type;

    private ArrayList<String> questionArrayList = new ArrayList<>();
    private CurrentQueueActivity slave = new CurrentQueueActivity();

private YourLibraryActivity slave2 = new YourLibraryActivity();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    static Bundle paBundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        setupNavigationDrawer();


        if(savedInstanceState!= null) paBundle = savedInstanceState;

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                queueName = extras.getString("playlist_name");
                queueQuestions = extras.getStringArray("playlist_questions");
                type = extras.getString("type");

//                Log.d(DEBUG, queueName);
//                Log.d(DEBUG, queueQuestions[0]);
            }
        }

        getSupportActionBar().setTitle(queueName);

        recyclerView = (RecyclerView) findViewById(R.id.playlist_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());





//        adapter = new PlaylistItemAdapter(queueQuestions);
        if( type.equals("library") )adapter = new PlaylistItemAdapter(queueQuestions);
        else adapter = new BrowseListItemAdapter(queueQuestions);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new PlaylistItemAdapter(queueQuestions);
        recyclerView.setAdapter(adapter);

    }



    @Override
    protected void onPause() {
        super.onPause();

//        Toast.makeText(
//                this,
//                "onpause",
//                Toast.LENGTH_SHORT
//        ).show();

       slave2.updateLibraryItem(queueName, queueQuestions);
//        YourLibraryActivity.updateLibraryItem(queueName, queueQuestions);
    }


    public void setQuestions(String[] s){
        queueQuestions = s;
    }

    public void refresh(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new PlaylistItemAdapter(queueQuestions);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(type.equals("library"))inflater.inflate(R.menu.action_bar_menu_playlist, menu);
        else inflater.inflate(R.menu.action_bar_menu_browselist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Delete button
        switch (item.getItemId()) {
            case R.id.settings:

                ConfirmationDialog confirm = new ConfirmationDialog();
                Bundle bundle = new Bundle();
                bundle.putString("queue", queueName);
                confirm.setArguments(bundle);
                confirm.show(getFragmentManager(), "dialog");

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
