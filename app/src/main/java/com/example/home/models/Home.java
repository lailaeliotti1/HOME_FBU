package com.example.home.models;

import org.json.JSONObject;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Home")
public class Home extends ParseObject {
    private String address;
    private Double homePrice;
    private Integer homeNoOfBedrooms;
    private Integer homeNoOfBathrooms;
    private Integer homeNoOfFloors;
    private Integer yearBuilt;
    public boolean saved;

    public Home(){}

    public static Home fromJson(JSONObject jsonObject){
        Home home = new Home();
        return home;
    }

    public String getAddress() {
        return address;
    }

    public Double getHomePrice() {
        return homePrice;
    }

    public Integer getHomeNoOfBedrooms() {
        return homeNoOfBedrooms;
    }

    public Integer getHomeNoOfBathrooms() {
        return homeNoOfBathrooms;
    }

    public Integer getHomeNoOfFloors() {
        return homeNoOfFloors;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public boolean isSaved() {
        return saved;
    }
}
