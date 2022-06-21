package com.example.home;

import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Homes {
    public String address;
    public Double homePrice;
    public Integer homeNoOfBedrooms;
    public Integer homeNoOfBathrooms;
    public Integer homeNoOfFloors;
    public Integer yearBuilt;
    public boolean saved;

    public Homes(){}

    public static Homes fromJson(JSONObject jsonObject){
        Homes home = new Homes();
        return home;
    }


}
