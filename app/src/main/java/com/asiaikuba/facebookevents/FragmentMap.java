package com.asiaikuba.facebookevents;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMap extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    List<Event> events;


    public FragmentMap() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        events = ((MainActivity) getActivity()).events;

        Marker warszawa = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(52.23, 20.92)));
        warszawa.setTag(0);

        InfoWindowData infoWindowAdapter = new InfoWindowData(getContext());

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

        eventsMarkersGenerator();
    }

    void eventsMarkersGenerator() {
        for (Event event : events) {
            if(!event.place.location.latitude.equals("")) {
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(
                                Double.parseDouble(event.place.location.latitude),
                                Double.parseDouble(event.place.location.longitude))));
                marker.setTag(event);
            }

        }

    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getContext(), "Info window clicked",
                Toast.LENGTH_SHORT).show();
        Event event = (Event) marker.getTag();
        int position = events.indexOf(event);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        FragmentEventInfo fragment = new FragmentEventInfo();
        fragment.setEventIdOnList(position);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}
