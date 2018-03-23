package com.asiaikuba.facebookevents;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Asia on 23.03.2018.
 *  :D
 */

public class InfoWindowData implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public InfoWindowData(Context context) {
        this.context = context;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        View info_window_layout = ((Activity)context).getLayoutInflater().inflate(R.layout.info_window,null);
//        TextView t = ( info_window_layout.findViewById(R.id.textView));
        TextView t =  info_window_layout.findViewById(R.id.textView);
        t.setText(("LatLong :: " + marker.getPosition().latitude + "," + marker.getPosition().longitude));

        return info_window_layout;

    }

}
