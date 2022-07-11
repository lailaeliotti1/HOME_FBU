package com.example.home.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Home")
public class Home extends ParseObject {
    private String mAddress;
    private String mPropertyType;
    private String mHomeNoOfBedrooms;
    private String mHomeNoOfBathrooms;
    private String mYearBuilt;
    private String mDistance;

    public Home(){}



    public String getAddress() {
        return mAddress;
    }
    public void setAddress(String address) {
        mAddress = address;
    }

    public String getPropertyType(){
        return mPropertyType;
    }
    public void setPropertyType(String propertyType) {
        mPropertyType = propertyType;
    }

    public String getHomeNoOfBedrooms() {
        return mHomeNoOfBedrooms;
    }
    public void setHomeNoOfBedrooms(String homeNoOfBedrooms) {
        mHomeNoOfBedrooms = homeNoOfBedrooms;
    }

    public String getHomeNoOfBathrooms() {
      return mHomeNoOfBathrooms;
    }
    public void setHomeNoOfBathrooms(String homeNoOfBathrooms) {
        mHomeNoOfBathrooms = homeNoOfBathrooms;
    }

    public String getYearBuilt() {
       return mYearBuilt;
    }
    public void setYearBuilt(String yearBuilt) {
        mYearBuilt = yearBuilt;
    }

    public String getDistance() {
        return mDistance;
    }
    public void setDistance(String distance) {
        mDistance = distance;
    }


}
