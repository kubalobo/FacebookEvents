package com.asiaikuba.facebookevents;

public class Event {
    public String description;
    public String start_time;
    public String end_time;
    public String id;
    public String name;
    public String rsvp_status;
    public Place place = new Place();

    Event() {}

    public Event(String description, String start_time, String end_time, String id, String name, String rsvp_status, Place place) {
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id = id;
        this.name = name;
        this.rsvp_status = rsvp_status;
        this.place = place;
    }
}
