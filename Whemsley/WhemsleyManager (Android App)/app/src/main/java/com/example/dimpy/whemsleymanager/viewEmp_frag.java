package com.example.dimpy.whemsleymanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;

public class viewEmp_frag extends Fragment {

    FloatingActionButton floatingActionButton;
    FragmentManager fragmentManager;
    public viewEmp_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentManager = getFragmentManager();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_emp_frag, container, false);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(getActivity());
                dialog.setTitle("Fetching Employee details..");
                dialog.setMessage("Please wait as we get the details");
                dialog.setIndeterminate(true);
                dialog.setCancelable(false);
                dialog.show();

                long delayInMillis = 1000;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        dialog.dismiss();

                    }
                }, delayInMillis);

                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content,
                                new about_emp_frag(),
                                "about_emp_frag").commit();

            }
        });



        return view;
    }



}
