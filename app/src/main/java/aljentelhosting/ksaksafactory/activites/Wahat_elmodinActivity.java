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
import aljentelhosting.ksaksafactory.data.Wahat_elmodin_Adaptor;
import aljentelhosting.ksaksafactory.data.Wahat_elmodin_JSONResponse;
import aljentelhosting.ksaksafactory.data.Wahat_elmodin_data;
import aljentelhosting.ksaksafactory.interfaces.Wahat_elmodin_Interface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Wahat_elmodinActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Wahat_elmodin_data> data;
    private Wahat_elmodin_Adaptor adapter ;
    ImageView go_back ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wahat_elmodin);
        initViews();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Wahat_elmodinActivity.this, Part_Industrial_cities.class);
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
        Wahat_elmodin_Interface request = retrofit.create(Wahat_elmodin_Interface.class);
        Call<Wahat_elmodin_JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<Wahat_elmodin_JSONResponse>() {
            @Override
            public void onResponse(Call<Wahat_elmodin_JSONResponse> call, Response<Wahat_elmodin_JSONResponse> response) {

                Wahat_elmodin_JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getfactory()));
                adapter = new Wahat_elmodin_Adaptor(data, Wahat_elmodinActivity.this,"Wahat_elmodinActivity");
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Wahat_elmodin_JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Wahat_elmodinActivity.this, Part_Industrial_cities.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
