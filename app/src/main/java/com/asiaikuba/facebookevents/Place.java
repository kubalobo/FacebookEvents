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


    private class PlaceLocation {
        public final String city;
        public final String country;
        public final String latitude;
        public final String longitude;
        public final String street;
        public final String zip;

        private PlaceLocation(String city, String country, String latitude, String longitude, String street, String zip) {
            this.city = city;
            this.country = country;
            this.latitude = latitude;
            this.longitude = longitude;
            this.street = street;
            this.zip = zip;
        }
    }
}
