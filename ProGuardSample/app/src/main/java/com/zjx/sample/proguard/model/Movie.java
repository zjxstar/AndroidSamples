package com.zjx.sample.proguard.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    public String name;

    public int time;

    @SerializedName("money")
    public double price;

    public String poster;

    public Movie(String name, int time, double price, String poster) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.poster = poster;
    }
}
