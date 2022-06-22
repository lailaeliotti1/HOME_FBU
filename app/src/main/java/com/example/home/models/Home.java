package com.example.home.models;

import org.json.JSONObject;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Home")
public class Home extends ParseObject {
    private String mAddress;
    private Double mHomePrice;
    private Integer mHomeNoOfBedrooms;
    private Integer mHomeNoOfBathrooms;
    private Integer mHomeNoOfFloors;
    private Integer mYearBuilt;
    public boolean mSaved;

    public Home(){}

    public static Home fromJson(JSONObject jsonObject){
        Home home = new Home();
        return home;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public Double getmHomePrice() {
        return mHomePrice;
    }

    public Integer getHomeNoOfBedrooms() {
        return mHomeNoOfBedrooms;
    }

    public void setmHomeNoOfBedrooms(Integer mHomeNoOfBedrooms) {
        this.mHomeNoOfBedrooms = mHomeNoOfBedrooms;
    }

    public Integer getmHomeNoOfBathrooms() {
        return mHomeNoOfBathrooms;
    }

    public void setmHomeNoOfBathrooms(Integer mHomeNoOfBathrooms) {
        this.mHomeNoOfBathrooms = mHomeNoOfBathrooms;
    }

    public Integer getmHomeNoOfFloors() {
        return mHomeNoOfFloors;
    }

    public void setmHomeNoOfFloors(Integer mHomeNoOfFloors) {
        this.mHomeNoOfFloors = mHomeNoOfFloors;
    }

    public Integer getmYearBuilt() {
        return mYearBuilt;
    }

    public void setmYearBuilt(Integer mYearBuilt) {
        this.mYearBuilt = mYearBuilt;
    }

    public boolean ismSaved() {
        return mSaved;
    }
}
