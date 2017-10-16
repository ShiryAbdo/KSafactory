package aljentelhosting.ksaksafactory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.MyTextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElasfE3lanFragment extends Fragment {

    TextView ads_titlet , user_namet;
    String user_name;
    String ads_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_elasf_e3lan, container, false);

        user_name= getArguments().getString("user_name");
        ads_title= getArguments().getString("ads_title");
        ads_titlet=(MyTextView)rootView.findViewById(R.id.ads_title);
        user_namet=(MyTextView)rootView.findViewById(R.id.user_name);

        if(ads_title.contains("ar")){
            ads_titlet.setText(ads_title.substring(20).replace("\";}", ""));

        }else {
            ads_titlet.setText(ads_title);
        }

        user_namet.setText(user_name);



        return rootView;
    }

}
