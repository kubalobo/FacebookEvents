package com.asiaikuba.facebookevents;

public class Place {
    public final String name;
    public final String id;
    public final PlaceLocation location;

    public Place(String name, String id, PlaceLocation location) {
        this.name = name;
        this.id = id;
        this.location = location;
    }
}
