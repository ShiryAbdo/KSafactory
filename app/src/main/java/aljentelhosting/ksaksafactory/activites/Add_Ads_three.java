package aljentelhosting.ksaksafactory.activites;

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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import aljentelhosting.ksaksafactory.others.AppController;


public class Add_Ads_three extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    String item_type_shope_in_dasters;
    String item_chose_class_add;
    String item_chose_part_dasteris;
    Bundle bundle;
    String add_title,add_price,add_content,image,add_contentt,latitude,longitude ;


    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    private GoogleMap mMap;
    double  newLatitude;
    double newLongitude ;
    LatLng newpoint ;
    String myUrl,id;
    String title ;
    Button countinu ;
     //    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    Context context ;
    LocationManager locationManager;
    ImageView go_back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__ads_three);


        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        id= sharedPref.getString("id", null);


        bundle=getIntent().getExtras();


        if (bundle!=null){

            add_title= bundle.getString("add_title");
            add_price=bundle.getString("add_price");
            add_content=bundle.getString("add_content");
            image=bundle.getString("image");
            item_type_shope_in_dasters=bundle.getString("item_type_shope_in_dasters");
            item_chose_class_add=bundle.getString("item_chose_class_add");
            item_chose_part_dasteris=bundle.getString("item_chose_part_dasteris");
            add_contentt=bundle.getString("add_contentt");
        }


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
         // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Add_Ads_three.this, Add_Ads_two.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context =  Add_Ads_three.this ;
        displayPromptForEnablingGPS(Add_Ads_three.this);
        countinu = (Button)findViewById(R.id.continu_add_factor);
        countinu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

  if (id!=null&&add_title!=null&&add_price!=image&&item_chose_class_add!=null
          &&item_type_shope_in_dasters!=null&&item_chose_part_dasteris!=null&&latitude!=null
          &&longitude!=null&&add_content!=null&&add_contentt!=null){

      Add_Adds(id,add_title,add_price,image, item_chose_class_add,item_type_shope_in_dasters,
              item_chose_part_dasteris,latitude,longitude,add_content,add_contentt);

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

                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title(getGeocodeName(point.latitude, point.longitude));
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                mMap.addMarker(marker);
                //to add to data base
                newLatitude =point.latitude ;
                newLongitude =point.longitude ;
                newpoint= point;
                latitude=newLatitude+"";
                longitude=newLatitude+"";



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



//     http://ksafactory.com/API/add_to_ads/index.php?
//     user_id=1&ads_title=hellooo&ads_price=%D8%AD%D8%B3%D8%A8%20%D8%A7%D9%84%D8%A7%D8%AA%D9%81%D8%A7%D9%82
//     &ads_image=165&ads_category=2006&ads_type=2100&ads_model=2131&latitude=24.53002396162187
//     &longitude=46.7277717590332&ads_content=hhahhahhahahaahhaahhaha&contact=01991991


    private void  Add_Adds(final String user_id ,
                             final String ads_title ,
                             final String ads_price
                             , final String ads_image ,
                             final String ads_category,
                             final String ads_type ,
                             final String ads_model,
                             final String latitude ,
                             final String longitude,
                             final String ads_content,
                             final String contact
                             ){



        Uri.Builder builder = new Uri.Builder();
//        "http://ksafactory.com/API/login/index.php?email=m@gmail.com&password=6666&type=1"
//        http://ksafactory.com/API/login/index.php
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("add_to_ads")
                .appendPath("index.php")
                .appendQueryParameter("user_id",user_id)
                .appendQueryParameter("ads_title", ads_title)
                .appendQueryParameter("ads_price",ads_price)
                .appendQueryParameter("ads_image",ads_image)
                .appendQueryParameter("ads_category",ads_category)
                .appendQueryParameter("ads_type",ads_type)
                .appendQueryParameter("ads_model",ads_model)
                .appendQueryParameter("latitude",latitude)
                .appendQueryParameter("longitude",longitude)
                .appendQueryParameter("ads_content",ads_content)
                .appendQueryParameter("contact",contact);
        myUrl= builder.build().toString();
        String tag_string_req = "req_register";




        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText( Add_Ads_three.this, "تم إضافه الإعلان", Toast.LENGTH_LONG).show();


                        startActivity(new Intent(getApplicationContext(),  AcountUser.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Add_Ads_three.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(user_id,user_id);
                params.put(ads_title,ads_title);
                params.put(ads_price,ads_price);
                params.put(ads_image,ads_image);
                params.put(ads_category,ads_category);
                params.put(ads_type,ads_type);
                params.put(ads_model,ads_model);
                params.put(latitude,latitude);
                params.put(longitude,longitude);
                params.put(ads_content,ads_content);
                params.put(contact,contact);
                return params;
            }

        };





        //Creating a Request Queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Adding request to the queue
        requestQueue.add(stringRequest);

        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent( Add_Ads_three.this,  Add_Ads_two.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
