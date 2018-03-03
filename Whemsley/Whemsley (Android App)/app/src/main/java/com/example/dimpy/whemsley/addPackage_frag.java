package com.example.dimpy.whemsley;

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
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class addPackage_frag extends Fragment {

    ImageButton scan_button;
    String code;
    FragmentManager fragmentManager;
    EditText packageid, packagedets1, packagedets2, packageStampNumber;
    FloatingActionButton fab ;

    public addPackage_frag() {
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
        view = inflater.inflate(R.layout.fragment_add_package_frag, container, false);

        scan_button = (ImageButton) view.findViewById(R.id.scan_button);
        packageid =(EditText) view.findViewById(R.id.editText_2);
        packagedets1 =(EditText) view.findViewById(R.id.editText_3);
        packagedets2 =(EditText) view.findViewById(R.id.editText_4);
        packageStampNumber =(EditText) view.findViewById(R.id.editText_5);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content,
                                new connectTrollyDets_frag(),
                                "connectTrollyDets_frag").commit();
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
                toast = null;
                packageid.setText("u0_293884");
                packagedets1.setText("Green Box of Drill Model: TE 7-C x 20");
                packagedets2.setText("78kg of total 20 units");
                packageStampNumber.setText("728829748_AKKJ47_914828492");

            }
        }
    }
}
