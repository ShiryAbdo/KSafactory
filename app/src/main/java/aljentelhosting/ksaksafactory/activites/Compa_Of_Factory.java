package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import java.util.Arrays;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.Compa_Of_Factory_Adaptor;
import aljentelhosting.ksaksafactory.data.Compa_Of_Factory_Data;
import aljentelhosting.ksaksafactory.data.Compa_Of_Factory_JSONRespons;
import aljentelhosting.ksaksafactory.interfaces.Compa_Of_Factory_Data_tInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Compa_Of_Factory extends AppCompatActivity {
    private RecyclerView recyclerView;
    ArrayList<Compa_Of_Factory_Data> data,data2;
    private Compa_Of_Factory_Adaptor adapter ;
    ImageView go_back ;
    Bundle bundle;
    String company_category_id,company_category_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compa__of__factory);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            company_category_id=bundle.getString("company_category_id");
            company_category_name=bundle.getString("company_category_name");

        }
        getAddsDtaied(company_category_id);
        initViews();
//        data2=new ArrayList<>();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Compa_Of_Factory.this, AllFactores_Category.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }



    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);





//        loadJSON();
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Compa_Of_Factory_Data_tInterface request = retrofit.create(Compa_Of_Factory_Data_tInterface.class);
        Call<Compa_Of_Factory_JSONRespons> call = request.getJSON();
        call.enqueue(new Callback<Compa_Of_Factory_JSONRespons>() {
            @Override
            public void onResponse(Call<Compa_Of_Factory_JSONRespons> call, Response<Compa_Of_Factory_JSONRespons> response) {

                Compa_Of_Factory_JSONRespons jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getcompany()));
                adapter = new Compa_Of_Factory_Adaptor(data,Compa_Of_Factory.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Compa_Of_Factory_JSONRespons> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }



    private void  getAddsDtaied (final String id ) {

        String url= "http://ksafactory.com/API/company_by_category/index.php?category="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new com.android.volley.Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
               Compa_Of_Factory_Data data = null;
                ArrayList<Compa_Of_Factory_Data> data22= new ArrayList<>();

//                Compa_Of_Factory_Data data= new  Compa_Of_Factory_Data();
                try {
                    JSONArray company = response.getJSONArray("company");

                    for (int n = 0; n < company.length(); n++) {
                        JSONObject object = company.getJSONObject(n);

                     data= new  Compa_Of_Factory_Data(object.getString("company_id"),
                     object.getString("company_name"),object.getString("company_image_name"),company_category_name);
//                           data.setCompany_id(object.getString("company_id"));
//                        data.setCompany_name(object.getString("company_name"));
//                        data.setCompany_image_name(object.getString("company_image_name"));
                        data22.add(data);

                    }


                    adapter = new Compa_Of_Factory_Adaptor(data22,Compa_Of_Factory.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();





                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
        //        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Compa_Of_Factory.this, AllFactores_Category.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
