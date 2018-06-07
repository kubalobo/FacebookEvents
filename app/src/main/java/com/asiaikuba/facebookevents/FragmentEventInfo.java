package com.asiaikuba.facebookevents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        TextView startTime = view.findViewById(R.id.event_info_start_time);
        TextView endTime = view.findViewById(R.id.event_info_end_time);
        Button deleteButton = view.findViewById(R.id.deleteButton);
        final MainActivity activity = ((MainActivity) getActivity());

        final Event event = activity.events.get(eventIdOnList);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.deletedEvents.add(event.id);
                activity.events.remove(eventIdOnList);


                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, new FragmentLogin());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        name.setText(event.name);
        description.setText(event.description);
        startTime.setText(event.start_time);
        endTime.setText(event.end_time);


        return view;
    }

}
