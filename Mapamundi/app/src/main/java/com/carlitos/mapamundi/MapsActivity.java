package com.carlitos.mapamundi;

import android.app.Activity;
import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "LOG";
    private GoogleMap mMap;
    private EditText searchAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        searchAddress = (EditText) findViewById(R.id.inputText);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (status == ConnectionResult.SUCCESS){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            Dialog d = GooglePlayServicesUtil.getErrorDialog(status, (Activity)getApplicationContext(),10);
            d.show();
        }

    }

    private void init(){
        searchAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER) {

                    geolocate();
                }
                return false;
            }
        });
    }

    private void geolocate() {
        String AddressToSearch = searchAddress.getText().toString();

        Geocoder gc = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = gc.getFromLocationName(AddressToSearch, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (list.size() > 0) {
            Address a = list.get(0);
            Log.d(TAG, "Address found: " + a.toString());
            LatLng searchResult = new LatLng(a.getLatitude(), a.getLongitude());
            mMap.addMarker(new MarkerOptions().position(searchResult).title(a.getFeatureName())
                    .snippet(String.valueOf(searchResult.latitude) + " | " + String.valueOf(searchResult.longitude)))
                    .showInfoWindow();
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(searchResult,15.5f));

        }
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
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        UiSettings uis = mMap.getUiSettings();
        uis.setAllGesturesEnabled(true);
        uis.setZoomControlsEnabled(true);
        uis.setMyLocationButtonEnabled(true);


        // Add a marker in Bolivar Square and move the camera
        LatLng bolivarSquare = new LatLng(4.597910, -74.076078);
        mMap.addMarker(new MarkerOptions().position(bolivarSquare).title("Plaza de Bolivar")
                .snippet(String.valueOf(bolivarSquare.latitude) + " | " + String.valueOf(bolivarSquare.longitude)))
                .showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bolivarSquare,18.5f));
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));

        init();
    }
}
