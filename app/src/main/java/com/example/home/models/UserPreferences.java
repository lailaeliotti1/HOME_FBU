package com.example.home.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("UserPreferences")

public class UserPreferences extends ParseObject {
    public static final String mLocation = "mLocation";
    public static final String mNoOfBedrooms = "mNoOfBedrooms";
    public static final String mNoOfBathrooms = "mNoOfBathrooms";
    public static final String mHouseStyle = "mHouseStyle";
    public static final String mNoOfFloors = "mNoOfFloors";
    public static final String mBudget = "mBudget";
    public static final String mSavedHomes = "mSavedHomes";

    public ParseFile getmLocation(){
        return getParseFile(mLocation);
    }
    public void setmLocation(ParseFile parseFile){
        put(mLocation, parseFile);
    }
    public ParseFile getmNoOfBedrooms(){
        return getParseFile(mLocation);
    }
    public void setmNoOfBedrooms(ParseFile parseFile){
        put(mNoOfBedrooms, parseFile);
    }
    public ParseFile getmNoOfBathrooms(){
        return getParseFile(mNoOfBathrooms);
    }
    public void setmNoOfBathrooms(ParseFile parseFile){
        put(mNoOfBathrooms, parseFile);
    }
    public ParseFile getmHouseStyle(){
        return getParseFile(mHouseStyle);
    }
    public void setmHouseStyle(ParseFile parseFile){
        put(mHouseStyle, parseFile);
    }
    public ParseFile getmNoOFFloors(){
        return getParseFile(mNoOfFloors);
    }
    public void setmNoOfFloors(ParseFile parseFile){
        put(mNoOfFloors, parseFile);
    }
    public ParseFile getmBudget(){
        return getParseFile(mBudget);
    }
    public void setmBudget(ParseFile parseFile){
        put(mBudget, parseFile);
    }
    public ParseFile getmSavedHomes(){
        return getParseFile(mSavedHomes);
    }
    public void setmSavedHomes(ParseFile parseFile){
        put(mSavedHomes, parseFile);
    }





}

