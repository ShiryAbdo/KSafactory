package aljentelhosting.ksaksafactory.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.DataAdapter;
import aljentelhosting.ksaksafactory.data.Factory_data;


public class SearchWithName extends Fragment implements View.OnClickListener {

    LinearLayout search_cont1,search_cont2;
    Button search_btn1,search_btn2;
    EditText search_text1,search_text2;
    RecyclerView recyclerView ;
            ArrayList<Factory_data> datal;
    DataAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                View rootView =inflater.inflate(R.layout.fragment_search_with_name, container, false);
        search_btn1=(Button)rootView.findViewById(R.id.search_btn1);
        search_btn2=(Button)rootView.findViewById(R.id.search_btn2);
        search_text1=(EditText)rootView.findViewById(R.id.search_text1);
        search_text2=(EditText)rootView.findViewById(R.id.search_text2);
        search_cont1=(LinearLayout)rootView.findViewById(R.id.s_cont1);
        search_cont2=(LinearLayout)rootView.findViewById(R.id.s_cont2);
            search_btn1.setOnClickListener(this);
            search_btn2.setOnClickListener(this);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.search_rec);
        return rootView ;

    }


    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id){
            case R.id.search_btn1:
              get_Data(search_string());

                break;

            case R.id.search_btn2:

                get_Data(search_string());

                break;
        }
    }


    String search_string(){
        String search="";
        if(search_cont1.getVisibility()== View.VISIBLE){
            if (!TextUtils.isEmpty(search_text1.getText().toString())){
                search=search_text1.getText().toString();
            }
        }else {


            if (!TextUtils.isEmpty(search_text2.getText().toString())){
                search=search_text2.getText().toString();
            }
        }
        return  search;
    }





    void get_Data(final String sersh){


        StringRequest request=new StringRequest(Request.Method.POST,"http://api.learn2crack.com", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Log.e("regcheck",response);
                if (response.contains("done")){
                    data_decod(response);}
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), " Error while get the data  ", Toast.LENGTH_SHORT).show();
                Log.e("errore", String.valueOf( ">>" + error.getCause() + ">>" + error.getMessage()));

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("search_text",sersh);
                return params;
            }
        };
        Volley.newRequestQueue(getActivity()).add(request);
    }



    private void data_decod(String response){
        datal=new ArrayList<>();
        datal.clear();
        try {
            byte[] u = response.getBytes("ISO-8859-1");
            response = new String(u, "UTF-8");


            Log.e("logincheck",response);
            JSONObject job = new JSONObject(response);
            JSONArray jsonArray =job.getJSONArray("done");
            for (int i =0; i<jsonArray.length(); i++){
                JSONObject jobject=jsonArray.getJSONObject(i);
                String c_name =jobject.getString("c_name");
                String in_name=jobject.getString("in_name");
                String time   =jobject.getString("c_time");
                String descr  =jobject.getString("c_disce");
                String thephoto  =jobject.getString("photo");
                String photo= URLDecoder.decode(thephoto, "UTF-8");
                String link=jobject.getString("c_link");
                String c_link = URLDecoder.decode(link, "UTF-8");
                Factory_data
                        d=new Factory_data("a","v","c");
                datal.add(d);
            }

            //



        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.e("linkis", String.valueOf(datal.size()));

        adapter = new DataAdapter(datal,getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        search_cont1.setVisibility(View.GONE);
        search_cont2.setVisibility(View.VISIBLE);
    }
}
