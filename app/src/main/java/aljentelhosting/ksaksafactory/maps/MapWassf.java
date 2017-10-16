package aljentelhosting.ksaksafactory.maps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

 import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import aljentelhosting.ksaksafactory.R;

public class MapWassf extends FragmentActivity // implements OnMapReadyCallback, LocationListener, OnMapClickListener {
{
    private GoogleMap mMap;
    LocationManager locationManager;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_wassf);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, (LocationListener) getApplicationContext());
//            mMap.setMyLocationEnabled(true);
        }
        else {
            ActivityCompat.requestPermissions(MapWassf.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }



        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(this, "gps is on ", Toast.LENGTH_SHORT).show();
            setmMap();
        }else{
            Toast.makeText(this, "gps is off ", Toast.LENGTH_SHORT).show();

            turnGPSOn();

        }

    }


    void setmMap(){


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;



            }
        });
        mMap.setOnMapClickListener(new OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(point.latitude, point.longitude)).title("New Marker");

                mMap.addMarker(marker);

                System.out.println(point.latitude+"---"+ point.longitude);
                long  x = (long) point.latitude;
            }
        });

        mMap. setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                mMap.clear();
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());


                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(currentLocation);
                markerOptions.title("i'm here");

                mMap.addMarker(markerOptions);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));

            }
        });
    }

    public void turnGPSOn()
    { AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Goto Settings Page To Enable GPS",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    /*


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // الخريطه اللي احنا بنعمل  عليها  اي تعدلات  بتكون   اللي  جايه من الأنترفيس

        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, (LocationListener) this);
        }
        else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        mMap.setMyLocationEnabled(true);

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

        @Override
        public void onLocationChanged(Location location) {
            mMap.clear();
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());


            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(currentLocation);
            markerOptions.title("i'm here");

            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));

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

    @Override
    public void onMapClick(LatLng point) {

         MarkerOptions marker = new MarkerOptions().position(
                new LatLng(point.latitude, point.longitude)).title("New Marker");

        mMap.addMarker(marker);

        System.out.println(point.latitude+"---"+ point.longitude);
        long  x = (long) point.latitude;
    } */


}
