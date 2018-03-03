package com.example.dimpy.whemsleymanager;

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
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class viewTrolly_frag extends Fragment {
    ImageButton scan_button;
    String code;
    FragmentManager fragmentManager;
    TextView tv0, tv1, tv3;
    ListView listView;
    LinearLayout toggle;
    ToggleButton toggle_button;

    public viewTrolly_frag() {
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
        View view = inflater.inflate(R.layout.fragment_view_trolly_frag, container, false);
        toggle = (LinearLayout) view.findViewById(R.id.toggle);
        toggle_button = (ToggleButton) view.findViewById(R.id.toggle_button);
        scan_button = (ImageButton) view.findViewById(R.id.scan_button_ViewTrolly);
        tv0 = (TextView) view.findViewById(R.id.tv0);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        listView =   (ListView) view.findViewById(R.id.listView);

        listView.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        toggle.setVisibility(View.GONE);

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



                    }
                }, delayInMillis);

                tv0.setVisibility(View.GONE);
                tv1.setVisibility(View.GONE);
                tv3.setVisibility(View.GONE);
                scan_button.setVisibility(View.GONE);

                tv3.setVisibility(View.VISIBLE);
                toggle.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                populateListView(listView);



            }
        }
    }

    private void populateListView(ListView listView) {

        ArrayList<String> your_array_list = new ArrayList<String>();
        your_array_list.add("\nPackage id : u0_293884 \nGreen Box of Drill Model:\n  TE 7-C x 20\n78kg of total 20 units\n728829748_AKKJ47_914828492\n\n");
        your_array_list.add("\nPackage id : u0_298986 \nBlue Box of Glass Cutter:\n  DD 200 G02 x 2\n72kg of total 2 units\n917483748_AKKJ48_183758992\n\n");
        your_array_list.add("\nPackage id : u0_298989 \nBlue Box of Glass Cutter:\n  DD 200 G02 x 2\n72kg of total 2 units\n917483748_AKKJ48_183758189\n\n");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, your_array_list );

        listView.setAdapter(arrayAdapter);

        Toast.makeText(getActivity(), "HI!", Toast.LENGTH_SHORT).show();

    }

}
