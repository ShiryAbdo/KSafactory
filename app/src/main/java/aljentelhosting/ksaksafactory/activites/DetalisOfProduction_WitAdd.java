package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.Folwers_Data;
import aljentelhosting.ksaksafactory.others.AppController;
import aljentelhosting.ksaksafactory.others.MyTextView;

public class DetalisOfProduction_WitAdd extends AppCompatActivity {


    ImageView go_back;
    Bundle bundle;
    String image_name,title,price,discount,discount_date,content,freight,service,method,close;
    MyTextView product_title,product_price,product_discount,product_discount_date,product_content,
            product_freight,product_service,product_method,titel,add_from_my_fav;
    ImageView product_image_name;
    String idi,id,company_id,company_category_name;
    String myUrl;
    public static final String KEY_product_id = "product_id";
    public static final String KEY_user_id = "user_id";

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis_of_production__wit_add);
        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        id= sharedPref.getString("id", null);
        company_id=getIntent().getStringExtra("company_id");
        company_category_name=getIntent().getStringExtra("company_category_name");
        bundle=getIntent().getExtras();
        Toast.makeText(getApplicationContext(),company_id, Toast.LENGTH_SHORT).show();


        if (bundle!=null){

            idi= bundle.getString("id");
        }


//        product_title=(MyTextView)findViewById(R.id.product_title);
        product_price=(MyTextView)findViewById(R.id.product_price);
        product_discount=(MyTextView)findViewById(R.id.product_discount);
        product_discount_date=(MyTextView)findViewById(R.id.product_discount_date);
        product_content=(MyTextView)findViewById(R.id.product_content);
        product_freight=(MyTextView)findViewById(R.id.product_freight);
        product_service=(MyTextView)findViewById(R.id.product_service);
        product_method=(MyTextView)findViewById(R.id.product_method);
        product_image_name=(ImageView)findViewById(R.id.product_image_name);
        titel=(MyTextView)findViewById(R.id.titel);
        add_from_my_fav=(MyTextView)findViewById(R.id.add_from_my_fav);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(  DetalisOfProduction_WitAdd.this, ProductesActivity.class);
                 intent.putExtra("company_id", getIntent().getStringExtra("company_id"));
                intent.putExtra("company_category_name",getIntent().getStringExtra("company_category_name"));
                 startActivity(intent);
                finish();
            }
        });


        getProductDtaied(idi);


        add_from_my_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AddTofEV(idi,id);

            }
        });
    }

    private void  getProductDtaied (final String id ) {

        String url= "http://ksafactory.com/API/view_product_details/index.php?product_id="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new com.android.volley.Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Folwers_Data data = new Folwers_Data();
                ArrayList<Folwers_Data> data22= new ArrayList<>();

                try {
                    JSONArray company = response.getJSONArray("product");

                    for (int n = 0; n < company.length(); n++) {
                        JSONObject object = company.getJSONObject(n);
                        product_price.setText(object.getString("product_price"));
                        product_discount.setText(object.getString("product_discount"));
                        product_discount_date.setText(object.getString("product_discount_date"));
                        product_content.setText(object.getString("product_content"));
                        product_freight.setText(object.getString("product_freight"));
                        product_service.setText(object.getString("product_service"));
                        product_method.setText(object.getString("product_method"));
                        String imageUr ="http://ksafactory.com/files/frontend/"+object.getString("product_image_name");
                        Picasso.with(DetalisOfProduction_WitAdd.this).load(imageUr).error(android.R.drawable.stat_notify_error).fit().into(product_image_name);
                        titel.setText(object.getString("product_title"));


//                        data= new Folwers_Data(object.getString("user_id"),object.getString("username"),object.getString("date_insert"));


                    }







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

    private void AddTofEV(final String product_id , final String user_id)
    {



        Uri.Builder builder = new Uri.Builder();
//        "http://ksafactory.com/API/login/index.php?email=m@gmail.com&password=6666&type=1"
//        http://ksafactory.com/API/login/index.php
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("add_to_fav")
                .appendPath("index.php")
                .appendQueryParameter("product_id",product_id)
                .appendQueryParameter("user_id", user_id);

        myUrl= builder.build().toString();
        String tag_string_req = "req_register";




        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DetalisOfProduction_WitAdd.this, "تمت الإضافه الي المفضله", Toast.LENGTH_LONG).show();

//                        Toast.makeText( RegistrationUser.this, "this error",Toast.LENGTH_LONG).show();


//                        startActivity(new Intent(getApplicationContext(),  MainActivity.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetalisOfProduction_WitAdd.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_product_id,product_id);
                params.put(KEY_user_id,user_id);


                return params;
            }

        };






        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);



    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent( DetalisOfProduction_WitAdd.this,  ProductesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
