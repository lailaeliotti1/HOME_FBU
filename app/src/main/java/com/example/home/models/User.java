package com.example.home.models;


import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
@Parcel
public class User {
    private String userName;
    private String password;
    private String email;


    public User(){}

    public static User fromJson(JSONObject jsonObject){
        User user = new User();
        return user;
    }

}
