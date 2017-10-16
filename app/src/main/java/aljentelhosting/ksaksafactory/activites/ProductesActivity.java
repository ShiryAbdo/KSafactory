package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
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
import aljentelhosting.ksaksafactory.data.Productes_Adaptor;
import aljentelhosting.ksaksafactory.data.Productes_data;
import aljentelhosting.ksaksafactory.others.MyTextView;

// this
public class ProductesActivity extends AppCompatActivity {
   private RecyclerView recyclerView;
   private ArrayList<Productes_data> data;
   private Productes_Adaptor adapter ;
   ImageView go_back ;
   Bundle bundle;
   MyTextView add_montage;
    public String idi ,image,company_category_name;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_productes);

//        if(bundle!=null){
//            idi=bundle.getString("company_id");
//
//        }

       idi=getIntent().getStringExtra("company_id");
       image=getIntent().getStringExtra("image");
       company_category_name=getIntent().getStringExtra("company_category_name");
       Toast.makeText(getApplicationContext(),idi, Toast.LENGTH_SHORT).show();

       add_montage=(MyTextView)findViewById(R.id.add_montage);

       Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);
       // back to main activity
       go_back =(ImageView)findViewById(R.id.go_back);
       go_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(  ProductesActivity.this, wasffWithTabb.class);
               intent.putExtra("company_id",idi);
               intent.putExtra("image",image);
               intent.putExtra("company_category_name",company_category_name);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
               startActivity(intent);
               finish();
           }
       });








       initViews();
       add_montage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(  ProductesActivity.this, AddProducts.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("company_id",idi);
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



       getAddsDtaied(idi);


   }






   private void  getAddsDtaied (final String id ) {

       String url=  "http://ksafactory.com/API/view_products/index.php?company="+id;
       JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
               , null, new com.android.volley.Response.Listener<JSONObject>() {

           @Override
           public void onResponse(JSONObject response) {
               Productes_data data = new Productes_data();
               ArrayList<Productes_data> data22= new ArrayList<>();

//                Log.e("regcheck",response);

               try {
                   JSONArray company = response.getJSONArray("product");

                   for (int n = 0; n < company.length(); n++) {


                       JSONObject object = company.getJSONObject(n);

                       String product_id =object.getString("product_id");
                       String product_title =object.getString("product_title");
                       String product_image_name =object.getString("product_image_name");
                       String product_service =object.getString("product_service");
                       String username =object.getString("product_id");
                       String date =object.getString("product_id");

       data= new Productes_data(product_id,product_title,product_image_name,product_service,username,date);

                       data22.add(data);


                   }




                   adapter = new Productes_Adaptor(data22,ProductesActivity.this,idi,company_category_name);
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
       Intent intent = new Intent(  ProductesActivity.this, wasffWithTabb.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
       finish();
   }

}




