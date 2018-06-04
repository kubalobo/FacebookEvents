package com.asiaikuba.facebookevents;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEventsList extends Fragment {


    public FragmentEventsList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);

        //create the ItemAdapter holding your Items
        ItemAdapter itemAdapter = new ItemAdapter();
        //create the managing FastAdapter, by passing in the itemAdapter
        FastAdapter<EventsAdapter> fastAdapter = FastAdapter.with(itemAdapter);

        //set our adapters to the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.eventsRecyclerView);
        recyclerView.setAdapter(fastAdapter);

        //set the items to your ItemAdapter
        MainActivity activity = (MainActivity) getActivity();
        List<EventsAdapter> events = activity.events;
        itemAdapter.add(events);

        return view;
    }

}
