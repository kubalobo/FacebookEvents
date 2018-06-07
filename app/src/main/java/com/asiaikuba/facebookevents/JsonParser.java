package com.asiaikuba.facebookevents;

import android.os.Debug;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JsonParser {

    static List<Event> parse(String json, Set<String> deletedEvents) {
        Moshi moshi = new Moshi.Builder()
                .build();

        Type type = Types.newParameterizedType(List.class, Event.class);
        JsonAdapter<List<Event>> adapter = moshi.adapter(type);

        try {
            List<Event> events = adapter.fromJson(json);

            Log.wtf("AAA", deletedEvents.toString());

            Iterator<Event> iter = events.iterator();

            while (iter.hasNext()) {
                Event ev = iter.next();

                if (deletedEvents.contains(ev.id))
                    iter.remove();
            }

//            System.out.println(events.get(1).place.location.latitude);
            return events;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
