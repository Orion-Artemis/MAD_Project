package com.example.mad.Activity;

import static android.content.ContentValues.TAG;
import static com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mad.R;
import com.example.mad.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private FusedLocationProviderClient mfusedLocationProviderClient;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_CODE = 1234;

    private final boolean mlocationPer = true;

    private EditText mSearchTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSearchTxt = (EditText) findViewById(R.id.inputSearch);
        getDeviceLocation();
        mSearchTxt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER){
                geoLocate();
            }
            return false;
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        hideSoftKeyboard();
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    private void geoLocate() {
        String searchStr = mSearchTxt.getText().toString().trim();
        if (searchStr.isEmpty()) {
            // Handle case when search string is empty
            Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            return;
        }

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchStr, 1);
            if (list.isEmpty()) {
                // Handle case when no address is found
                Toast.makeText(this, "No location found for: " + searchStr, Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (IOException e) {
            // Handle geocoding errors
            e.printStackTrace();
            Toast.makeText(this, "Geocoding error occurred", Toast.LENGTH_SHORT).show();
            return;
        }

        // Retrieve the first address from the list
        Address address = list.get(0);
        moveLat(new LatLng(address.getLatitude(), address.getLongitude()), 15f, address.getAddressLine(0));
        hideSoftKeyboard();
    }


    private void getDeviceLocation() {
        mfusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            Task<Location> location = mfusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Location current = (Location) task.getResult();
                    moveLat(new LatLng(current.getLatitude(), current.getLongitude()), 15f,"My Location");
                }
            });
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: Security Exception" + e.getMessage());
        }
        hideSoftKeyboard();
    }

    private void moveLat(LatLng latLng, float zoom, String title) {
        mMap.moveCamera(newLatLngZoom(latLng, zoom));
        if (!title.equals("My Location")){
             MarkerOptions options = new MarkerOptions()
                     .position(latLng)
                     .title(title);
             mMap.addMarker(options);
         }
        hideSoftKeyboard();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        getDeviceLocation();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        geoLocate();
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}