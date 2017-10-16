package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.android.volley.toolbox.Volley;
 import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.fragments.A3lanatDetails;
import aljentelhosting.ksaksafactory.fragments.ElasfE3lanFragment;
import aljentelhosting.ksaksafactory.fragments.MapWasfFactory;
import aljentelhosting.ksaksafactory.others.MyTextView;

public class A3lanActivityItem extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    Bundle bundle;
    String ads__id ,image;
    Bundle bundle2 ,bundle3 ,bundle4;
    private ViewPager mViewPager;
    ImageView go_back ;
    MyTextView wasf_titel;
    String user_name ,ads_title,ads_image_name,ads_category_name
            ,ads_type_name,ads_model_name,ads_price,ads_city_name
            ,date_insert,ads_id,ads_visited,ads_latitude,ads_longitude ;
    ImageView imagE3lan;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityv);

        bundle4= new Bundle();
        bundle2= new Bundle();
        bundle3= new Bundle();
        bundle=getIntent().getExtras();


        if (bundle!=null){

            ads__id= bundle.getString("ads_id");
            image=bundle.getString("image");
        }
        getAddsDtaied(ads__id);


        wasf_titel=(MyTextView)findViewById(R.id.wasf_titel);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);



        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A3lanActivityItem.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        imagE3lan=(ImageView)findViewById(R.id.imagE3lan);
        Picasso.with( this).load(image).error(android.R.drawable.stat_notify_error).fit().into(imagE3lan);
// if (ads_longitude!=null){



         tabLayout = (TabLayout) findViewById(R.id.tabs);







    }

    private void  getAddsDtaied (final String id ) {

        String url= "http://ksafactory.com/API/view_ads_details/index.php?ads_id="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//
                try {
                    JSONArray ads = response.getJSONArray("ads");


                    for (int n = 0; n < ads.length(); n++) {
                        JSONObject object = ads.getJSONObject(n);
                        user_name = object.getString("user_name");
                        ads_title = object.getString("ads_title");
                        ads_image_name = object.getString("ads_image_name");
                        ads_category_name = object.getString("ads_category_name");
                        ads_type_name = object.getString("ads_type_name");
                        ads_model_name = object.getString("ads_model_name");
                        ads_price = object.getString("ads_price");

                        ads_city_name = object.getString("ads_city_name");
                        date_insert = object.getString("date_insert");
//                        رقم الأعلان
                        ads_id = object.getString("ads_id");
//                        عدد المشاهداات
                        ads_visited = object.getString("ads_visited");
                        ads_latitude = object.getString("ads_latitude");
                        ads_longitude = object.getString("ads_longitude");



                        if(ads_title.contains("ar")){
                            wasf_titel.setText(ads_title.substring(20).replace("\";}", ""));

                        }else {
                            wasf_titel.setText(ads_title);
                        }

                        bundle2.putString("ads_category_name", object.getString("ads_category_name"));
                        bundle2.putString("ads_type_name",object.getString("ads_type_name"));
                        bundle2.putString("ads_model_name", object.getString("ads_model_name"));
                        bundle2.putString("ads_price", object.getString("ads_price"));
                        bundle2.putString("date_insert", object.getString("date_insert"));
                        bundle2.putString("ads_city_name", object.getString("ads_city_name"));
                        bundle2.putString("ads_id", object.getString("ads_id"));
                        bundle2.putString("ads_visited", object.getString("ads_visited"));

                        bundle3.putString("user_name",object.getString("user_name"));
                        bundle3.putString("ads_title",object.getString("ads_title"));

                        bundle4.putString("company_latitude", object.getString("ads_latitude"));
                        bundle4.putString("company_longitude", object.getString("ads_longitude"));


                    }
                    String su=response.getString("success");
                    if (su.equals("1")){

                        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                        // Set up the ViewPager with the sections adapter.
                        mViewPager = (ViewPager) findViewById(R.id.Tabcontainer);
                        mViewPager.setAdapter(mSectionsPagerAdapter);

                        mSectionsPagerAdapter.notifyDataSetChanged();
                        tabLayout.setupWithViewPager(mViewPager);


                    }

                    if(ads_title.contains("ar")){
                        wasf_titel.setText(ads_title.substring(20).replace("\";}", ""));

                    }else {
                        wasf_titel.setText(ads_title);
                    }

//                    bundle2.putString("ads_category_name", ads_category_name);
//                    bundle2.putString("ads_type_name", ads_type_name);
//                    bundle2.putString("ads_model_name", ads_model_name);
//                    bundle2.putString("ads_price", ads_price);
//                    bundle2.putString("ads_city_name", date_insert);
//                    bundle2.putString("ads_id", ads_id);
//                    bundle2.putString("ads_visited", ads_visited);
//
//                    Toast.makeText(getApplicationContext(),
//                            "ceart acount", Toast.LENGTH_SHORT).show();


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








    /**
     * A placeholder fragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ElasfE3lanFragment elasfE3lanFragment = new ElasfE3lanFragment();
                    elasfE3lanFragment.setArguments(bundle3);

                    return elasfE3lanFragment;
                case 1:

                    A3lanatDetails a3lanatDetails =new A3lanatDetails();
                    a3lanatDetails.setArguments(bundle2);


                      return a3lanatDetails;

                case 2:
                    MapWasfFactory mapWasfFactory = new MapWasfFactory();
                    mapWasfFactory.setArguments(bundle4);
                    return mapWasfFactory;

            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3  ;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return " الوصف";
                case 1:
                    return " المواصفات ";
                case 2:
                    return "الخريطه";

            }
            return null;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(A3lanActivityItem.this, e3lanatActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
