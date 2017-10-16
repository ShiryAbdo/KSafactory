package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.Arrays;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.DataAdapter_search_company;
import aljentelhosting.ksaksafactory.data.search_company_Data;
import aljentelhosting.ksaksafactory.data.search_company_JSONResponse;
import aljentelhosting.ksaksafactory.interfaces.search_company_RequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchWithNameActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://ksafactory.com/API/";
    private RecyclerView mRecyclerView;
    private ArrayList<search_company_Data> mArrayList;
    private DataAdapter_search_company mAdapter;


    LinearLayout search_cont1,search_cont2;
    Button search_btn1,search_btn2;
    EditText search_text1,search_text2;
    ImageView go_back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_with_name);



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchWithNameActivity.this, SoadyFactory.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        initViews();
        loadJSON();
    }

    private void initViews(){

        search_btn1=(Button)findViewById(R.id.search_btn1);
        search_btn2=(Button)findViewById(R.id.search_btn2);
        search_text1=(EditText)findViewById(R.id.search_text1);
        search_text2=(EditText)findViewById(R.id.search_text2);
        search_cont1=(LinearLayout)findViewById(R.id.s_cont1);
        search_cont2=(LinearLayout)findViewById(R.id.s_cont2);

        search_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                search_cont1.setVisibility(View.GONE);
                search_cont2.setVisibility(View.VISIBLE);
                search_text1.getText().toString();
                mAdapter.getFilter().filter(search_text1.getText().toString());




            }
        });
        search_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_text2.getText().toString();
                loadJSON();
                mAdapter.getFilter().filter(search_text1.getText().toString());


            }
        });
        mRecyclerView = (RecyclerView)findViewById(R.id.search_rec);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        search_company_RequestInterface request = retrofit.create(search_company_RequestInterface.class);
        Call<search_company_JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<search_company_JSONResponse>() {
            @Override
            public void onResponse(Call<search_company_JSONResponse> call, Response<search_company_JSONResponse> response) {

                search_company_JSONResponse jsonResponse = response.body();
                 mArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getcompany()));
                 mAdapter = new DataAdapter_search_company(mArrayList);
                 mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<search_company_JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchWithNameActivity.this, SoadyFactory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
