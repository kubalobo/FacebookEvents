package com.asiaikuba.facebookevents;

import com.mikepenz.materialize.holder.StringHolder;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class JsonEventAdapter {
    @FromJson
    StringHolder fromJson(String inputString) {
        return new StringHolder(inputString);
    }

    @ToJson
    String toJson(StringHolder inString) {
        return inString.toString();
    }
}

