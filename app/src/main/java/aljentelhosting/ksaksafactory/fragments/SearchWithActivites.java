package aljentelhosting.ksaksafactory.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.onItemSelectedListener.CustomOnItemSelectedListener;


public class SearchWithActivites extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_search_with_activites, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);



        // Spinner element
        Spinner Chooes_from_Actvites = (Spinner) rootView.findViewById(R.id.choes_actvity);

        // Spinner click listener
        Chooes_from_Actvites.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        // Spinner Drop down elements
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>( getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Chooes_from_Actvites.setAdapter(dataAdapter);
         return rootView;






    }


}
