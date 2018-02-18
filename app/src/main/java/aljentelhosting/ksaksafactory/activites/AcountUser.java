package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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

public class AcountUser extends AppCompatActivity {
    TextView username ,outtig,fevourt_adds,flowers ,setting_acount,add_adds,my_adds,add_elmonshat,elmonshat_modafa;
    ImageView go_back ;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
//    String url = "http://ksafactory.com/API/my_account/index.php?user_id=20";
    String account_about,user_image,usernamee,emaili,user_mobile,user_gender,user_address ,id;
    Bundle bundle;
    String typeShared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_acount_user2);
        bundle= new Bundle();
        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        String usernameT = sharedPref.getString("username", null);
        String emailShared = sharedPref.getString("email", "null");
        id= sharedPref.getString("id", null);
        typeShared= sharedPref.getString("type", "null");

        username=(TextView)findViewById(R.id.username);

        outtig=(TextView)findViewById(R.id.outtig);
        fevourt_adds=(TextView)findViewById(R.id.fevourt_adds);
        flowers=(TextView)findViewById(R.id.flowers);
        setting_acount=(TextView)findViewById(R.id.setting_acount);
        add_adds=(TextView)findViewById(R.id.add_adds);
        my_adds=(TextView)findViewById(R.id.my_adds);
        add_elmonshat=(TextView)findViewById(R.id.add_elmonshat);
        elmonshat_modafa=(TextView)findViewById(R.id.elmonshat_modafa);
        if(typeShared.equals("1")){
            add_adds.setVisibility(View.VISIBLE);
            my_adds.setVisibility(View.VISIBLE);
        }else {
            add_adds.setVisibility(View.GONE);
            my_adds.setVisibility(View.GONE);
        }

        if(typeShared.equals("2")){
            add_elmonshat.setVisibility(View.VISIBLE);
            elmonshat_modafa.setVisibility(View.VISIBLE);
        }else {
            add_elmonshat.setVisibility(View.GONE);
            elmonshat_modafa.setVisibility(View.GONE);
        }



        username.setText(usernameT);
        checkMyAcoung(id);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AcountUser.this,  MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });



        setting_acount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UpdateAcountUser.class);
                intent.putExtra("account_about",account_about);
                intent.putExtra("user_name",usernamee);
                intent.putExtra("user_image", user_image);
                intent.putExtra("user_email", emaili);
                intent.putExtra("user_mobile", user_mobile);
                intent.putExtra("user_gender", user_gender);
                intent.putExtra("user_address", user_address);

                startActivity(intent);

            }
        });


        outtig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                editor = sharedPref.edit();
                editor.clear();
                editor.commit();


                Intent intent = new Intent(getApplicationContext(), LOgActivty.class);
                finish();
                startActivity(intent);
            }
        });
        flowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), FolwersActivity.class);
                intent.putExtra("id",id);
                finish();
                startActivity(intent);
            }
        });

        fevourt_adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), My_Fav_Productes.class);
                intent.putExtra("id",id);
                finish();
                startActivity(intent);
            }
        });


        my_adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAdsActivity.class);
                intent.putExtra("id",id);
                finish();
                startActivity(intent);

            }
        });

        add_elmonshat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addFactory.class);
                 finish();
                startActivity(intent);
            }
        });
        elmonshat_modafa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Elmonshat_modafa.class);
                intent.putExtra("id",id);
                finish();
                startActivity(intent);

            }
        });


        add_adds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Add_Ads.class);
                intent.putExtra("id",id);
                finish();
                startActivity(intent);
            }
        });


    }


    private void checkMyAcoung(final String id ){
        String url= "http://ksafactory.com/API/my_account/index.php?user_id="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray user = response.getJSONArray("user");
                    // Error in login. Get the error message


                    for (int n = 0; n < user.length(); n++) {
                        JSONObject object = user.getJSONObject(n);
                        account_about = object.getString("account_about");
                        usernamee = object.getString("user_name");
                        user_image = object.getString("user_image");
                        emaili = object.getString("user_email");
                        user_mobile = object.getString("user_mobile");
                        user_gender = object.getString("user_gender");
                        user_address = object.getString("user_address");

                    }
                    String su=response.getString("success");
                    if (su.equals("1")){



                        bundle.putString("account_about",account_about);
                        bundle.putString("user_name",usernamee);
                        bundle.putString("user_image", user_image);
                        bundle.putString("user_email", emaili);

                        bundle.putString("user_mobile", user_mobile);
                        bundle.putString("user_gender", user_gender);
                        bundle.putString("user_address", user_address);


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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AcountUser.this,  MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
