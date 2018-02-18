package aljentelhosting.ksaksafactory.activites;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aljentelhosting.ksaksafactory.R;

public class LOgActivty extends AppCompatActivity {
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    EditText editText_email, editText2_password;
    Button loginbutton;
    CheckBox moasa_chekBox, singelCheckBox;
    public String username, emaili, passwordd, uid, type, myUrl;
    TextView forget_password, acount_new;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        String emailShared = sharedPref.getString("email", "null");
        String passwordShared = sharedPref.getString("password", "null");
        String typeShared = sharedPref.getString("type", "null");

//        if (!emailShared.equals("null")){
//            checkLogin(emailShared, passwordShared, typeShared);
//
//        }


        setContentView(R.layout.new_login);
        editText2_password = (EditText) findViewById(R.id.editText2_password);
        editText_email = (EditText) findViewById(R.id.editText_email);
        moasa_chekBox = (CheckBox) findViewById(R.id.moasa_chekBox);
        singelCheckBox = (CheckBox) findViewById(R.id.singelCheckBox);
        loginbutton = (Button) findViewById(R.id.loginbutton);




        loginbutton = (Button) findViewById(R.id.loginbutton);


        forget_password = (TextView) findViewById(R.id.forgetpassword);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LOgActivty.this,  ForgetPassword.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });
        acount_new = (TextView) findViewById(R.id.acount_new);
        forget_password.setPaintFlags(forget_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        acount_new.setPaintFlags(acount_new.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (moasa_chekBox.isChecked()) {
                    type = "2";
                }
                if (singelCheckBox.isChecked()) {
                    type = "1";
                }
                if(editText_email.getText().toString().trim().length() > 0&&editText2_password.getText().toString().trim().length() > 0){
                    checkLogin(check_the_EditText(editText_email), check_the_EditText(editText2_password), type);
                }else {
                    Toast.makeText(getApplicationContext(),
                            "أدخل الإيميل والباس ورد" , Toast.LENGTH_SHORT).show();
                }




            }
        });


        acount_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // close existing dialog fragments
                FragmentManager manager = getFragmentManager();
                Fragment frag = manager.findFragmentByTag("fragment_edit_name");
                if (frag != null) {
                    manager.beginTransaction().remove(frag).commit();
                }

                //                R.style.custom_dialog_theme

                // custom dialog
                final Dialog dialog = new Dialog(LOgActivty.this, R.style.custom_dialog_theme);
                dialog.setContentView(R.layout.custom);

                //                requestWindowFeature(Window.FEATURE_NO_TITLE);
                // set the custom dialog components - text, image and button
                Button as_moassa = (Button) dialog.findViewById(R.id.as_moassa);
                // if button is clicked, close the custom dialog
                as_moassa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), RegistrationCompany.class);
                        startActivity(intent);
                        //                        dialog.dismiss();
                    }
                });


                Button as_singel = (Button) dialog.findViewById(R.id.as_singel);
                // if button is clicked, close the custom dialog
                as_singel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), RegistrationUser.class);
                        startActivity(intent);
                        //                        dialog.dismiss();
                    }
                });

                //                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();


            }


        });

    }

    private void checkLogin(final String email, final String password, final String typee) {
        String url= "http://ksafactory.com/API/login/index.php"+"?email="+email+"&password="+password+"&type="+typee;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray user = response.getJSONArray("user");
                    // Error in login. Get the error message


                    for (int n = 0; n < user.length(); n++) {
                        JSONObject object = user.getJSONObject(n);
                        username = object.getString("username");
                        emaili = object.getString("email");
                        passwordd = object.getString("password");
                        uid = object.getString("user_id");
                        type = object.getString("type");

                    }
                    String su=response.getString("success");
                    if (su.equals("1")){


                        String id = uid;
                        editor.putString("username", username);
                        editor.putString("id", uid);
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.putString("type", typee);
                        editor.commit();


                        Intent intent = new Intent(LOgActivty.this,
                                MainActivity.class);
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
        });

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
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

}