package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.AppController;

public class AddProducts extends AppCompatActivity {
    EditText title ,price,discount,service,freight,method,content;
    String myUrl;
    public static final String KEY_user_id = "user_id";
    public static final String KEY_product_id = "product_id";
    ImageView image ;
    Intent intent;
    String[] FILE;
    private static int IMG_RESULT = 1;
    String ImageDecode;
    Bitmap bitmap = null;
    String imageByte ,id_user,id_company;
    Button cuttongadding;
    Bundle bundle;;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        id_user= sharedPref.getString("id", null);


        bundle=getIntent().getExtras();


        if (bundle!=null){

            id_company= bundle.getString("company_id");
         }

         title=(EditText)findViewById(R.id.title);
        price=(EditText)findViewById(R.id.price);
        discount=(EditText)findViewById(R.id.discount);
        service=(EditText)findViewById(R.id.service);
        freight=(EditText)findViewById(R.id.freight);
        method=(EditText)findViewById(R.id.method);
        content=(EditText)findViewById(R.id.content);
        image=(ImageView)findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, IMG_RESULT);
            }
        });
        cuttongadding= (Button)findViewById(R.id.cuttongadding);
        cuttongadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( title.getText().toString().trim().length() > 0&&price.getText().toString().trim().length() > 0&&
                        discount.getText().toString().trim().length() > 0&&content.getText().toString().trim().length() > 0&&
                        service.getText().toString().trim().length() > 0&&freight.getText().toString().trim().length() > 0
                        &&method.getText().toString().trim().length() > 0){
                    AddTPrductio(check_the_EditText(title),check_the_EditText(price),
                            check_the_EditText(discount),check_the_EditText(content),imageByte
                            ,id_company,check_the_EditText(service),check_the_EditText(freight),check_the_EditText(method)
                            ,id_user);

                }else {
                    Toast.makeText(getApplicationContext(),
                            "أدخل البيانات" , Toast.LENGTH_SHORT).show();
                }

            }
        });




    }



    private void AddTPrductio(final String title , final String price  , final String discount , final String content
    , final String image, final String company , final String service , final String freight , final String method  , final String user_id )

    {



        Uri.Builder builder = new Uri.Builder();
//        http://ksafactory.com/API/add_product/index.php?title=hello&price=130
//          &discount=10%25&content=hahhahhahahahah&image=167
// &company=2&service=buying%20goods&freight=hahhahah&method=hahhayy&user_id=1
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("add_product")
                .appendPath("index.php")
                .appendQueryParameter("title",title)
                .appendQueryParameter("price", price)
         .appendQueryParameter("discount", discount)
         .appendQueryParameter("content", content)
          .appendQueryParameter("image", image)
         .appendQueryParameter("company", company)
         .appendQueryParameter("service", service)
         .appendQueryParameter("freight", freight)
         .appendQueryParameter("method", method)
         .appendQueryParameter("user_id", user_id);

        myUrl= builder.build().toString();
        String tag_string_req = "req_register";




        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {




                        Intent setIntent = new Intent(AddProducts.this, ProductesActivity.class);
                        setIntent.putExtra("company_id",id_company);
                        startActivity(setIntent);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddProducts.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("title",title);
                params.put(price,price);
                params.put("discount",discount);
                params.put("content",content);
                params.put("image",image);
                params.put("company",company);
                params.put("service",service);
                params.put("freight",freight);
                params.put("method",method);
                params.put("user_id",user_id);


                return params;
            }

        };






        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);



    }



    private String check_the_EditText(EditText Text){
        String the_returnData="";
        if(TextUtils.isEmpty(Text.getText().toString())){
            Text.setError("Requier");
        }else{
            the_returnData= Text.getText().toString()   ;

        }
        return the_returnData;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {


                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };


                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);

                cursor.moveToFirst();

                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), URI);
                imageByte=getStringImage(bitmap);
                image.setImageBitmap(bitmap);

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();
//
//                image.setImageBitmap(BitmapFactory
//                        .decodeFile(ImageDecode));

//                Toast.makeText(this, columnIndex+"", Toast.LENGTH_LONG)
//                        .show();

            }
        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }

    }


    public String getStringImage(Bitmap bitmap){
        Log.i("MyHitesh",""+bitmap);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);


        return temp;
    }

//
//    public String convertBitmapToString(Bitmap bmp){
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
//        byte[] byte_arr = stream.toByteArray();
//        String imageStr = Base64.encodeBytes(byte_arr);
//        return imageStr;
//    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(AddProducts.this, ProductesActivity.class);
        setIntent.putExtra("company_id",id_company);
        startActivity(setIntent);
    }

}
