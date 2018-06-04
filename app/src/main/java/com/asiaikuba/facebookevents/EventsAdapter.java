package com.asiaikuba.facebookevents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends AbstractItem<EventsAdapter, EventsAdapter.ViewHolder> {
//    public StringHolder name;
    public StringHolder description;
    public StringHolder start_time;
    public StringHolder end_time;
    public StringHolder id;
    public StringHolder name;
    public StringHolder rsvp_status;

    public EventsAdapter(StringHolder description, StringHolder start_time, StringHolder end_time, StringHolder id, StringHolder name, StringHolder rsvp_status) {
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id = id;
        this.name = name;
        this.rsvp_status = rsvp_status;
    }

    // Fast Adapter methods
    @Override
    public int getType() { return R.id.event_name; }

    @Override
    public int getLayoutRes() { return R.layout.events_list_item; }

    @NonNull
    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }

    // Manually create the ViewHolder class
    protected static class ViewHolder extends FastAdapter.ViewHolder<EventsAdapter> {
        @BindView(R.id.event_name)
        TextView name;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(EventsAdapter item, List<Object> payloads) {
            StringHolder.applyTo(item.name, name);
//            StringHolder.applyToOrHide(item.description, description);
        }

        @Override
        public void unbindView(EventsAdapter item) {
            name.setText(null);
//            description.setText(null);
        }
    }

}
