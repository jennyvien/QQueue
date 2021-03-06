package edu.ucsb.cs.cs185.qqueue.qqueue;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Jenny on 6/5/2016.
 */
public class SettingsFragment extends DialogFragment {
    private static final String DEBUG = "SF_DEBUG";
    private final int TYPE_SERIOUS_NSFW = 0;
    private final int TYPE_NSFW = 1;
    private final int TYPE_SERIOUS = 2;
    private final int TYPE_NORMAL = 3;

    boolean nsfwToggled;
    boolean seriousToggled;
    boolean notificationsToggled;

    int currentTheme;

    Switch switch_nsfw;
    Switch switch_serious;
    Switch switch_notif;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup contentView = (ViewGroup) getActivity().getLayoutInflater().inflate(R.layout.settings_dialog, null);
        setupCurrentTheme();
        setupButton(contentView);
        setupToggles(contentView);
        resetToggles();
        return contentView;
    }

    private void setupCurrentTheme() {
        Bundle bundle = getArguments();
        if (bundle != null)
            currentTheme = bundle.getInt("theme_cur");
        else
            currentTheme = TYPE_NORMAL;
    }

    private void setupButton(ViewGroup contentView) {
        Button ok = (Button) contentView.findViewById(R.id.btn_settings_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentTheme = determineTheme();
                Intent intent = new Intent(v.getContext(), CurrentQueueActivity.class);
                intent.putExtra("theme", currentTheme);

                final Activity act = getActivity();

                switch(currentTheme) {
                    case TYPE_NSFW :
                        intent.putExtra("filtered_qs", MyData.questionsNSFW);
                        break;
                    case TYPE_SERIOUS :
                        intent.putExtra("filtered_qs", MyData.questionsSerious);
                        break;
                    case TYPE_SERIOUS_NSFW :
                        intent.putExtra("filtered_qs", MyData.questionsSeriousNSFW);
                        break;
                }

                if(notificationsToggled) {
                    Toast.makeText(
                            v.getContext(),
                            "You will be receiving notifications from QQueue.",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                startActivity(intent);
                act.finish();
            }
        });
    }

    private void setupToggles(ViewGroup contentView) {
        switch_nsfw = (Switch) contentView.findViewById(R.id.switch_nsfw);
        switch_nsfw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    nsfwToggled = true;
                } else {
                    nsfwToggled = false;
                }
            }
        });

        switch_serious = (Switch) contentView.findViewById(R.id.switch_serious);
        switch_serious.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    seriousToggled = true;
                } else {

                    seriousToggled = false;
                }
            }
        });


        switch_notif = (Switch) contentView.findViewById(R.id.switch_notifications);
        switch_notif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notificationsToggled = true;
                } else {
                    notificationsToggled = false;
                }
            }
        });
    }

    public int determineTheme() {
        if(nsfwToggled && seriousToggled) {
            return TYPE_SERIOUS_NSFW;
        } else if (seriousToggled) {
            return TYPE_SERIOUS;
        } else if (nsfwToggled) {
            return TYPE_NSFW;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SharedPreferences sp = activity.getSharedPreferences("sp", Context.MODE_PRIVATE);
        currentTheme = sp.getInt("saved_theme", TYPE_NORMAL);
        notificationsToggled = sp.getBoolean("notif", false);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("saved_theme", currentTheme);
        editor.putBoolean("notif", notificationsToggled);
        editor.commit();
    }

    public void resetToggles() {
        switch(currentTheme) {
            case TYPE_NORMAL :
                nsfwToggled = false;
                seriousToggled = false;
                break;
            case TYPE_NSFW :
                nsfwToggled = true;
                switch_nsfw.toggle();
                seriousToggled = false;
                break;
            case TYPE_SERIOUS :
                nsfwToggled = false;
                switch_serious.toggle();
                seriousToggled = true;
                break;
            case TYPE_SERIOUS_NSFW :
                nsfwToggled = true;
                seriousToggled = true;
                switch_nsfw.toggle();
                switch_serious.toggle();
        }
        if(notificationsToggled)
            switch_notif.toggle();
    }
}
