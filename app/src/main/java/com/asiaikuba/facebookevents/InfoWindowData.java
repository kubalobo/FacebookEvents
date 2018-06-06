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
    private MainActivity activity;

    public InfoWindowData(Context context, Activity activity) {
        this.context = context;
        this.activity = (MainActivity) activity;

    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {

        View info_window_layout = ((Activity)context).getLayoutInflater().inflate(R.layout.info_window,null);
//        TextView t = ( info_window_layout.findViewById(R.id.textView));
        TextView t =  info_window_layout.findViewById(R.id.infoWindow_name);
        int position = (int)marker.getTag();
        Event ev = activity.events.get(position);
        t.setText( ev.name );

        return info_window_layout;
    }
}
