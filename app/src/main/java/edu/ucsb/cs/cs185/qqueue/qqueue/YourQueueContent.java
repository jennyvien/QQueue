package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import java.util.ArrayList;

/**
 * Created by Spencer on 6/4/2016.
 */
//public class YourQueueContent extends ListActivity implements SwipeActionAdapter.SwipeActionListener {
public class YourQueueContent extends ListFragment implements SwipeActionAdapter.SwipeActionListener {
    protected SwipeActionAdapter mAdapter;
    private ArrayList<String> content = new ArrayList<>();

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
////        if (savedInstanceState != null) {
////            content = savedInstanceState.getStringArrayList("questions");
////        }
//
//        content = getActivity().getIntent().getExtras().getStringArrayList("questions");
//
//
//        Toast.makeText(
//                getActivity(),
//                ""+content.size(),
//                Toast.LENGTH_SHORT
//        ).show();
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        //content = this.getArguments().getStringArrayList("questions");
//        //CurrentQueueActivity activity = getActivity();
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Activity activity = getActivity();
        Intent intent = null;
        if(activity!=null) {
            activity.getIntent();
            if (intent != null ){
                content = intent.getExtras().getStringArrayList("questions");
            }
        }

//        content = activity.getIntent().getExtras().getStringArrayList("questions");
       // content = getArguments().getStringArrayList("questions");

        Toast.makeText(
                getActivity(),
                ""+content.size(),
                Toast.LENGTH_SHORT
        ).show();

//        if (savedInstanceState != null) {
//            content = savedInstanceState.getStringArrayList("questions");
//        }

        //initialize demo array
        //for (int i = 0; i < 20; i++) content.add( "Row " + (i + 1) );

        Toast.makeText(
                getActivity(),
                "Build content",
                Toast.LENGTH_SHORT
        ).show();

        String[] contentArray = new String[content.size()];
        for( int j = 0; j < content.size(); j++) contentArray[j] = content.get(j);

        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.your_queue_row,
                R.id.text,
                content
        );
        mAdapter = new SwipeActionAdapter(stringAdapter);
        mAdapter.setSwipeActionListener(this)
                .setDimBackgrounds(true)
                .setListView(getListView());
        setListAdapter(mAdapter);
    }


    @Override
    public boolean hasActions(int position, SwipeDirection direction) {
        if(direction.isLeft()) return false;
        if(direction.isRight()) return true;
        return false;
    }

    @Override
    public boolean shouldDismiss(int position, SwipeDirection direction) {
        return direction == SwipeDirection.DIRECTION_FAR_RIGHT;
    }

    @Override
    public void onSwipe(int[] positionList, SwipeDirection[] directionList) {
        for(int i=0;i<positionList.length;i++) {
            SwipeDirection direction = directionList[i];
            int position = positionList[i];
            String dir = "";



            switch (direction) {
                case DIRECTION_FAR_LEFT:
                    dir = "Far left";

                    break;
                case DIRECTION_NORMAL_LEFT:
                    dir = "Left";
                    break;
                case DIRECTION_FAR_RIGHT:
                    dir = "Far right";
                    Toast.makeText(
                            getActivity(),
                            "removed " + content.get(position),
                            Toast.LENGTH_SHORT
                    ).show();
                    content.remove(position);
                    mAdapter.notifyDataSetChanged();
                    break;
                case DIRECTION_NORMAL_RIGHT:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Test Dialog").setMessage("You swiped right").create().show();
                    dir = "Right";
                    break;
            }
            Toast.makeText(
                    getActivity(),
                    dir + " swipe Action triggered on " + mAdapter.getItem(position),
                    Toast.LENGTH_SHORT
            ).show();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){
        Toast.makeText(
                getActivity(),
                "Clicked "+mAdapter.getItem(position),
                Toast.LENGTH_SHORT
        ).show();
    }

}
