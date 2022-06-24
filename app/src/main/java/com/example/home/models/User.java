package com.example.home.models;


import com.parse.ParseClassName;
import com.parse.ParseUser;

import org.json.JSONObject;

@ParseClassName("_User")
public class _User extends ParseUser {
    private String mUserName;
    private String mPassword;
    private String mEmail;
    private String _User;


    public _User(){}

    public static com.example.home.models._User fromJson(JSONObject jsonObject){
        com.example.home.models._User user = new _User();
        return user;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
