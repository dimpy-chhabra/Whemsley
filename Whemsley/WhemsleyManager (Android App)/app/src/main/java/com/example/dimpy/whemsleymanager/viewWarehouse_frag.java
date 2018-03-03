package com.example.dimpy.whemsleymanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class viewWarehouse_frag extends Fragment {

    GridView gridView;
    public viewWarehouse_frag() {
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
        View view;

        view = inflater.inflate(R.layout.fragment_view_warehouse_frag, container, false);

         gridView = (GridView) view.findViewById(R.id.grid_view);

        gridView.setAdapter(new ImageAdapter(getActivity()));
        /*
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Searching for Package..");
        dialog.setMessage("Please as we find your package and fetch it's details");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

        long delayInMillis = 1555;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();



            }
        }, delayInMillis);
*/
        return view;
    }



}
