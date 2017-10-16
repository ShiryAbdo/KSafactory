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
import aljentelhosting.ksaksafactory.data.Cites_stand_up_JSONResponse;
import aljentelhosting.ksaksafactory.data.cites_stand_up_Adaptor;
import aljentelhosting.ksaksafactory.data.cites_stand_up_data;
import aljentelhosting.ksaksafactory.interfaces.Cites_stand_up_Interface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cites_stand_upActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<cites_stand_up_data> data;
    private cites_stand_up_Adaptor adapter ;
    ImageView go_back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cites_stand_up);
        initViews();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cites_stand_upActivity.this, Part_Industrial_cities.class);
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


        loadJSON();
    }



    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Cites_stand_up_Interface request = retrofit.create(Cites_stand_up_Interface.class);
        Call<Cites_stand_up_JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<Cites_stand_up_JSONResponse>() {
            @Override
            public void onResponse(Call<Cites_stand_up_JSONResponse> call, Response<Cites_stand_up_JSONResponse> response) {

                Cites_stand_up_JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getfactory()));
                adapter = new cites_stand_up_Adaptor(data, Cites_stand_upActivity.this,"Cites_stand_upActivity");
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Cites_stand_up_JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Cites_stand_upActivity.this, Part_Industrial_cities.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
