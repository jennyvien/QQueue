package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spencer on 6/4/2016.
 */
public class YourQueueAddToPlaylist extends DialogFragment {
//    private setQueueListener listener;
    private ArrayList<String> questionList = new ArrayList<>();
    private final String DEBUG = "PA_DEBUG";

    //static Fragment frag;
    final Fragment temp = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.your_queue_dialog, null);
        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.playlist_item_addtoplaylist2, null);
        TextView name = (TextView)contentView.findViewById(R.id.yourQueueHeader);
        name.setText("Add To Your Queue:");

        Button button = (Button)contentView.findViewById(R.id.okbutton);
        button.setText("Cancel");
//        Button  = (Button)contentView.findViewById(R.id.okbutton);
        button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      getFragmentManager().beginTransaction().remove(temp).commit();

                  }
              }
            );
        //RelativeLayout bottom = (RelativeLayout)contentView.findViewById(R.id.listBottomBar);
        //bottom.setVisibility(View.GONE);

        //Get fragment manager at YQC level
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        final YourQueueContent yourQueue = new YourQueueContent();

//        final PlaylistItemAddtoplaylist playlist = new PlaylistItemAddtoplaylist();

        final YourQueueATPListfragment listFrag = new YourQueueATPListfragment();

        //ArrayList<String> content = new ArrayList<>();
        //String content=null;
        Bundle bundle = getArguments();
        String[] questionList = new String[0];
        if(bundle!=null) questionList = bundle.getStringArray("questions");

        Bundle bundle2 = new Bundle();
        bundle2.putStringArray( "questions", questionList);
        //Log.d(DEBUG, content);


        listFrag.setArguments(bundle2);
        //frag = listFrag;

        //looks for "yourQueueContent" and adds the new fragment into it with a tag
        fragmentTransaction.add(R.id.yourQueueContent, listFrag, "listFrag");
        fragmentTransaction.commit();

        //sets onclicklistener for ok button
//        final Fragment temp = this;
//        Button ok = (Button)contentView.findViewById(R.id.okbutton);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //takes list from queuecontent and stores it in this fragment's member questionList
//                YourQueueContent tempContent = (YourQueueContent) getChildFragmentManager().findFragmentByTag("yourQueueContent");
//                questionList = tempContent.updateList();
//
//                //takes this fragment's questionList and sets the activity's member content to it
//                CurrentQueueActivity activity = (CurrentQueueActivity) getActivity();
//
//                String[] strArray = activity.convert(questionList);
//                activity.setQuestions(strArray);
//
//                //remove dialog
//                getFragmentManager().beginTransaction().remove(temp).commit();
//
//                //refreshes card queue actitivty
//                activity.refresh();
//
//            }
//        });

//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(contentView).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // save changes to current list
//                    }
//                });
//        builder.create();
        return contentView;
    }


}
