package com.asiaikuba.facebookevents;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.places.PlaceManager;
import com.facebook.places.model.PlaceFields;
import com.facebook.places.model.PlaceSearchRequestParams;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    private CallbackManager callbackManager;

    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        printKeyHash();

        callbackManager = CallbackManager.Factory.create();


        LoginButton loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), "SUKCES!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "REZYGNACJA!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), "PORAŻKA!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Marker mPerth = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10)));
        mPerth.setTag(0);

        Marker warszawa = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.23, 20.92)));
        warszawa.setTag(0);
//
        InfoWindowData infoWindowAdapter = new InfoWindowData(this);



//        mPerth.showInfoWindow();

//        LatLng snowqualmie = new LatLng(52.23, 20.92);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(snowqualmie)
//                .title("aaa")
//                .snippet("bbb");

//        InfoWindowData info = new InfoWindowData();
//        info.setImage("snowqualmie");
//        info.setHotel("Hotel : excellent hotels available");
//        info.setFood("Food : all types of restaurants available");
//        info.setTransport("Reach the site by bus, car and train.");

//        CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
//        mMap.setInfoWindowAdapter(customInfoWindow);

        mMap.setInfoWindowAdapter(infoWindowAdapter);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

//        Marker m = mMap.addMarker(markerOptions);
//        m.setTag(0);
//        m.showInfoWindow();
    }

    /**
     * Old probably not important method */
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.asiaikuba.facebookevents",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf("Problem", "NameNotFoundException");

        } catch (NoSuchAlgorithmException e) {
            Log.wtf("Problem", "NoSuchAlgorithmException");

        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();

    }



}
