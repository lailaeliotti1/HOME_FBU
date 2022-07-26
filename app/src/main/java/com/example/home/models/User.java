package com.example.home.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;

@ParseClassName("_User")
public class User extends ParseUser {
    private String mUserName;
    private String mPassword;
    private String mEmail;

    public User() {
    }

    public String getUserName() {
        return mUserName;
    }


    public String getEmail() {
        return mEmail;
    }


    public String getPassword() {
        return mPassword;
    }

}
