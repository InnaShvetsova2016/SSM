package com.gmail.shvetsova2015.inna.entity;

import java.util.ArrayList;
import java.util.List;

public class Time {
    List<String> time = new ArrayList<>();

    public Time() {
        this.time.add("09:00");
        this.time.add("10:00");
        this.time.add("11:00");
        this.time.add("12:00");
        this.time.add("13:00");
        this.time.add("14:00");
        this.time.add("15:00");
        this.time.add("16:00");
        this.time.add("17:00");
        this.time.add("18:00");
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
