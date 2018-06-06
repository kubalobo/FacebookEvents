package com.asiaikuba.facebookevents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEventInfo extends Fragment {

    private int eventIdOnList;

    public FragmentEventInfo() {
        // Required empty public constructor
    }

    public void setEventIdOnList(int id) {
        eventIdOnList = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_info, container, false);

        TextView name = view.findViewById(R.id.event_info_name);
        TextView description = view.findViewById(R.id.event_info_description);

        Event event = ((MainActivity) getActivity()).events.get(eventIdOnList);
        name.setText(event.name);
        description.setText(event.description);

        return view;
    }

}
