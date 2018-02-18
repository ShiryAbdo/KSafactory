package aljentelhosting.ksaksafactory.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import aljentelhosting.ksaksafactory.R;


public class MapWasfFactory extends Fragment   {


    MapView mMapView;
    public  GoogleMap googleMp;
    double newLatitude;
    double newLongitude;
     GoogleMap googleMap;
    String company_latitude,company_longitude;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
     View rootView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          rootView = inflater.inflate(R.layout.fragment_map_wasf_factory2, container, false);
        company_latitude= getArguments().getString("company_latitude");
        company_longitude= getArguments().getString("company_longitude");
        if(company_latitude!=null){
            newLatitude= Double.parseDouble(company_latitude);
        }
        if(company_longitude!=null){
            newLongitude= Double.parseDouble(company_longitude);
        }



        MapsInitializer.initialize(getActivity());

            showMap();


        return rootView;
    }






    public String getGeocodeName(double latitude, double longitude) {
        Context context = getContext();

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
    private  void showMap(){
        MapView mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(new Bundle());
        mMapView.onResume();
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap = googleMap;
                googleMap.clear();
                googleMap = googleMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                        LatLng sydney = new LatLng(newLatitude,  newLongitude);
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(sydney);
                        markerOptions.title(getGeocodeName(newLatitude, newLongitude));
                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15.0f));
//                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngSrc, 15.0f));



            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_LOCATION:{

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showMap();

                } else {
                    Toast.makeText(getActivity(), "Permission denied to read your LOCATION", Toast.LENGTH_SHORT).show();
                }
                break;

            }

        }
    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        mMapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mMapView.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mMapView.onDestroy();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mMapView.onLowMemory();
//    }
}
