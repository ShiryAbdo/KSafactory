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
import aljentelhosting.ksaksafactory.data.My_Fav_Adaptor;
import aljentelhosting.ksaksafactory.data.My_Fav_Data;
import aljentelhosting.ksaksafactory.others.MyTextView;

public class My_Fav_Productes extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<My_Fav_Data> data;
    private My_Fav_Adaptor adapter ;
    ImageView go_back;
    Bundle bundle;
    String idi,id;
    MyTextView add_producte;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__fav__productes);
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
                Intent intent = new Intent(  My_Fav_Productes.this, AcountUser.class);
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

        String url= "http://ksafactory.com/API/my_fav/index.php?user_id="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new com.android.volley.Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                My_Fav_Data data = new My_Fav_Data();
                ArrayList<My_Fav_Data> data22= new ArrayList<>();

                try {
                    JSONArray company = response.getJSONArray("user");

                    for (int n = 0; n < company.length(); n++) {
                        JSONObject object = company.getJSONObject(n);
                        data= new My_Fav_Data(object.getString("fav_id"),
                                object.getString("product_id"),object.getString("product_title")
                                ,object.getString("product_service"),object.getString("date_insert"));

                        data22.add(data);

                    }


                    adapter = new My_Fav_Adaptor(data22,My_Fav_Productes.this);
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
        Intent intent = new Intent(  My_Fav_Productes.this, AcountUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
