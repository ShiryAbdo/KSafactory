package aljentelhosting.ksaksafactory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.MyTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutOFowner extends Fragment {

    String company_category_name,company_site,company_times;
    MyTextView catogaryCompany ,companytimes ,companysite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView =inflater.inflate(R.layout.fragment_about_owner, container, false);

        company_category_name= getArguments().getString("company_category_name");
        company_site= getArguments().getString("company_site");
        company_times= getArguments().getString("company_times");

        catogaryCompany=(MyTextView)rootView.findViewById(R.id.catogaryCompany);
        companytimes=(MyTextView)rootView.findViewById(R.id.companytimes);
        companysite=(MyTextView)rootView.findViewById(R.id.companysite);

        if(company_category_name.contains("ar")){
            catogaryCompany.setText(company_category_name.substring(20).replace("\";}", ""));
        }else {
            catogaryCompany.setText(company_category_name);
        }

        companytimes.setText(company_site);
        companysite.setText(company_times);




        return rootView;
    }

}
