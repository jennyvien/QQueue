package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



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

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new PlaylistItemAdapter(queueQuestions);
        recyclerView.setAdapter(adapter);

    }





    public void setQuestions( String[] s){
        queueQuestions = s;
    }

    public void refresh(){
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        adapter = new PlaylistItemAdapter(queueQuestions);
        recyclerView.setAdapter(adapter);
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
