package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Spencer on 6/5/2016.
 */
public class PlaylistItemDialog extends DialogFragment {
    private Button edit; //= (Button)Contentv.findViewById(R.id.Edit);
    private Button delete; //= (Button)getActivity().findViewById(R.id.Delete);
    private Button add; //= (Button)getActivity().findViewById(R.id.AddtoPlaylist);

    public CurrentQueueActivity slave = new CurrentQueueActivity();
    private ArrayList<String> content = new ArrayList<>();
    private String[] questionList;
    private String question;
    private int position;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        View contentView = getActivity().getLayoutInflater().inflate(R.layout.playlist_item_dialog, null);
//
//
//    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.playlist_item_dialog, null);

        edit = (Button)contentView.findViewById(R.id.Edit);
        delete = (Button)contentView.findViewById(R.id.Delete);
        add = (Button)contentView.findViewById(R.id.AddtoPlaylist);

        Bundle bundle = getArguments();
        if(bundle!=null) {
            question = bundle.getString("question");
            position = bundle.getInt("position");
            questionList = bundle.getStringArray("questionList");
        }
//        Toast.makeText(
//                getActivity(),
//                question,
//                Toast.LENGTH_SHORT
//        ).show();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getActivity(),
                        "Clicked edit",
                        Toast.LENGTH_SHORT
                ).show();

            }
        });

        final Fragment tempThis = this;
        final CurrentQueueActivity slave2 = slave;
        final ArrayList<String> tempArray = slave2.convertFromString(questionList);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                tempArray.remove(position);
                String[] tempStringList = slave2.convert(tempArray);

                PlaylistActivity activity = (PlaylistActivity) getActivity();

                activity.setQuestions(tempStringList);

                getFragmentManager().beginTransaction().remove(tempThis).commit();

                activity.refresh();

                //activity.returnAdapter().notifyDataSetChanged();
//                activity.returnView().invalidate();


            }
        });


        return contentView;
    }


}
