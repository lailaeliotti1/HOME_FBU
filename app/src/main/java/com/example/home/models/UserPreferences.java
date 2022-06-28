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

    public ParseFile getLocation(){
        return getParseFile(mLocation);
    }
    public void setLocation(ParseFile parseFile){
        put(mLocation, parseFile);
    }
    public ParseFile getmNoOfBedrooms(){
        return getParseFile(mLocation);
    }
    public void setNoOfBedrooms(ParseFile parseFile){
        put(mNoOfBedrooms, parseFile);
    }
    public ParseFile getNoOfBathrooms(){
        return getParseFile(mNoOfBathrooms);
    }
    public void setNoOfBathrooms(ParseFile parseFile){
        put(mNoOfBathrooms, parseFile);
    }
    public ParseFile getmHouseStyle(){
        return getParseFile(mHouseStyle);
    }
    public void setHouseStyle(ParseFile parseFile){
        put(mHouseStyle, parseFile);
    }
    public ParseFile getNoOFFloors(){
        return getParseFile(mNoOfFloors);
    }
    public void setNoOfFloors(ParseFile parseFile){
        put(mNoOfFloors, parseFile);
    }
    public ParseFile getBudget(){
        return getParseFile(mBudget);
    }
    public void setBudget(ParseFile parseFile){
        put(mBudget, parseFile);
    }
    public ParseFile getSavedHomes(){
        return getParseFile(mSavedHomes);
    }
    public void setSavedHomes(ParseFile parseFile){
        put(mSavedHomes, parseFile);
    }





}

