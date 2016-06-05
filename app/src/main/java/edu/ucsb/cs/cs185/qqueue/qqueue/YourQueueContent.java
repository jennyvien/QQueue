package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wdullaer.swipeactionadapter.SwipeActionAdapter;
import com.wdullaer.swipeactionadapter.SwipeDirection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Spencer on 6/4/2016.
 */
//public class YourQueueContent extends ListActivity implements SwipeActionAdapter.SwipeActionListener {
public class YourQueueContent extends ListFragment implements SwipeActionAdapter.SwipeActionListener {

    protected SwipeActionAdapter mAdapter;
//    private String[] content = new String[20];
    private ArrayList<String> content = new ArrayList<>();
    private int flag = 0;

//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container,
//                             Bundle savedInstanceState){
//        return inflater.inflate(R.layout.your_queue_content, container, false);
//
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        View contentView = getActivity().getLayoutInflater().inflate(R.layout.your_queue_content, null);
//
//
//        String[] content = new String[20];
//        for (int i = 0; i < 20; i++) content[i] = "Row " + (i + 1);
////        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
//        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
//                //this, //replaced with getActivity()
//                getActivity(),
//                R.layout.your_queue_row,
//                R.id.text,
//                new ArrayList<>(Arrays.asList(content))
//        );
//        mAdapter = new SwipeActionAdapter(stringAdapter);
//        mAdapter.setSwipeActionListener(this)
//                .setDimBackgrounds(true)
//                .setListView(getListView());
//        setListAdapter(mAdapter);
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             ViewGroup container,
//                             Bundle savedInstanceState){
//        View v = inflater.inflate(R.layout.your_queue_content, container, false);
//        String[] content = new String[20];
//        for (int i = 0; i < 20; i++) content[i] = "Row " + (i + 1);
////        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
//        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
//                //this, //replaced with getActivity()
//                getActivity(),
//                R.layout.your_queue_row,
//                R.id.text,
//                new ArrayList<>(Arrays.asList(content))
//        );
//        mAdapter = new SwipeActionAdapter(stringAdapter);
//        mAdapter.setSwipeActionListener(this)
//                .setDimBackgrounds(true)
//                .setListView(getListView());
//        setListAdapter(mAdapter);
//        return v;
//    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize demo array
        //for (int i = 0; i < 20; i++) content.set(i, "Row " + (i + 1));

        if( flag == 0 )for (int i = 0; i < 20; i++) content.add( "Row " + (i + 1) );
        else for (int i = 0; i < content.size(); i++) content.add( "Row " + (i + 1) );

        Toast.makeText(
                getActivity(),
                "Build content",
                Toast.LENGTH_SHORT
        ).show();

        String[] contentArray = new String[content.size()];
        for( int j = 0; j < content.size(); j++) contentArray[j] = content.get(j);

        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(
                //this, //replaced with getActivity()
                getActivity(),
                R.layout.your_queue_row,
                R.id.text,
                new ArrayList<>(Arrays.asList(contentArray))
        );
        mAdapter = new SwipeActionAdapter(stringAdapter);
        mAdapter.setSwipeActionListener(this)
                .setDimBackgrounds(true)
                .setListView(getListView());
        setListAdapter(mAdapter);
       //mAdapter.
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

//                    mAdapter.remove()
//                    mAdapter.getAdapter().

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
