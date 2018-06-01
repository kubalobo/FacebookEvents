package com.asiaikuba.facebookevents;

public class Event {
    public final String description;
    public final String start_time;
    public final String end_time;
    public final String id;
    public final String name;
    public final String rsvp_status;

    public Event(String description, String start_time, String end_time, String id, String name, String rsvp_status) {
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.id = id;
        this.name = name;
        this.rsvp_status = rsvp_status;
    }
//    public final ??? place;

}
