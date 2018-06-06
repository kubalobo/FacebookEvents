package com.asiaikuba.facebookevents;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JsonParser {

    static List<Event> parse(String json) {
        Moshi moshi = new Moshi.Builder()
                .build();

        Type type = Types.newParameterizedType(List.class, Event.class);
        JsonAdapter<List<Event>> adapter = moshi.adapter(type);

        try {
            List<Event> events = adapter.fromJson(json);
            System.out.println(events.get(0).name);
            return events;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
