package com.example.hosplinictracker;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.hosplinictracker.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng centerLocation;

    MarkerOptions marker;


    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        centerLocation = new LatLng(3.1319,101.6841);
        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Klinik Kesihatan Ampang")
                .position(new LatLng(3.1451,101.7614))
                .snippet("Open: 24 Hour")
        );

        markerOptions.add(new MarkerOptions().title("Klinik Uzma Kelana Mall")
                .position(new LatLng(3.1046,101.5986))
                .snippet("Open: 24 Hour")
        );

        markerOptions.add(new MarkerOptions().title("Klinik Selva")
                .position(new LatLng(3.0439,101.6205))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Klang Health Clinic (Anika)")
                .position(new LatLng(3.0372,101.4470))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Pandamaran Health Clinic")
                .position(new LatLng(3.0141,101.4123))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Global Doctors Hospital")
                .position(new LatLng(3.1681,101.6488))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Ajwa")
                .position(new LatLng(3.0747,101.4863))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Putrajaya")
                .position(new LatLng(2.9291,101.6742))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Cyberjaya")
                .position(new LatLng(2.9195,101.6307))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Shah Alam")
                .position(new LatLng(3.0713,101.4900))
                .snippet("Open: 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("GK Clinic")
                .position(new LatLng(3.0650,101.4808))
                .snippet("Open: 9am-10pm")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Mediviron")
                .position(new LatLng(3.0677,101.4891))
                .snippet("Open: 8am-10pm")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Noura")
                .position(new LatLng(3.0770,101.4895))
                .snippet("Open: 9am-9.30pm")
        );
        markerOptions.add(new MarkerOptions().title("KPJ Klang Specialist Hospital")
                .position(new LatLng(3.0630,101.4631))
                .snippet("Open: 8.30am-5.30pm")
        );
        markerOptions.add(new MarkerOptions().title("Pusat Kesihatan UiTM Shah Alam")
                .position(new LatLng(3.0692,101.4936))
                .snippet("Open: 8am-5pm")
        );

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation,8));

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng(-34, 151);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void enableMyLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if (mMap != null){
                mMap.setMyLocationEnabled(true);
            }
        }else {
            String perms [] = {"android.permission.ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this,perms,200);
        }
    }

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude  ());


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        }
    }

}