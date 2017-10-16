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
import aljentelhosting.ksaksafactory.data.Flower_Adaptor;
import aljentelhosting.ksaksafactory.data.Folwers_Data;

public class FolwersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Folwers_Data> data;
    private Flower_Adaptor adapter ;
    ImageView go_back;
    Bundle bundle;
    String idi,id;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folwers);
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
                Intent intent = new Intent(  FolwersActivity.this,  AcountUser.class);
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

        String url= "http://ksafactory.com/API/followers/index.php?user="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new com.android.volley.Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Folwers_Data data = new Folwers_Data();
                ArrayList<Folwers_Data> data22= new ArrayList<>();

                 try {
                    JSONArray company = response.getJSONArray("followers");

                    for (int n = 0; n < company.length(); n++) {
                        JSONObject object = company.getJSONObject(n);
                        data= new Folwers_Data(object.getString("user_id"),object.getString("username"),object.getString("date_insert"));

                        data22.add(data);

                    }


                    adapter = new Flower_Adaptor(data22,FolwersActivity.this);
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
        Intent intent = new Intent(  FolwersActivity.this,  AcountUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
