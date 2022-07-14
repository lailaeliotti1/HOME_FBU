package com.example.home.models;

import com.example.home.ZipCode.ZipcodeParser;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("UserPreferences")
public class UserPreferences extends ParseObject {
    private static final String KEY_BEDROOM_NO = "noOfBedrooms";
    private static final String KEY_PROPERTY_TYPE = "propertyType";
    private static final String KEY_LAT = "latitude";
    public static final String USER_KEY = "user";
    private static final String KEY_LNG = "longtiude";
    private static final String KEY_ZIPCODE = "zipcode";


    public Number getNoOfBedrooms(){
        return getNumber("noOfBedrooms");
    }
    public void setNoOfBedrooms(Integer noOfBedrooms){
        put(KEY_BEDROOM_NO, noOfBedrooms);
    }
    public String getPropertyType(){
        return getString("propertyType");
    }
    public void setPropertyType(String propertyType){
        put(KEY_PROPERTY_TYPE, propertyType);
    }
    public void setUser(User user) {
        put(USER_KEY, user);
    }

    public Number getLat(){
        return getNumber(KEY_LAT);
    }
    public void setLat(Number latitude){
        put(KEY_LAT, latitude);
    }
    public Number getLng(){
        return getNumber(KEY_LNG);
    }
    public void setLng(Number longitude){
        put(KEY_LNG, longitude);
    }
    public Integer getZipcode(){
        return getInt(KEY_ZIPCODE);
    }
    public void setZipcode(Integer zipcode){
        put(KEY_ZIPCODE, zipcode);
    }


    public ParseObject getUserPreferences(){
        return getParseObject("UserPreferences");
    }
    }


