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

    private ArrayList<String> questionArrayList = new ArrayList<>();
    private CurrentQueueActivity slave = new CurrentQueueActivity();

private YourLibraryActivity slave2 = new YourLibraryActivity();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    static Bundle paBundle;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = getIntent();
//        if (intent != null) {
//            Bundle extras = intent.getExtras();
//            if (extras != null) {
//                queueName = extras.getString("playlist_name");
//                queueQuestions = extras.getStringArray("playlist_questions");
//                questionArrayList=slave.convertFromString(queueQuestions);
//
//
//                Log.d(DEBUG, queueName);
//                Log.d(DEBUG, queueQuestions[0]);
//            }
//        }
//
//       // if(queueQuestions!=null)questionArrayList = slave.convertFromString(queueQuestions);
//        //questionArrayList = new ArrayList<>( Arrays.asList(new String[]{"hello", "test", "bye"}));
//
//
//        if(questionArrayList.size()>0) {
//            ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
//                    this,
//                    R.layout.playlist_item,
//                    R.id.playlist_item,
//                    questionArrayList
//            );
//            mAdapter = new SwipeActionAdapter(stringAdapter);
//            mAdapter.setSwipeActionListener(this)
//                    .setDimBackgrounds(true)
//                    .setListView(getListView());
//            setListAdapter(mAdapter);
//        }
//
//    }


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




        adapter = new PlaylistItemAdapter(queueQuestions);
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
        inflater.inflate(R.menu.action_bar_menu_playlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:

                //PlaylistNewPlaylistDialog dialog = new PlaylistNewPlaylistDialog();
                //dialog.show(getFragmentManager(), "newQueue");

//                DialogFragment confirm = new DialogFragment();
                ConfirmationDialog confirm = new ConfirmationDialog();
                Bundle bundle = new Bundle();
                bundle.putString("queue", queueName);
                confirm.setArguments(bundle);
                confirm.show(getFragmentManager(), "dialog");

                //slave2.removeLibraryItem(queueName);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //    @Override
//    public boolean hasActions(int position, SwipeDirection direction) {
//        if(direction.isLeft()) return false;
//        if(direction.isRight()) return true;
//        return false;
//    }
//
//    @Override
//    public boolean shouldDismiss(int position, SwipeDirection direction) {
//        return direction == SwipeDirection.DIRECTION_FAR_RIGHT;
//    }
//
//    @Override
//    public void onSwipe(int[] positionList, SwipeDirection[] directionList) {
//        for(int i=0;i<positionList.length;i++) {
//            SwipeDirection direction = directionList[i];
//            int position = positionList[i];
//
//            switch (direction) {
//                case DIRECTION_FAR_LEFT:
//                    break;
//                case DIRECTION_NORMAL_LEFT:
//                    break;
//                case DIRECTION_FAR_RIGHT:
//                    questionArrayList.remove(position);
//                    mAdapter.notifyDataSetChanged();
//                    break;
//                case DIRECTION_NORMAL_RIGHT:
//
//                    break;
//            }
//            mAdapter.notifyDataSetChanged();
//        }
//    }




}
