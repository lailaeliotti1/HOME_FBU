package com.example.home.models;


import com.parse.ParseClassName;
import com.parse.ParseUser;

import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
@ParseClassName("_User")
public class _User extends ParseUser {
    private String mUserName;
    private String mPassword;
    private String mEmail;
    private String _User;


    public _User(){}

    public static _User fromJson(JSONObject jsonObject){
        _User user = new _User();
        return user;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }
}
