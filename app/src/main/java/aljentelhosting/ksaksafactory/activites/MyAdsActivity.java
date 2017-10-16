package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.MyAdsAdaptor;
import aljentelhosting.ksaksafactory.data.MyAds_Data;

public class MyAdsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<MyAds_Data> data;
    private MyAdsAdaptor adapter ;
    ImageView go_back;
    Bundle bundle;
    String idi,id;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);

        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        id= sharedPref.getString("id", null);

        initViews();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(  MyAdsActivity.this, AcountUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        bundle=getIntent().getExtras();


        if (bundle!=null){

            idi= bundle.getString("id");
        }
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        getAddsDtaied(id);

    }



    private void  getAddsDtaied (final String id ) {

        String url= "http://ksafactory.com/API/my_ads/index.php?user_id="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new com.android.volley.Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                MyAds_Data data = new MyAds_Data();
                ArrayList<MyAds_Data> data22= new ArrayList<>();

                try {
                    JSONArray company = response.getJSONArray("ads");

                    for (int n = 0; n < company.length(); n++) {
                        JSONObject object = company.getJSONObject(n);
                        data= new MyAds_Data(object.getString("ads_id"),object.getString("ads_title"),
                                object.getString("ads_category_name"),object.getString("ads_price"),
                                object.getString("ads_image_name"),object.getString("ads_city_name"));

                        data22.add(data);

                    }


                    adapter = new MyAdsAdaptor(data22,MyAdsActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();





                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(  MyAdsActivity.this, AcountUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
