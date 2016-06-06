package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import java.util.ArrayList;

/**
 * Created by Spencer on 6/4/2016.
 */
//public class YourQueueContent extends ListActivity implements SwipeActionAdapter.SwipeActionListener {
public class PlaylistItemAddtoplaylist extends ListFragment implements SwipeActionAdapter.SwipeActionListener {
    protected SwipeActionAdapter mAdapter;
//    private ArrayList<String> content = new ArrayList<>();
    private String question;
    private Button ok;
    private  YourLibraryActivity slave =  new YourLibraryActivity();
    private final String DEBUG = "PA_DEBUG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState != null) {
//            content = savedInstanceState.getStringArrayList("questions");
//        }
        Bundle bundle = getArguments();
        if(bundle!=null) question = bundle.getString("question");

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.playlist_item_addtoplaylist, null);
//        return contentView;
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> libNames = new ArrayList<>();
        libNames = YourLibraryActivity.getPlaylistNames();



        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.your_queue_row,
                R.id.text,
                libNames
        );
        mAdapter = new SwipeActionAdapter(stringAdapter);
        mAdapter.setSwipeActionListener(this)
                .setDimBackgrounds(true)
                .setListView(getListView());
        setListAdapter(mAdapter);

        ok = (Button)getActivity().findViewById(R.id.okbutton);
    }

    public void okClicked(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

//    public ArrayList<String> updateList(){
//        return content;
//    }

    @Override
    public boolean hasActions(int position, SwipeDirection direction) {
        if(direction.isLeft()) return false;
        if(direction.isRight()) return false;
        return false;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
//        Toast.makeText(
//                getActivity(),
//                "Clicked "+mAdapter.getItem(position),
//                Toast.LENGTH_SHORT
//        ).show();

//        Bundle bundle = new Bundle();
//        bundle.putString("question", question);


        String queueName = (String) mAdapter.getItem(position);
        slave.addToLibraryItems(queueName, question);
        Log.d(DEBUG, queueName);
        Log.d(DEBUG, question);

//        PlaylistItemAddtoplaylist frag = (PlaylistItemAddtoplaylist)getActivity().getFragmentManager().findFragmentByTag("dialog");
        PlaylistItemAddtoFragment frag = (PlaylistItemAddtoFragment) PlaylistItemDialog.frag;
        getActivity().getFragmentManager().beginTransaction().remove(frag).commit();

    }

    @Override
    public boolean shouldDismiss(int position, SwipeDirection direction) {
        return direction == SwipeDirection.DIRECTION_FAR_RIGHT;
    }

    @Override
    public void onSwipe(int[] positionList, SwipeDirection[] directionList) {
        for(int i=0;i<positionList.length;i++) {
            SwipeDirection direction = directionList[i];
//            int position = positionList[i];

            switch (direction) {
                case DIRECTION_FAR_LEFT:
                    break;
                case DIRECTION_NORMAL_LEFT:
                    break;
                case DIRECTION_FAR_RIGHT:
                    break;
                case DIRECTION_NORMAL_RIGHT:

                    break;
            }
            mAdapter.notifyDataSetChanged();
        }
    }


}
