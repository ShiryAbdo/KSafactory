package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class UpdateAcountUser extends AppCompatActivity {
    ImageView go_back ;
    EditText editText_nam,editText_phone,editText_email,editText_address,contain_massge;
    SharedPreferences sharedPref;
    String usernameT ,emailShared,passwordShared,typeShared,id;
    String username,email,passwordd,uid,type;
    Bundle bundle;
    String account_about,user_image,usernamee,emaili,user_mobile,user_gender,user_address ,idi;
    String myUr;
    Button sed_button;
    String emailRegEx ;

    public static final String KEY_user_id = "user_id";
    public static final String KEY_username = "username";
    public static final String KEY_email= "email";
    public static final String KEY_phone = "phone";
    public static final String kEY_about ="about";

    public static final String kEY_country ="country";
    public static final String kEY_city ="city";
    public static final String kEY_address ="address";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount_user);
        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        usernameT = sharedPref.getString("username", null);
        id= sharedPref.getString("id", null);
         emailShared = sharedPref.getString("email", "null");
         passwordShared = sharedPref.getString("password", "null");
         typeShared = sharedPref.getString("type", "null");


        bundle=getIntent().getExtras();





        if (bundle!=null){

            account_about= bundle.getString("account_about");
            usernamee=bundle.getString("user_name");
            user_image= bundle.getString("user_image");
            emaili=bundle.getString("user_email");
            user_mobile= bundle.getString("user_mobile");
            user_gender=bundle.getString("user_gender");
            user_address=bundle.getString("user_address");

        }
        editText_nam=(EditText)findViewById(R.id.editText_nam);
        editText_phone=(EditText)findViewById(R.id.editText_phone);
        editText_email=(EditText)findViewById(R.id.editText_email);
        editText_address=(EditText)findViewById(R.id.editText_address);
        contain_massge=(EditText)findViewById(R.id.contain_massge);

        editText_nam.setText(usernamee);
        editText_phone.setText(user_mobile);
        editText_email.setText(emaili);
        editText_address.setText(user_address);
        contain_massge.setText("");

        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";


        sed_button=(Button)findViewById(R.id.sed_button);
        sed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText_nam.getText().toString().trim().length() > 0
                        &&editText_email.getText().toString().trim().length() > 0&&
                        editText_phone.getText().toString().trim().length() > 0&&
                        contain_massge.getText().toString().trim().length() > 0&&
                        editText_address.getText().toString().trim().length() > 0
                        ){



                    if (editText_email.getText().toString().matches(emailRegEx) && editText_email.getText().toString().length() > 0)
                    {



                        UpdateAcountUserA(id,editText_nam.getText().toString(),
                                editText_email.getText().toString(),editText_phone.getText().toString(),contain_massge.getText().toString(),
                                "countery","city",editText_address.getText().toString());
                    }else {

                        Toast.makeText(getApplicationContext(),"الإيميل غير صالح", Toast.LENGTH_SHORT).show();
                    }



                }else {

                    Toast.makeText(getApplicationContext(),
                            "أدخل البيانات" , Toast.LENGTH_SHORT).show();
                }

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

                Intent intent = new Intent(UpdateAcountUser.this,  AcountUser.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }



    // http://ksafactory.com/API/edit_my_account/index.php?user_id=28&username=hekmatmohamed&
    // email=hekmatmohamed%40gmail.com&


    // phone=019918818&about=hello&country=1316&city=18&address=egypt
    private void UpdateAcountUserA (final String user_id
            , final String username , final String email
            , final String phone , final String about, final String country , final String city,
                                    final String address
            ){







        Uri.Builder builder = new Uri.Builder();
//        "http://ksafactory.com/API/login/index.php?email=m@gmail.com&password=6666&type=1"
//        http://ksafactory.com/API/login/index.php
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("edit_my_account")
                .appendPath("index.php")
                .appendQueryParameter("user_id",user_id)
                .appendQueryParameter("username", username)
                .appendQueryParameter("email",email)
                .appendQueryParameter("email",email)
                .appendQueryParameter("phone",phone)
                .appendQueryParameter("about",about)
                .appendQueryParameter("country",country)
                .appendQueryParameter("city",city)
                .appendQueryParameter("address",address);
              myUr= builder.build().toString();
        String tag_string_req = "req_register";




        StringRequest stringRequest = new StringRequest(Request.Method.GET, myUr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText( UpdateAcountUser.this,"تم تعديل الحساب بنجاح", Toast.LENGTH_LONG).show();

//                        Toast.makeText( RegistrationUser.this, "this error",Toast.LENGTH_LONG).show();


                        startActivity(new Intent(getApplicationContext(),    LOgActivty.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateAcountUser.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }



                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_user_id,user_id);
                params.put(KEY_username,username);
                params.put(KEY_email,email);
                params.put(KEY_email,email);
                params.put(KEY_phone,phone);
                params.put(kEY_about,about);
                params.put(kEY_country,country);
                params.put(kEY_city,city);
                params.put(kEY_address,address);

                return params;
            }

        };






        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);



    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(UpdateAcountUser.this,  AcountUser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}
