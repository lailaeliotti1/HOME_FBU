package com.example.home.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Home")
public class Home extends ParseObject {
    public String mAddress;
    public String mPropertyType;
    public String mHomeNoOfBedrooms;
    public String mHomeNoOfBathrooms;
    public String mYearBuilt;
    public String mDistance;

    public Home(){}

    public Home (JSONObject jsonObject) throws JSONException{
        mAddress = jsonObject.getJSONObject("property").getJSONObject("address").getString("oneLine");
        mPropertyType = jsonObject.getJSONObject("property").getJSONObject("summary").getString("proptype");
        mHomeNoOfBedrooms = jsonObject.getJSONObject("property").getJSONObject("address").getJSONObject("rooms").getString("beds");
        mHomeNoOfBathrooms = jsonObject.getJSONObject("property").getJSONObject("building").getJSONObject("rooms").getString("bathstotal");
        mYearBuilt = jsonObject.getJSONObject("property").getJSONObject("summary").getString("yearbuilt");
        mDistance = jsonObject.getJSONObject("property").getJSONObject("address").getString("distance");
    }

    public String getAddress() {
        return mAddress;
    }

    public String getPropertyType(){
        return mPropertyType;
    }

    public String getHomeNoOfBedrooms() {
        return mHomeNoOfBedrooms;
    }

    public String getHomeNoOfBathrooms() {
      return mHomeNoOfBathrooms;
    }

    public String getYearBuilt() {
       return mYearBuilt;
    }

    public String getDistance() {
        return mDistance;
    }

    public boolean isSaved() {
        return true;
    }
}
