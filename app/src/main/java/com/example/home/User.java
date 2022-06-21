package com.example.home;


import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
@Parcel
public class User {
    public String userName;
    public String password;
    public String email;
    public ArrayList<Homes> savedHomes;
    public String location;
    public Integer noOfBedrooms;
    public Integer noOfBathrooms;
    public String houseStyle;
    public Integer noOfFloors;
    public Double budget;

    public User(){}

    public static User fromJson(JSONObject jsonObject){
        User user = new User();
        return user;
    }

}
