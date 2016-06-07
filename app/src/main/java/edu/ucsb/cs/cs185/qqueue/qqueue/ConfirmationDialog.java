package edu.ucsb.cs.cs185.qqueue.qqueue;

/**
 * Created by Spencer on 5/5/2016.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class ConfirmationDialog extends DialogFragment {
    public String dialogString;

    private YourLibraryActivity slave2 = new YourLibraryActivity();
    private setDialogListener listener;
    private String content;

    public void setText(String string) {
        dialogString = string;
    }

    public void setDialogListener(setDialogListener listener) {
        this.listener = listener;
    }

    public interface setDialogListener {
        void setDialog(boolean b);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if(bundle!=null) content = bundle.getString("queue");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sure you want to delete this queue?");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //finish();
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                slave2.removeLibraryItem(content);
                Toast.makeText(
                        getActivity(),
                        content +" has been deleted",
                        Toast.LENGTH_SHORT
                ).show();
                getActivity().finish();

            }
        });
        return builder.create();

    }
}



