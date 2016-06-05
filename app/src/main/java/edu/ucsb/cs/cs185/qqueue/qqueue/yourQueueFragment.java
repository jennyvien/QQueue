package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Spencer on 6/4/2016.
 */
public class YourQueueFragment extends DialogFragment {
    private setQueueListener listener;

    public void setQueueListener( setQueueListener listener){this.listener = listener;}

    public interface setQueueListener{
        void onQueueSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.your_queue_dialog, null);

        /* View contentView = getActivity().findViewById(R.layout.your_queue_dialog); */

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        YourQueueContent yourQueue = new YourQueueContent();

        //looks for "yourQueueContent" and adds the new fragment into it
        fragmentTransaction.add(R.id.yourQueueContent, yourQueue);
        //fragmentTransaction.add(R.id.yourQueueDialog, yourQueue);

        fragmentTransaction.commit();

        Button ok = (Button)contentView.findViewById(R.id.okbutton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onQueueSet();

            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(contentView).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // save changes to current list
                    }
                });
        builder.create();
        return contentView;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState ) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        //LayoutInflater inflater = getActivity().getLayoutInflater();
//        //inflater.inflate(R.layout.your_queue_dialog, null);
////        View view = getActivity().
////        View contentView = getActivity().getLayoutInflater().inflate(R.layout.your_queue_dialog, null);
//        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.your_queue_dialog, null);
//
//        /* View contentView = getActivity().findViewById(R.layout.your_queue_dialog); */
//
//        FragmentManager fragmentManager = getChildFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        YourQueueContent yourQueue = new YourQueueContent();
//
//        fragmentTransaction.add(R.id.yourQueueContent, yourQueue);
//        //fragmentTransaction.add(R.id.yourQueueDialog, yourQueue);
//
//        fragmentTransaction.commit();
//
//
//        //inflate with layout
////        builder.setView(inflater.inflate(R.layout.your_queue_dialog, null))
//        builder.setView(contentView)
//                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // save changes to current list
//                    }
//                });
//
//        return builder.create();
//
//    }


}
