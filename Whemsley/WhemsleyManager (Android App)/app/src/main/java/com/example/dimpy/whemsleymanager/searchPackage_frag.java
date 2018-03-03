package com.example.dimpy.whemsleymanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Timer;
import java.util.TimerTask;

public class searchPackage_frag extends Fragment {

    ImageButton scan_button;
    String code;
    FragmentManager fragmentManager;
    EditText packageStampNumber;
    FloatingActionButton fab ;
    TextView detailsTextView;

    public searchPackage_frag() {
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
        View view;
        view = inflater.inflate(R.layout.fragment_search_package_frag, container, false);

        scan_button = (ImageButton) view.findViewById(R.id.scan_button);
        detailsTextView = (TextView) view.findViewById(R.id.detailsTextView);
        packageStampNumber =(EditText) view.findViewById(R.id.editText_01);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                detailsTextView.setText("\nPackage ID: u0_293884 \n" +
                        "Troll Number: 0076D5\nPackage" +
                        " Dets:\nGreen Box of Drill Model: TE 7-C x 20\n" +
                        "78kg of total 20 units\nLast Transported from: OakLand\nShippment Number: 9829919" +
                        " \n StampNumber: 728829748_AKKJ47_914828492\n\n ");

            }
        });

        scan_button.setOnClickListener(new View.OnClickListener() {
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
                toast = "Scanned Barcode " + result.getContents();
            }
            code = result.getContents();
            Log.e(" in onactivityres", "  " + toast);
            // At this point we may or may not have a reference to the activity
            if (getActivity() != null && toast != null) {
                Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();

                packageStampNumber.setText("728829748_AKKJ47_914828492");

            }
        }
    }
}
