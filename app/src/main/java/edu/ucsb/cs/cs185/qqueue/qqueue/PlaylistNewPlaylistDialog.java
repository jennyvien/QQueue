package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Spencer on 6/5/2016.
 */
public class PlaylistNewPlaylistDialog extends DialogFragment {

    private EditText edit;
    private Button ok;
    private ArrayList<String> content = new ArrayList<>();
    private String[] questionList;
    private String question;
    private int position;

    public CurrentQueueActivity slave = new CurrentQueueActivity();

    public YourLibraryActivity slave2 = new YourLibraryActivity();

    //@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            question = bundle.getString("question");
            position = bundle.getInt("position");
            questionList = bundle.getStringArray("questionList");
        }

       ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.playlist_item_edit, null);

        TextView text = (TextView)contentView.findViewById(R.id.textView);
        text.setText("Name New Playlist:");
        ok = (Button)contentView.findViewById(R.id.okEditButton);
        edit = (EditText) contentView.findViewById(R.id.editText);

        //Bundle bundleOut = new Bundle();
        //bundleOut.putString("question", editedQuestion);

        final Fragment tempThis = this;
        //final ArrayList<String> tempArray = slave.convertFromString(questionList);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit.getText().toString();

//                tempArray.set(position, editedQuestion);
                YourLibraryActivity activity = (YourLibraryActivity) getActivity();
                getFragmentManager().beginTransaction().remove(tempThis).commit();
//                PlaylistActivity activity = (PlaylistActivity) getActivity();
//                String[] tempStringList = slave.convert(tempArray);


                slave2.addNewQueue(name);

                activity.refresh();


                //activity.setQuestions(tempStringList);
                //activity.refresh();
            }


        });
        return contentView;
    }
}
