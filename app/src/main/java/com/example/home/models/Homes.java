package com.example.home.models;

import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Homes {
    private String address;
    private Double homePrice;
    private Integer homeNoOfBedrooms;
    private Integer homeNoOfBathrooms;
    private Integer homeNoOfFloors;
    private Integer yearBuilt;
    public boolean saved;

    public Homes(){}

    public static Homes fromJson(JSONObject jsonObject){
        Homes home = new Homes();
        return home;
    }


}
