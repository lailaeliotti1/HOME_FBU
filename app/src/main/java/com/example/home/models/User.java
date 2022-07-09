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
    //private UserPreferences mUserPreferences = new UserPreferences();
    //private ParseObject mParseObject = ParseObject.create("UserPreferences");


    public User(){}

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

//    public UserPreferences getUserPreferences(){
//        return mUserPreferences;
//    }
//    public void setUserPreferences(UserPreferences mUserPreferences) {
//        this.mUserPreferences = mUserPreferences;
//
//    }
}
