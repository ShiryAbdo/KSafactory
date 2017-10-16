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



import java.util.ArrayList;
import java.util.Arrays;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.Adaptor_Industrial_cities;
import aljentelhosting.ksaksafactory.data.IndustrialJSONResponse;
import aljentelhosting.ksaksafactory.data.Industrial_cities_Data;
import aljentelhosting.ksaksafactory.interfaces.Industrial_cities_Interface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Industrial_cities extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Industrial_cities_Data> data;
    private Adaptor_Industrial_cities adapter ;
    ImageView go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industrial_cities);

        initViews();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(  Industrial_cities.this, Show_Industrial_cities.class);
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


        loadJSON();
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Industrial_cities_Interface request = retrofit.create(Industrial_cities_Interface.class);
        Call<IndustrialJSONResponse> call = request.getJSON();
        call.enqueue(new Callback<IndustrialJSONResponse>() {
            @Override
            public void onResponse(Call<IndustrialJSONResponse> call, Response<IndustrialJSONResponse> response) {

                IndustrialJSONResponse  industrialJSONResponse = response.body();
                data = new ArrayList<>(Arrays.asList(industrialJSONResponse.getfactory()));
                adapter = new Adaptor_Industrial_cities(data, Industrial_cities.this,"Industrial_cities");
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<IndustrialJSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(  Industrial_cities.this, Show_Industrial_cities.class);
        startActivity(intent);
        finish();
    }

}
