package com.asiaikuba.facebookevents;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ViewHolder> {
    private List<Event> mDataset;
    private FragmentManager fragmentManager;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public TextView nameTextView;
//        public ViewHolder(View v) {
//            super(v);
//            nameTextView = (TextView) itemView.findViewById(R.id.event_name);
//
//        }
//    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EventsListAdapter(List<Event> myDataset, FragmentManager fm) {
        mDataset = myDataset;
        fragmentManager = fm;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.events_list_item, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(mDataset.get(position).name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView event_name;
        private Context context;
        public TextView nameTextView;

        public ViewHolder( View itemView) {
            super(itemView);
            this.nameTextView = (TextView) itemView.findViewById(R.id.event_name);
            // Store the context
            this.context = context;

            Button descriptionBtn = (Button) itemView.findViewById(R.id.event_description);
            descriptionBtn.setOnClickListener(this); // calling onClick() method
            Button mapBtn = (Button) itemView.findViewById(R.id.event_map);
            mapBtn.setOnClickListener(this);

            itemView.setOnClickListener(this);
            }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it


                switch (view.getId()){
                    case R.id.event_description:
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        FragmentEventInfo fragment = new FragmentEventInfo();
                        fragment.setEventIdOnList(position);
                        transaction.replace(R.id.fragmentContainer, fragment);
                        transaction.commit();

                        break;
                    case R.id.event_map:

                        break;

                }
            }
        }
    }

}



