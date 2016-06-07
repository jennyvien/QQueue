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
import android.widget.ImageButton;
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
        button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      getFragmentManager().beginTransaction().remove(temp).commit();

                  }
              }
            );



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

        final String[] questionListFinal = questionList;

        Bundle bundle2 = new Bundle();
        bundle2.putStringArray( "questions", questionList);
        //Log.d(DEBUG, content);


        listFrag.setArguments(bundle2);
        //frag = listFrag;

        //looks for "yourQueueContent" and adds the new fragment into it with a tag
        fragmentTransaction.add(R.id.yourQueueContent, listFrag, "listFrag");
        fragmentTransaction.commit();

        ImageButton add = (ImageButton)contentView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       getFragmentManager().beginTransaction().remove(temp).commit();

                                       //load the add dialog
                                       PlaylistNewPlaylistDialog dialog = new PlaylistNewPlaylistDialog();

                                       Bundle bundle1 = new Bundle();
                                       bundle1.putInt("addFromPlaylist", 2);
                                       bundle1.putStringArray("questionList", questionListFinal );

                                       dialog.setArguments(bundle1);
                                       dialog.show(getFragmentManager(), "newQueue");


                                   }
                               }
        );

        return contentView;
    }


}
