package com.example.dimpy.whemsley;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class viewHistory_frag extends Fragment {

    TextView tv0;
    ListView listView;

    public viewHistory_frag() {
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
        View view = inflater.inflate(R.layout.fragment_view_history_frag, container, false);

        tv0 = (TextView) view.findViewById(R.id.tv0);
        listView =   (ListView) view.findViewById(R.id.listView);

        ArrayList<String> your_array_list = new ArrayList<String>();
        your_array_list.add("\nPackage id : u0_293884 \nInserted to Trolly 0076D5\n  @1237 hrs 13Feb 2018\n78kg of total 20 units\nTrans_id: 88274\nManager Incharge: Mr. Chandreshekhar\n");
        your_array_list.add("\nPackage id : u0_298986 \nInserted to Trolly 0076D5\n  @1426 hrs 13Feb 2018\n72kg of total 2 units\nTrans_id: 88278\nManager Incharge: Mr. Chandreshekhar\n");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, your_array_list );

        listView.setAdapter(arrayAdapter);

        return view;
    }

}
