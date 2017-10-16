package aljentelhosting.ksaksafactory.maps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
 import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.activites.MainActivity;
import aljentelhosting.ksaksafactory.activites.SoadyFactory;
import aljentelhosting.ksaksafactory.others.AppController;

public class CountinuAdding extends FragmentActivity implements OnMapReadyCallback ,LocationListener {

    private GoogleMap mMap;

    LatLng newpoint ;
    String title ;
    Button countinu ;
    Bundle bundle;

//    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    Context context ;
    LocationManager locationManager;
    String name_of_onwe,name_of_factory ,telphone_of_factory ,phone_numbe,email_adress ,what_is_producted;
    String city,facebook,twitter,instegrame,image ,latitude,longitude ,google,fax,site,category_id;



    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    public static final String KEY_user_id= "user_id";
    public static final String KEY_category= "category";


    public static final String KEY_name= "name";
    public static final String KEY_mobile= "name";
    public static final String KEY_tel = "name_ar";
    public static final String KEY_fax = "fax";
    public static final String KEY_email = "email";
    public static final String kEY_site ="site";
    public static final String kEY_cite ="site";


    public static final String kEY_address ="address";
    public static final String kEY_times ="times";
    public static final String kEY_facebook ="facebook";
    public static final String kEY_twitter ="twitter";
    public static final String kEY_google ="google";
    public static final String kEY_instegrame ="instegrame";


    public static final String kEY_image ="image";
    public static final String kEY_latitude ="latitude";
    public static final String kEY_longitude ="longitude";



    String myUrl,id;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. continue_add_factory);

        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        id= sharedPref.getString("id", null);

        bundle=getIntent().getExtras();
        if(bundle!=null){
            city=bundle.getString("city");
            facebook=bundle.getString("facebook");
            twitter=bundle.getString("twitter");
            instegrame=bundle.getString("instegrame");
            google=bundle.getString("google");
            fax=bundle.getString("fax");
            site=bundle.getString("site");
            image=bundle.getString("image");
            name_of_onwe=bundle.getString("name_of_onwe");
            name_of_factory=bundle.getString("name_of_factory");
            telphone_of_factory=bundle.getString("telphone_of_factory");
            phone_numbe=bundle.getString("phone_numbe");
            email_adress=bundle.getString("email_adress");
            what_is_producted=bundle.getString("what_is_producted");
            category_id=bundle.getString("category_id");
         }





//        Toast.makeText(CountinuAdding.this,telphone_of_factory, Toast.LENGTH_LONG).show();
//        Toast.makeText(CountinuAdding.this,id, Toast.LENGTH_LONG).show();
//        Toast.makeText(CountinuAdding.this,what_is_producted, Toast.LENGTH_LONG).show();
//        Toast.makeText(CountinuAdding.this,category_id, Toast.LENGTH_LONG).show();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
         context =  CountinuAdding.this ;
        displayPromptForEnablingGPS(CountinuAdding.this);
        countinu = (Button)findViewById(R.id.continu_add_factor);
        countinu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              if(category_id!=null){

                  AddFactory(id, category_id,name_of_onwe,phone_numbe,telphone_of_factory,fax,
                          email_adress,site,"adress",what_is_producted,city,facebook,twitter,google,
                          instegrame,"image",latitude,longitude);
              }






            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            //    here to request the missing permissions, and then overriding
            //    public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            //    to handle the case where the user grants the permission. See the documentation
            //    for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);

