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

 import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import aljentelhosting.ksaksafactory.R;


public class MapWasfFactory extends Fragment implements LocationListener, OnMapReadyCallback {


    MapView mMapView;
    public  GoogleMap googleMp;
    double newLatitude;
    double newLongitude;
    LatLng newpoint;
    LocationManager locationManager;
    String company_latitude,company_longitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map_wasf_factory2, container, false);
        company_latitude= getArguments().getString("company_latitude");
        company_longitude= getArguments().getString("company_longitude");
        if(company_latitude!=null){
            newLatitude= Double.parseDouble(company_latitude);
        }
        if(company_longitude!=null){
            newLongitude= Double.parseDouble(company_longitude);
        }







        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

         mMapView.getMapAsync(this);


//        ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.mapView)).getMapAsync();


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        return rootView;
    }


    @Override
    public void onLocationChanged(Location location) {
//        LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
//
//
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(currentLocation);
//        markerOptions.title(getGeocodeName(location.getLatitude(), location.getLongitude()));
//
//
//        // ROSE color icon
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//
//        googleMp.addMarker(markerOptions);
//
//        googleMp.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10.0f));
//
//        googleMp.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10.0f));

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
    public void onMapReady(GoogleMap googleMap) {
        this.googleMp = googleMap;
        if (ActivityCompat.checkSelfPermission( getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }



        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(newLatitude,  newLongitude);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(sydney);
        markerOptions.title(getGeocodeName(newLatitude, newLongitude));
        googleMp.addMarker(markerOptions);

        googleMp.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.0f));

        googleMp.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.0f));


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



    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
