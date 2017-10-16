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
import aljentelhosting.ksaksafactory.data.AdaptorBank;
import aljentelhosting.ksaksafactory.data.BankJSONResponse;
import aljentelhosting.ksaksafactory.data.Bank_data;
import aljentelhosting.ksaksafactory.interfaces.ApiInterfaceBank;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BankActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Bank_data> data;
    private AdaptorBank adapter ;
    ImageView go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        initViews();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(  BankActivity.this, MainActivity.class);
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
        ApiInterfaceBank request = retrofit.create(ApiInterfaceBank.class);
        Call<BankJSONResponse> call = request.getJSON();
        call.enqueue(new Callback<BankJSONResponse>() {
            @Override
            public void onResponse(Call<BankJSONResponse> call, Response<BankJSONResponse> response) {
                data=new ArrayList<Bank_data>();
                BankJSONResponse bankJSONResponse = response.body();
                data = new ArrayList<>(Arrays.asList(bankJSONResponse.getBanks()));
                adapter = new AdaptorBank(data, BankActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<BankJSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(  BankActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
