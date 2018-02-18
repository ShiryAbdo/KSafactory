package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;


import java.util.Timer;
import java.util.TimerTask;

import aljentelhosting.ksaksafactory.R;

public class splashScreen extends AppCompatActivity {
    private ProgressBar mProgress;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        final String emailShared = sharedPref.getString("email", "null");
        final String passwordShared = sharedPref.getString("password", "null");
        final String typeShared = sharedPref.getString("type", "null");

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // Check if user is already logged in or not
                 if (!emailShared.equals("null")&&!passwordShared.equals("null")&&!typeShared.equals("null")) {
                     Intent intent = new Intent(splashScreen.this,
                             MainActivity.class);
                     startActivity(intent);
                     finish();

                }else {
                    Intent intent = new Intent(splashScreen.this,   LOgActivty.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);



//        Timer timer = new Timer();

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                splashScreen.this.runOnUiThread(new TimerTask() {
//                    @Override
//                    public void run() {
////
////                        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
////                        editor = sharedPref.edit();
////                        String emailShared = sharedPref.getString("email", "null");
////                        String passwordShared = sharedPref.getString("password", "null");
////                        String typeShared = sharedPref.getString("type", "null");
////
////                        if (!emailShared.equals("null")){
////                            checkLogin(emailShared, passwordShared, typeShared);
////
////                        }else {}
//
//                        Intent intent = new Intent(splashScreen.this,   LOgActivty.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//        }, 2000);









    }



}
