package com.example.dimpy.whemsley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Timer;
import java.util.TimerTask;

public class connectTrollyDets_frag extends Fragment {

    ImageButton scan_button_trolly;
    String code;
    FragmentManager fragmentManager;

    public connectTrollyDets_frag() {
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
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_connect_trolly_dets_frag, container, false);

        scan_button_trolly = (ImageButton) view.findViewById(R.id.scan_button_trolly);
        scan_button_trolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanFromFragment();
            }
        });
        return view;
    }


    private void scanFromFragment() {

        IntentIntegrator.forSupportFragment(this).initiateScan();

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String toast;
        if (result != null) {
            if (result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
            }
            code = result.getContents();
            Log.e(" in onactivityres", "  " + toast);
            // At this point we may or may not have a reference to the activity
            if (getActivity() != null && toast != null) {
                Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
                toast = null;
                gotoOccFrag();
            }
        }
    }

    private void gotoOccFrag() {


        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading...");
        dialog.setMessage("Please wait. Adding Package to Trolly");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

        long delayInMillis = 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dialog.dismiss();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content,
                                new aboutMe_frag(),
                                "aboutMe_frag").commit();
            }
        }, delayInMillis);



        Toast.makeText(getActivity(), "New Package stored to Trolly", Toast.LENGTH_SHORT).show();
    }

}
