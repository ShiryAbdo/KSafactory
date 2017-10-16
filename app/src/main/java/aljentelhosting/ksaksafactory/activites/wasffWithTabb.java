package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.fragments.AboutOFactory;
import aljentelhosting.ksaksafactory.fragments.AboutOFowner;
import aljentelhosting.ksaksafactory.fragments.MapWasfFactory;
import aljentelhosting.ksaksafactory.others.CustomTabLayout;
import aljentelhosting.ksaksafactory.others.MyTextView;

public class wasffWithTabb extends AppCompatActivity {
    MyTextView title ,show_productes;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private GoogleMap mMap;
    double  newLatitude;
    double newLongitude ;
    LatLng newpoint ;
    LocationManager locationManager;
    ImageView go_back ,image_wasf;

    Bundle bundle;
    String ads__id ,image,company_category_name;
    Bundle bundle2 ,bundle3 ,bundle4;
    String company_name,company_mobile,company_tel,company_fax,company_email,
            company_site,company_address,company_times,city_name,company_latitude,

    company_longitude,company_image_name;
    CustomTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wasff_with_tabb);

        bundle4= new Bundle();
        bundle2= new Bundle();
        bundle3= new Bundle();
        bundle=getIntent().getExtras();


        if (bundle!=null){

            ads__id= bundle.getString("company_id");
            image=bundle.getString("image");
            company_category_name=bundle.getString("company_category_name");
        }
        Toast.makeText(getApplicationContext(),ads__id, Toast.LENGTH_SHORT).show();



        title=(MyTextView)findViewById(R.id.wasf_titel);
        show_productes=(MyTextView)findViewById(R.id.show_productes);


        show_productes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ads__id= bundle.getString("company_id");
                Intent intent=new Intent( wasffWithTabb.this,ProductesActivity.class);
                intent.putExtra("company_id",bundle.getString("company_id"));
                intent.putExtra("image",bundle.getString("image"));
                intent.putExtra("company_category_name",bundle.getString("company_category_name"));

                startActivity(intent);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getAddsDtaied(ads__id);
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(wasffWithTabb.this,   AllFactores_Category.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        image_wasf=(ImageView)findViewById(R.id.image_wasf);
        if(image!=null){
//            Picasso.with( this).load(image).error(android.R.drawable.stat_notify_error).fit().into(image_wasf);

        }else {

//            image_wasf.setImageResource(R.drawable.bg_add_factory);
        }



         tabLayout = (CustomTabLayout) findViewById(R.id.tabs);



    }


    private void  getAddsDtaied (final String id ) {

        String url= "http://ksafactory.com/API/view_company_details/index.php?company_id="+id;
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url
                , null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
//
                try {
                    JSONArray ads = response.getJSONArray("company");


                    for (int n = 0; n < ads.length(); n++) {
                        JSONObject object = ads.getJSONObject(n);
                        company_name = object.getString("company_name");
                        company_mobile = object.getString("company_mobile");
                        company_tel = object.getString("company_tel");
                        company_fax = object.getString("company_fax");
                        company_email = object.getString("company_email");
                        company_site = object.getString("company_site");
                        company_address = object.getString("company_address");

                        company_times = object.getString("company_times");
                        city_name = object.getString("city_name");
//                        رقم الأعلان
                        company_latitude = object.getString("company_latitude");
//                        عدد المشاهداات
                        company_longitude = object.getString("company_longitude");
                        company_image_name = object.getString("company_image_name");



//                        if(ads_title.contains("ar")){
//                            wasf_titel.setText(ads_title.substring(20).replace("\";}", ""));
//
//
//                        }

///                    frist tab
                        bundle2.putString("company_category_name", company_category_name);
                        bundle2.putString("company_site", object.getString("company_site"));
                        bundle2.putString("company_times", object.getString("company_times"));
///                     second tab

                        bundle3.putString("company_mobile",object.getString("company_mobile"));
                        bundle3.putString("company_tel", object.getString("company_tel"));
                        bundle3.putString("company_fax", object.getString("company_fax"));
                        bundle3.putString("company_email", object.getString("company_email"));
                        bundle3.putString("company_address", object.getString("company_address"));
                        bundle3.putString("city_name", object.getString("city_name"));
///                     thred tab
                        bundle4.putString("company_latitude", object.getString("company_latitude"));
                        bundle4.putString("company_longitude", object.getString("company_longitude"));


                    }
                    String su=response.getString("success");
                    if (su.equals("1")){

                        title.setText(company_name);
                        if(company_image_name.equals("")){
                            image_wasf.setImageResource(R.drawable.bg_add_factory);

                        }else {
                            final String imagee ="http://ksafactory.com/files/frontend/"+company_image_name;
                            Picasso.with(getApplicationContext()).load(imagee).error(android.R.drawable.stat_notify_error).fit().into(image_wasf);
                        }

                        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                        // Set up the ViewPager with the sections adapter.
                        mViewPager = (ViewPager) findViewById(R.id.Tabcontainer);
                        mViewPager.setAdapter(mSectionsPagerAdapter);
                        tabLayout.setupWithViewPager(mViewPager);

                    }else {

//
//                        mViewPager = (ViewPager) findViewById(R.id.Tabcontainer);
//                        mViewPager.setAdapter(mSectionsPagerAdapter);
//                        tabLayout.setupWithViewPager(mViewPager);


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
                    AboutOFowner aboutOFowner = new AboutOFowner();
                    aboutOFowner.setArguments(bundle2);
                    return aboutOFowner;

                case 1:
                    AboutOFactory aboutOFactory = new AboutOFactory();
                    aboutOFactory.setArguments(bundle3);

                    return aboutOFactory;
                case 2:
                    MapWasfFactory  mapWasfFactory = new MapWasfFactory();
                    mapWasfFactory.setArguments(bundle4);

                    return mapWasfFactory;


            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:

                    return "نظرة عامة" ;

                case 1:
                    return  "تفاصيل الإتصال";


                case 2:
                    return  " الخريطه";


            }
            return null;
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(wasffWithTabb.this,   AllFactores_Category.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
