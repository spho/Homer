package com.example.christoph.homer;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    static final LatLng Zurich_technopark = new LatLng(47.03895892, 8.5154389);

    Marker marker;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.


    Location currentLocation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        setUpMapIfNeeded();
        showMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public MapsActivity(){

    }

    private Location convertToAddress(LatLng ll) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(ll.latitude, ll.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();

            Location loc = new Location(address, city, state, country, postalCode, knownName);
            return loc;

        } catch (IOException e) {
            Log.d("Tag", "Creation of address failed: " + e.toString());
            e.printStackTrace();
        }
        return null;

    }

    private void showMap() {
       // mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        // zoom in the camera to Davao city
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Zurich_technopark, 15));
        // animate the zoom process
        mMap.animateCamera(CameraUpdateFactory.zoomTo(8), 2000, null);

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);

    }

    private void closeMap() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("address", currentLocation.getAddress());
        intent.putExtra("city", currentLocation.getCity());
        intent.putExtra("country", currentLocation.getCountry());
        intent.putExtra("knownname", currentLocation.getKnownName());
        intent.putExtra("postal", currentLocation.getPostalCode());
        intent.putExtra("state", currentLocation.getState());

        startActivity(intent);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (currentLocation != null) {

        }

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString()));
        currentLocation = convertToAddress(latLng);
        closeMap();

    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
