package com.asiaikuba.facebookevents;

public class PlaceLocation {
    public String city = "";
    public String country = "";
    public String latitude = "";
    public String longitude = "";
    public String street = "";
    public String zip = "";

    PlaceLocation() {}

    private PlaceLocation(String city, String country, String latitude, String longitude, String street, String zip) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.zip = zip;
    }
}
