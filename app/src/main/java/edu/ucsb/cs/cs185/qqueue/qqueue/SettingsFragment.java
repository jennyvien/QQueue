package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

/**
 * Created by Jenny on 6/5/2016.
 */
public class SettingsFragment extends DialogFragment{
    private static final String DEBUG = "SF_DEBUG";
    private final int TYPE_SERIOUS_NSFW = 0;
    private final int TYPE_NSFW = 1;
    private final int TYPE_SERIOUS = 2;
    private final int TYPE_NORMAL = 3;

    boolean nsfwToggled;
    boolean seriousToggled;
    boolean notificationsToggled;

    int currentTheme;
    Resources.Theme theme;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.settings_dialog, null);

        nsfwToggled = false;
        seriousToggled = false;
        notificationsToggled = false;

        theme = getActivity().getTheme();

        //sets onclicklistener for ok button
        final Fragment temp = this;
        Button ok = (Button)contentView.findViewById(R.id.btn_settings_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                //takes this fragment's questionList and sets the activity's member content to it
                CurrentQueueActivity activity = (CurrentQueueActivity) getActivity();

                MyData myData = new MyData();

                if(nsfwToggled && seriousToggled) {
                    activity.updateContentWithSettings(myData.getQuestionsSeriousNSFW(), TYPE_SERIOUS_NSFW);
                } else if(nsfwToggled) {
                    activity.updateContentWithSettings(myData.getQuestionsNSFW(), TYPE_NSFW);
                } else if(seriousToggled) {
                    activity.updateContentWithSettings(myData.getQuestionsSerious(), TYPE_NSFW);
                }

                //remove dialog
                getFragmentManager().beginTransaction().remove(temp).commit();

                //refreshes card queue actitivty
                activity.refresh();
            }
        });

        Switch nsfw_serious = (Switch) contentView.findViewById(R.id.switch_nsfw);
        nsfw_serious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    nsfwToggled = true;
                    getActivity().setTheme(R.style.AppThemeNSFW);

                } else {
                    nsfwToggled = false;
                }
                changeTheme();
            }
        });

        Switch switch_serious = (Switch) contentView.findViewById(R.id.switch_serious);
        switch_serious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    seriousToggled = true;
                } else {

                    seriousToggled = false;
                }
                changeTheme();
            }
        });


        Switch switch_notif = (Switch) contentView.findViewById(R.id.switch_notifications);
        switch_notif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notificationsToggled = true;
                } else {
                    notificationsToggled = false;
                }
                changeTheme();
            }
        });

        return contentView;
    }

    public void changeTheme() {
        if(seriousToggled) {
            getActivity().setTheme(R.style.AppThemeSerious);
        } else if (nsfwToggled) {
            getActivity().setTheme(R.style.AppThemeNSFW);
            Log.d(DEBUG, "inchagetheme, nsfw");
        } else {
            getActivity().setTheme(R.style.AppThemeNormal);
        }
    }
}