//        title= getGeocodeName(newLatitude,newLongitude);
    }

    public static void displayPromptForEnablingGPS(final Activity activity)
    {

        final AlertDialog.Builder builder =  new AlertDialog.Builder(activity);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Do you want open GPS setting?";

        builder.setMessage(message)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                activity.startActivity(new Intent(action));
                                d.dismiss();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface d, int id) {
                                d.cancel();
                            }
                        });
        builder.create().show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (android.support.v4.app.ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && android.support.v4.app.ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();
                double  newLatitude;
                double newLongitude ;

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title(getGeocodeName(point.latitude, point.longitude));
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(marker);
                //to add to data base

                newLatitude =point.latitude ;
                newLongitude =point.longitude ;
                newpoint= point;
                latitude=newLatitude+"";
                longitude=newLongitude+"";



             }        });

    }

    @Override
    public void onLocationChanged(Location location) {

//        mMap.clear();
        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLocation);
        markerOptions.title(getGeocodeName(location.getLatitude(),location.getLongitude()));


        // ROSE color icon
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10.0f));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10.0f));

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

//addresses == null ||
    public String getGeocodeName(double latitude, double longitude) {
        Context context = getApplicationContext();

        Geocoder geocoder = new Geocoder( context);
        List<Address> addresses = null;
        String unknown ="Unknown Location";
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return unknown;
        }
        if ( addresses == null ||addresses.size() == 0) {
            return unknown;
        }
        Address address = addresses.get(0);



        String cn = address.getCountryName();

        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName();
        String mainLocality = address.getSubAdminArea();

        return city + ", " + state+ ", " +country;
    }



    private void  AddFactory(final String user_id ,
                             final String category , final String name
            , final String mobile , final String tel,
                             final String fax , final String email,
                             final String site , final String adress,
                             final String times , final String city
            , final String facebook  , final String twitter,
                             final String google, final String instegrame,
                             final String image, final String latitude, final String longitude){



        Uri.Builder builder = new Uri.Builder();
//        "http://ksafactory.com/API/login/index.php?email=m@gmail.com&password=6666&type=1"
//        http://ksafactory.com/API/login/index.php


        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("add_to_company")
                .appendPath("index.php")
                .appendQueryParameter("user_id",id)
                .appendQueryParameter("category", category)
                .appendQueryParameter("name",name_of_onwe)
                .appendQueryParameter("mobile",phone_numbe)
                .appendQueryParameter("tel",telphone_of_factory)
                .appendQueryParameter("fax",fax)
                .appendQueryParameter("email",email_adress)
                .appendQueryParameter("site",site)
                .appendQueryParameter("address",adress)
                .appendQueryParameter("times",what_is_producted)
                .appendQueryParameter("city",city)
                .appendQueryParameter("facebook",facebook)
                .appendQueryParameter("twitter",twitter)
                .appendQueryParameter("google",google)
                .appendQueryParameter("instegrame",instegrame)
                .appendQueryParameter("image",image)
                .appendQueryParameter("latitude",latitude)
                .appendQueryParameter("longitude",longitude);
        myUrl= builder.build().toString();
        String tag_string_req = "req_register";




        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(CountinuAdding.this,response, Toast.LENGTH_LONG).show();
                        Log.d("CDA",  response);
                        Log.d("CDA",  "here");



                        Toast.makeText( CountinuAdding.this, "تم إضافة مصنع بنجاح",Toast.LENGTH_LONG).show();







                        startActivity(new Intent(getApplicationContext(),  MainActivity.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CountinuAdding.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }
                }){


            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_user_id,user_id);
                params.put(KEY_category,category);
                params.put(KEY_name,name);
                params.put(KEY_mobile,mobile);
                params.put(KEY_tel,tel);
                params.put(KEY_email,fax);
                params.put(KEY_fax,email);
                params.put(kEY_site,site);
                params.put(kEY_address,adress);
                params.put(kEY_times,times);
                params.put(kEY_cite,city);
                params.put(kEY_facebook,facebook);
                params.put(kEY_twitter,twitter);
                params.put(kEY_google,google);
                params.put(kEY_instegrame,instegrame);
                params.put(kEY_image,image);
                params.put(kEY_latitude,latitude);
                params.put(kEY_longitude,longitude);
                return params;
            }

        };






        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);



    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(CountinuAdding.this , SoadyFactory.class);

        startActivity(setIntent);
    }

}
