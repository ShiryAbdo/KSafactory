package aljentelhosting.ksaksafactory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.MyTextView;


public class A3lanatDetails extends Fragment {

    String ads_category_name,ads_type_name,ads_model_name,ads_price,ads_city_name
            ,date_insert,ads_id,ads_visited ;
    MyTextView ads_category_namet,ads_type_namet,ads_model_namet,ads_pricet,ads_city_namet
            ,date_insertt,ads_idt,ads_visitedt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_a3lanat_details, container, false);
        // get data from bandel
        ads_category_name= getArguments().getString("ads_category_name");
        ads_type_name= getArguments().getString("ads_type_name");
        ads_model_name= getArguments().getString("ads_model_name");
        ads_price= getArguments().getString("ads_price");
        ads_city_name= getArguments().getString("ads_city_name");
        date_insert= getArguments().getString("date_insert");
        ads_id= getArguments().getString("ads_id");
        ads_visited= getArguments().getString("ads_visited");



//         ********
        ads_category_namet=(MyTextView)rootView.findViewById(R.id.ads_category_name);
        ads_type_namet=(MyTextView)rootView.findViewById(R.id.ads_type_name);
        ads_model_namet=(MyTextView)rootView.findViewById(R.id.ads_model_name);
        ads_pricet=(MyTextView)rootView.findViewById(R.id.ads_price);
        ads_city_namet=(MyTextView)rootView.findViewById(R.id.ads_city_name);
        date_insertt=(MyTextView)rootView.findViewById(R.id.date_insert);
        ads_idt=(MyTextView)rootView.findViewById(R.id.ads_id);
        ads_visitedt=(MyTextView)rootView.findViewById(R.id.ads_visited);

        if(ads_category_name.contains("ar")){
            ads_category_namet.setText(ads_category_name.substring(20).replace("\";}", ""));

        }else {
            ads_category_namet.setText(ads_category_name);
        }



//        ads_category_namet.setText(ads_category_name);


        if(ads_type_name.contains("ar")){

            ads_type_namet.setText(ads_type_name.substring(20).replace("\";}", ""));

        }else {
            ads_type_namet.setText(ads_type_name);
        }
        if(ads_model_name.contains("ar")){
            ads_model_namet.setText(ads_model_name.substring(20).replace("\";}", ""));

        }else {

            ads_model_namet.setText(ads_model_name);
        }

        ads_pricet.setText(ads_price);
        ads_city_namet.setText(ads_city_name.substring(20).replace("\";}", ""));
        date_insertt.setText(date_insert);
        ads_idt.setText(ads_id);
        ads_visitedt.setText(ads_visited);

        return rootView ;

    }

//    @Override
//    public void onBackPressed() {
//        Log.d("CDA", "onBackPressed Called");
//        Intent setIntent = new Intent(Intent.ACTION_MAIN);
//        setIntent.addCategory(Intent.CATEGORY_HOME);
//        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(setIntent);
//    }

}
