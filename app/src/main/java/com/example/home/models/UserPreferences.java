package com.example.home.models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("UserPreferences")
public class UserPreferences extends ParseObject {
    private static final String KEY_BEDROOM_NO = "noOfBedrooms";
    private static final String KEY_BATHROOM_NO = "mNoOfBathrooms";
    private static final String KEY_PROPERTY_TYPE = "mPropertyType";
    //private ParseObject mParseObject = ParseObject.create("UserPreferences");

    public Number getNoOfBedrooms(){
        return getNumber("noOfBedrooms");
    }
    public void setNoOfBedrooms(Integer noOfBedrooms){
        put(KEY_BEDROOM_NO, noOfBedrooms);
    }
    public Number getNoOfBathrooms(){
        return getNumber("noOfBathrooms");
    }
    public void setNoOfBathrooms(Integer noOfBathrooms){
        put(KEY_BATHROOM_NO, noOfBathrooms);
    }
    public String getPropertyType(){
        return getString("propertyType");
    }
    public void setPropertyType(String propertyType){
        put(KEY_PROPERTY_TYPE, propertyType);
    }
    public ParseObject getUserPreferences(){
        return getParseObject("UserPreferences");
    }
    }


