package com.example.dimpy.whemsleymanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class viewTransactions_frag extends Fragment {

    TextView tv0;
    ListView listView;

    public viewTransactions_frag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_transactions_frag, container, false);

        tv0 = (TextView) view.findViewById(R.id.tv0);
        listView =   (ListView) view.findViewById(R.id.listView);

        ArrayList<String> your_array_list = new ArrayList<String>();
        your_array_list.add("\nPackage id : r3_345455 \nBy Employee: Johanaa\nInserted to Trolly 0076D5\n  @1237 hrs 13Feb 2018\n78kg of total 20 units\nTrans_id: 88274\nManager Incharge: Ms. Yang\n");
        your_array_list.add("\nPackage id : p2_255454 \nBy Employee: Kim\nInserted to Trolly 02446D5\n  @1426 hrs 13Feb 2018\n72kg of total 2 units\nTrans_id: 88278\nManager Incharge: Mrs. Smith\n");
        your_array_list.add("\nPackage id : j1_737278 \nBy Employee: Amanda\nInserted to Trolly 0076D5\n  @1237 hrs 13Feb 2018\n78kg of total 20 units\nTrans_id: 88274\nManager Incharge: Ms. Yang\n");
        your_array_list.add("\nPackage id : m4_917395 \nBy Employee: Jack\nInserted to Trolly 0076D5\n  @1237 hrs 13Feb 2018\n78kg of total 20 units\nTrans_id: 88274\nManager Incharge: Ms. Yang\n");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, your_array_list );

        listView.setAdapter(arrayAdapter);

        return view;
    }





}
