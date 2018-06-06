package com.asiaikuba.facebookevents;

public class Place {
    public String name = "";
    public String id = "";
    public PlaceLocation location = new PlaceLocation();

    Place() {
//        this.location = new PlaceLocation();
    }

    public Place(String name, String id, PlaceLocation location) {
        this.name = name;
        this.id = id;
        this.location = location;
    }
}
