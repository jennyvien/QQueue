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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Spencer on 6/4/2016.
 */
public class YourPlayListsFragment extends DialogFragment {
    private setQueueListener listener;
    private ArrayList<String> questionList = new ArrayList<>();
    private YourLibraryActivity slave = new YourLibraryActivity();
    private CurrentQueueActivity slave2 = new CurrentQueueActivity();
    public void setQueueListener( setQueueListener listener){this.listener = listener;}

    public interface setQueueListener{
        void onQueueSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.your_queue_dialog, null);

        //Get fragment manager at YQC level
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final YourQueueContent yourQueue = new YourQueueContent();

        ArrayList<String> content = new ArrayList<>();
        //Bundle bundle = getArguments();
        ArrayList<String> names = YourLibraryActivity.getPlaylistNames();
        Bundle bundle = new Bundle();
        String[] nameList = slave2.convert(names);
        bundle.putStringArray("names",nameList);
        if(bundle!=null) content = bundle.getStringArrayList("questions");

        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("questions", content);
        yourQueue.setArguments(bundle2);

        //looks for "yourQueueContent" and adds the new fragment into it with a tag
        fragmentTransaction.add(R.id.yourQueueContent, yourQueue, "yourQueueContent");
        fragmentTransaction.commit();

        //sets onclicklistener for ok button
        final Fragment temp = this;
        Button ok = (Button)contentView.findViewById(R.id.okbutton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //takes list from queuecontent and stores it in this fragment's member questionList
                YourQueueContent tempContent = (YourQueueContent) getChildFragmentManager().findFragmentByTag("yourQueueContent");
                questionList = tempContent.updateList();

                //takes this fragment's questionList and sets the activity's member content to it
                CurrentQueueActivity activity = (CurrentQueueActivity) getActivity();

                String[] strArray = activity.convert(questionList);
                activity.setQuestions(strArray);

                //remove dialog
                getFragmentManager().beginTransaction().remove(temp).commit();

                //refreshes card queue actitivty
                activity.refresh();

            }
        });

        ImageButton star = (ImageButton)contentView.findViewById(R.id.imageButton);
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //takes list from queuecontent and stores it in this fragment's member questionList
                YourQueueContent tempContent = (YourQueueContent) getChildFragmentManager().findFragmentByTag("yourQueueContent");
                questionList = tempContent.updateList();

                //takes this fragment's questionList and sets the activity's member content to it
                CurrentQueueActivity activity = (CurrentQueueActivity) getActivity();

                String[] strArray = activity.convert(questionList);
                //activity.setQuestions(strArray);

                for(int i = 0; i < questionList.size();i++) {
                    slave.addToLibraryItems("Favorites", questionList.get(i));
                }
                        Toast.makeText(
                getActivity(),
                "Current queue added to favorites!",
                Toast.LENGTH_SHORT
        ).show();
                //remove dialog
                getFragmentManager().beginTransaction().remove(temp).commit();

                //refreshes card queue actitivty
                //activity.refresh();

            }
        });
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
