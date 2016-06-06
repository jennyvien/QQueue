package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    private Button ok;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState != null) {
//            content = savedInstanceState.getStringArrayList("questions");
//        }
        Bundle bundle = getArguments();
        if(bundle!=null) content = bundle.getStringArrayList("questions");

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Toast.makeText(
//                getActivity(),
//                "Passed in length: "+content.size(),
//                Toast.LENGTH_SHORT
//        ).show();

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

        ok = (Button)getActivity().findViewById(R.id.okbutton);
    }

    public void okClicked(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }

    public ArrayList<String> updateList(){
        return content;
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

                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("updatedlist", content);
                    //Fragment dialog = getActivity().getFragmentManager().findFragmentByTag("yourQueue");
                    //dialog.setArguments(bundle);
                    //getActivity().updateContent(content);

                    mAdapter.notifyDataSetChanged();
                    break;
                case DIRECTION_NORMAL_RIGHT:
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Test Dialog").setMessage("You swiped right").create().show();
                    dir = "Right";
                    break;
            }
//            Toast.makeText(
//                    getActivity(),
//                    dir + " swipe Action triggered on " + mAdapter.getItem(position),
//                    Toast.LENGTH_SHORT
//            ).show();
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
