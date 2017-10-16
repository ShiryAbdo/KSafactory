package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;


public class ForgetPassword extends AppCompatActivity {
    ImageView go_back ;
    EditText re_get_password,restpass,resetrepassword;
    Button re_get_butto;


    private String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPassword.this,  LOgActivty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        re_get_password=(EditText)findViewById(R.id.re_get_password);
        re_get_butto=(Button)findViewById(R.id.re_get_butto);


        restpass        =(EditText)findViewById(R.id.resetpass);
        resetrepassword =(EditText)findViewById(R.id.resetrepass);


        re_get_butto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (re_get_butto.getText().toString().trim().length() > 0){




                    email= check_the_EditText(re_get_password);

                    String rpass=check_the_EditText(resetrepassword);
                    String password=restpass.getText().toString();
                    if (rpass.equals(password)){
                        pass=password;
//                        restpassword(email,pass);
                        checkLogin(re_get_password.getText().toString().trim(),resetrepassword.getText().toString().trim());
                    }else{
                        Toast.makeText(ForgetPassword.this, "كلمه المرور وتأكيدها غير متطابقين ", Toast.LENGTH_SHORT).show();
                    }




                }else {

                    Toast.makeText(ForgetPassword.this, "إدخل البيانات", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void restpassword(final String email, final String password){
        String url="http://ksafactory.com/API/losspassword/index.php?email="+email+"&password="+password;

        StringRequest loginreq = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("done")){


                    Toast.makeText(ForgetPassword.this, "تم التعديل برجاء قم بتسجيل الدخول ", Toast.LENGTH_SHORT).show();



                }else  if(response.equals("errore")){

                    Toast.makeText(getApplicationContext(), "حدث خطأ اثنا التحديث برجاء المحوله مره اخرى ", Toast.LENGTH_SHORT).show();
                }else if(response.equals("wrong email")){
                    Toast.makeText(getApplicationContext(), "البريد الإلكترونى خاطئ", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };

        Volley.newRequestQueue(getApplicationContext()).add(loginreq);
    }





    private void checkLogin(final String email, final String password) {
        String url="http://ksafactory.com/API/losspassword/index.php?email="+email+"&password="+password;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {

                    String su=response.getString("success");
                    if (su.equals("1")){


                        Toast.makeText(getApplicationContext(),  "تم تغير البيانات" , Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent( ForgetPassword.this,
                                LOgActivty.class);
                        startActivity(intent);
                        finish();

                    }else {

                        Toast.makeText(getApplicationContext(),  "برجاء إدخال البيانات الصحيحة" , Toast.LENGTH_SHORT).show();
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        }){

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }

        };


        // Adding request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjReq);
        //        AppController.getInstance().addToRequestQueue(jsonObjReq);
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
        Intent intent = new Intent(ForgetPassword.this,  LOgActivty.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}