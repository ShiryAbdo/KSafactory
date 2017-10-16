package aljentelhosting.ksaksafactory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.MyTextView;


public class AboutOFactory extends Fragment {

 String company_mobile,company_tel,company_fax,company_email,company_address,city_name;
    MyTextView companymobile,companytel,companyfax,companyemail,cityname,companyaddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_about_factory, container, false);
        company_mobile= getArguments().getString("company_mobile");
        company_tel= getArguments().getString("company_tel");
        company_fax= getArguments().getString("company_fax");
        company_email= getArguments().getString("company_email");
        company_address= getArguments().getString("company_address");
        city_name= getArguments().getString("city_name");

        companymobile=(MyTextView)rootView.findViewById(R.id.companymobile);
        companytel=(MyTextView)rootView.findViewById(R.id.companytel);
        companyfax=(MyTextView)rootView.findViewById(R.id.companyfax);
        companyemail=(MyTextView)rootView.findViewById(R.id.companyemail);
        cityname=(MyTextView)rootView.findViewById(R.id.cityname);
        companyaddress=(MyTextView)rootView.findViewById(R.id.companyaddress);

        companymobile.setText(company_mobile);
        companytel.setText(company_tel);
        companyfax.setText(company_fax);
        companyemail.setText(company_email);
        companyaddress.setText(company_address);
        if(city_name.contains("ar")){
            cityname.setText(city_name.substring(20).replace("\";}", ""));
        }else {
            cityname.setText(city_name);
        }




        return rootView;
    }

}
