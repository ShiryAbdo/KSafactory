package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.AppController;

public class SupportActivity extends AppCompatActivity {
    ImageView go_back ;
    EditText editText_nam , editText_phone, editText_email, editText_address,contain_massge;
    Button sed_button;
    String name,phone ,email ,address,massage ,myUrl;


    public static final String KEY_name = "name";
    public static final String KEY_phone = "mobile";
    public static final String KEY_email = "message_title";
    public static final String KEY_address = "message_title";
    public static final String kEY_massage ="content";
    String emailRegEx ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        editText_nam=(EditText)findViewById(R.id.editText_nam) ;
        editText_phone=(EditText)findViewById(R.id.editText_phone) ;
        editText_email=(EditText)findViewById(R.id.editText_email) ;
        editText_address=(EditText)findViewById(R.id.editText_address) ;
        contain_massge=(EditText)findViewById(R.id.contain_massge) ;



        sed_button=(Button) findViewById(R.id.sed_button) ;

        name= editText_nam.getText().toString().trim();
        phone= editText_phone.getText().toString().trim();
        email= editText_email.getText().toString().trim();
        address= editText_address.getText().toString().trim();
        massage= contain_massge.getText().toString().trim();
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";

        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportActivity.this,    MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        sed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText_nam.getText().toString().trim().length() > 0&&editText_phone.getText().toString().trim().length() > 0&&
                        editText_email.getText().toString().trim().length() > 0&&editText_address.getText().toString().trim().length() > 0
                        &&contain_massge.getText().toString().trim().length() > 0){


                    if (editText_email.getText().toString().matches(emailRegEx) && editText_email.getText().toString().length() > 0)
                    {

                        senData(check_the_EditText(editText_nam), check_the_EditText(editText_phone), check_the_EditText(editText_email),
                                check_the_EditText(editText_address),check_the_EditText(contain_massge));
                    }else {
                        Toast.makeText(getApplicationContext(),"الإيميل غير صالح", Toast.LENGTH_SHORT).show();
                    }







                }else {
                    Toast.makeText(getApplicationContext(),
                            "أدخل البيانات" , Toast.LENGTH_SHORT).show();
                }


            }
        });

    }




    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void  senData (final String namee, final String phonee,
                           final String emaile, final String adresse , final String massagee) {

        Uri.Builder builder = new Uri.Builder();
//        "http://ksafactory.com/API/login/index.php?email=m@gmail.com&password=6666&type=1"
//        http://ksafactory.com/API/login/index.php
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("contact_us")
                .appendPath("index.php")
                .appendQueryParameter("name",editText_nam.getText().toString().trim())
                .appendQueryParameter("mobile", editText_phone.getText().toString().trim())
                .appendQueryParameter("message_title",editText_address.getText().toString().trim())
                .appendQueryParameter("email",editText_email.getText().toString().trim())
                .appendQueryParameter("content",contain_massge.getText().toString().trim()) ;
         myUrl= builder.build().toString();
        String tag_string_req = "req_register";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText( SupportActivity.this,response, Toast.LENGTH_LONG).show();

//                        Toast.makeText( RegistrationUser.this, "this error",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SupportActivity.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_name,namee);
                params.put(KEY_phone,phonee);
                params.put(KEY_email, emaile);
                params.put(KEY_address,adresse);
                params.put(kEY_massage,massagee);
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
    public void onBackPressed() {
        Intent intent = new Intent(SupportActivity.this,    MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
