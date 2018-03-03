package com.example.dimpy.whemsleymanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;


public class lockDown_frag extends Fragment {

    ToggleButton toggle_button;
    public lockDown_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lock_down_frag, container, false);
        toggle_button = (ToggleButton) view.findViewById(R.id.toggle_button);

        toggle_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final ProgressDialog dialog = new ProgressDialog(getActivity());
                    dialog.setTitle("Changing Trolly Status...");
                    dialog.setMessage("Please wait as we add your credentials to the database");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(false);
                    dialog.show();

                    long delayInMillis = 2000;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            dialog.dismiss();

                        }
                    }, delayInMillis);

                } else {

                    final ProgressDialog dialog = new ProgressDialog(getActivity());
                    dialog.setTitle("Changing Trolly Status...");
                    dialog.setMessage("Please wait as we add your credentials to the database");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(false);
                    dialog.show();

                    long delayInMillis = 2000;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            dialog.dismiss();

                        }
                    }, delayInMillis);

                }
            }
        });

        return view;
    }




}
