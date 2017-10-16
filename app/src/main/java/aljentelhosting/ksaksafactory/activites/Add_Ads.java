package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;

public class Add_Ads extends AppCompatActivity {
    EditText add_title ,add_price,add_content;
    ImageView add_image ;
    Button cuttongadding;

    private String UPLOAD_URL ="http://ksafactory.com/files/frontend";
    Intent intent;    String[] FILE;
    String myUrl;
    private static int IMG_RESULT = 1;
    String ImageDecode,imageByte ;
    Bitmap bitmap = null;
     String mmm ,id;
    String myBase64Image;
    private String KEY_IMAGE = "image";
    private String KEY_NAME = "category";
    byte[] byteArray;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    private int PICK_IMAGE_REQUEST = 1;
    String image;
    String nameImage;
    byte [] b;
    String namOfIMage;
    ImageView go_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        id= sharedPref.getString("id", null);
        setContentView(R.layout.activity_add__ads);
        add_title=(EditText)findViewById(R.id.add_title);
        add_price=(EditText)findViewById(R.id.add_price);
        add_content=(EditText)findViewById(R.id.add_content);
        add_image=(ImageView)findViewById(R.id.add_image);
        cuttongadding=(Button)findViewById(R.id.cuttongadding);
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showFileChooser();


            }
        });


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Ads.this, AcountUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });



        cuttongadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (image!=null){
                    uploadImage();
                }




                Intent intent = new Intent(Add_Ads.this,Add_Ads_two.class);
                intent.putExtra("add_title",check_the_EditText(add_title));
                intent.putExtra("add_price", check_the_EditText(add_price));
                String add_contentt= check_the_EditText(add_content).trim();
                intent.putExtra("add_content",add_contentt);
                intent.putExtra("image","image");



                startActivity(intent);

            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                nameImage = String.valueOf(filePath);
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                add_image.setImageBitmap(bitmap);

                try {
                    image = getStringImage(bitmap);
//            nameImage = image.substring(0,5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }



    public String getStringImage(Bitmap bitmap) throws IOException {
         Log.i("MyHitesh",""+bitmap);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
         b= baos.toByteArray();
        nameImage = String.valueOf(b[1]);
        baos.close();

        String temp= Base64.encodeToString(b, Base64.DEFAULT);


        return temp;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent( Add_Ads.this,  AcountUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }



    private void uploadImage(){
        Uri.Builder builder = new Uri.Builder();


//        http://ksafactory.com/API/upload_images/index.php?image=Jellyfish.jpg&&category=2083
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("upload_images")
                .appendPath("index.php")
                .appendQueryParameter("image",image)
                .appendQueryParameter("category","2083");
        myUrl= builder.build().toString();
        //Showing the progress dialog
//        final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Disimissing the progress dialog
//                        loading.dismiss();
                        namOfIMage=s;
                        //Showing toast message of the response
                        Toast.makeText(Add_Ads.this, s, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
 //                        loading.dismiss();
                        volleyError.printStackTrace();

                        Toast.makeText(Add_Ads.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new Hashtable<String, String>();

                //Adding parameters
                params.put(KEY_IMAGE, image);
                params.put(KEY_NAME, "2083");

                //returning parameters
                return params;
            }
        };

        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }


    private String check_the_EditText(EditText Text){
        String the_returnData="null";
        if(TextUtils.isEmpty(Text.getText().toString())){
            Text.setError("Requier");
        }else{
            the_returnData= Text.getText().toString()   ;
        }
        return the_returnData;
    }


}
