package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.CustomArrayAdapter;
import aljentelhosting.ksaksafactory.data.CustomArrayAdapter_Spinner;
import aljentelhosting.ksaksafactory.data.Factory_data;
import aljentelhosting.ksaksafactory.data.JSONResponse;
import aljentelhosting.ksaksafactory.data.JSONResponse_AddCatogery;
import aljentelhosting.ksaksafactory.data.RequestInterface_AddCatogery;
import aljentelhosting.ksaksafactory.data.ads_category_Data;
import aljentelhosting.ksaksafactory.interfaces.RequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Add_Ads_two extends AppCompatActivity {
    Spinner chose_part_dasteris ,chose_class_add,type_shope_in_dasters;
    EditText add_contentt;
    Button cuttongadding ;
    List<String> categories;
    String item_type_shope_in_dasters;
    String item_chose_class_add;
    String item_chose_part_dasteris;
    Bundle bundle;
    String add_title,add_price,add_content,image;
    byte[] byteArray;
    ArrayList<Factory_data> data;
    ArrayList<ads_category_Data> data_catogery;
    CustomArrayAdapter adapter ;
    CustomArrayAdapter_Spinner myAdaptor;
    ImageView go_back ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__ads_two);
        chose_part_dasteris =(Spinner)findViewById(R.id.chose_part_dasteris);
        chose_class_add =(Spinner)findViewById(R.id.chose_class_add);
        type_shope_in_dasters =(Spinner)findViewById(R.id.type_shope_in_dasters);
        add_contentt=(EditText)findViewById(R.id.add_content);
        cuttongadding=(Button)findViewById(R.id.cuttongadding);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Ads_two.this, Add_Ads.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });



        loadJSON();
        ads_category_loadJSON();
        bundle=getIntent().getExtras();


        if (bundle!=null){

            add_title= bundle.getString("add_title");
             add_price=bundle.getString("add_price");
            add_content=bundle.getString("add_content");
            image=bundle.getString("image");
//             byteArray= getIntent().getByteArrayExtra("image");
         }



        // Spinner Drop down elements
        categories= new ArrayList<>();
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);






        // Spinner click listener
        chose_part_dasteris.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
//                catogery_of_factory_item= parent.getItemAtPosition(position).toString();
                item_chose_part_dasteris = ((TextView)view.findViewById(R.id.num_offers_txt)).getText().toString().trim();

//                String item2 = ((TextView)view.findViewById(R.id.max_discount_txt)).getText().toString();
                // Showing selected spinner item
                //                Toast.makeText(parent.getContext(), item2, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        // Spinner click listener
        chose_class_add.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                item_chose_class_add = ((TextView)view.findViewById(R.id.num_offers_txt)).getText().toString().trim();

                // Showing selected spinner item

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // attaching data adapter to spinner


        // Spinner click listener
        type_shope_in_dasters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
                item_type_shope_in_dasters= ((TextView)view.findViewById(R.id.num_offers_txt)).getText().toString().trim();

                // Showing selected spinner item

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // attaching data adapter to spinner





        cuttongadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(add_contentt.getText().toString().trim().length() > 0){
                    Intent intent =new Intent(Add_Ads_two.this,Add_Ads_three.class);
                    intent.putExtra("add_title",add_title);
                    intent.putExtra("add_price",add_price);
                    intent.putExtra("add_content",add_content);
                    intent.putExtra("image",image);
                    intent.putExtra("item_type_shope_in_dasters",item_type_shope_in_dasters);
                    intent.putExtra("item_chose_class_add",item_chose_class_add);
                    intent.putExtra("item_chose_part_dasteris",item_chose_part_dasteris);
                    intent.putExtra("add_contentt",add_contentt.getText().toString());
//                    intent.putExtra("image",byteArray);
                    startActivity(intent);
                }


            }
        });


    }




    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));


                adapter = new CustomArrayAdapter(Add_Ads_two.this,
                        R.layout.customspinneritem, data);

                chose_part_dasteris.setAdapter(adapter);
                chose_class_add.setAdapter(adapter);
            }
            //////
            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }



    private void ads_category_loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface_AddCatogery request = retrofit.create(RequestInterface_AddCatogery.class);
        Call<JSONResponse_AddCatogery> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse_AddCatogery>() {
            @Override
            public void onResponse(Call<JSONResponse_AddCatogery> call, Response<JSONResponse_AddCatogery> response) {

                JSONResponse_AddCatogery jsonResponse = response.body();
                data_catogery = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));



                myAdaptor = new CustomArrayAdapter_Spinner(Add_Ads_two.this,
                        R.layout.customspinneritem, data_catogery);

                type_shope_in_dasters.setAdapter(myAdaptor);
            }
            //////
            @Override
            public void onFailure(Call<JSONResponse_AddCatogery> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }




    @Override
    public void onBackPressed() {
        Intent intent = new Intent( Add_Ads_two.this,   Add_Ads.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
