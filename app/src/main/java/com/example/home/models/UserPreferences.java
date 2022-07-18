package com.example.home.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("UserPreferences")
public class UserPreferences extends ParseObject {
    private static final String KEY_BEDROOM_NO = "noOfBedrooms";
    private static final String KEY_PROPERTY_TYPE = "propertyType";
    private static final String KEY_LAT = "latitude";
    private static final String USER_KEY = "user";
    private static final String KEY_LNG = "longitude";
    private static final String KEY_ZIPCODE = "zipcode";
    private static final String KEY_RECOMMENDATION = "recommendationSwitch";
    private static final String KEY_RADIUS = "radius";
    private static final String KEY_MAXBED = "maxbeds";
    private int mRadius = 1;


    public Number getNoOfBedrooms(){
        return getNumber(KEY_BEDROOM_NO);
    }
    public void setNoOfBedrooms(Number noOfBedrooms){
        put(KEY_BEDROOM_NO, noOfBedrooms);
    }
    public void setMaxNoOfBedrooms(Integer maxNoOfBedrooms){
        put(KEY_MAXBED, maxNoOfBedrooms);
    }
    public Integer getMaxNoOfBedrooms(){
        if(getRecommendationSwitch() == true)
            return (int)getNumber(KEY_BEDROOM_NO)+1;
        return getInt(KEY_BEDROOM_NO);
    }
    public String getPropertyType(){
        return getString(KEY_PROPERTY_TYPE);
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
    public Number getRadius(){
        return getNumber(KEY_RADIUS);
    }
    public void setRadius(Integer radius){
        if(getRecommendationSwitch()==false)
            put(KEY_RADIUS, radius);
        else
            put(KEY_RADIUS, mRadius);
    }

    public ParseObject getUserPreferences(){
        return getParseObject("UserPreferences");
    }

    public void setRecommendationSwitch(Boolean recommendationSwitch) {
        put(KEY_RECOMMENDATION, recommendationSwitch);
    }
    public Boolean getRecommendationSwitch(){
        return getBoolean(KEY_RECOMMENDATION);
    }
}


