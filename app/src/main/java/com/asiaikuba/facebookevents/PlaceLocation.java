package com.asiaikuba.facebookevents;

public class PlaceLocation {
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
