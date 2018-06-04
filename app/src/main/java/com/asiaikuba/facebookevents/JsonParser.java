package com.asiaikuba.facebookevents;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {

    static List<EventsAdapter> parse(String json) {
        Moshi moshi = new Moshi.Builder()
                .add(new JsonEventAdapter())
                .build();

        Type type = Types.newParameterizedType(List.class, EventsAdapter.class);
        JsonAdapter<List<EventsAdapter>> adapter = moshi.adapter(type);

        try {
            List<EventsAdapter> events = adapter.fromJson(json);
            System.out.println(events.get(0).name);
            return events;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
