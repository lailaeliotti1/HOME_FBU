package com.example.home.JsonParser;

import android.util.Log;

import com.example.home.models.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeJsonParser {
    private static final String ADDRESS_PARAM = "address";
    private static final String ONELINE_PARAM = "oneLine";
    private static final String SUMMARY_PARAM = "summary";
    private static final String PROPERTYTYPE_PARAM = "proptype";
    private static final String BUILDING_PARAM = "building";
    private static final String ROOMS_PARAM = "rooms";
    private static final String BED_PARAM = "beds";
    private static final String BATHS_PARAM = "bathstotal";
    private static final String YEAR_PARAM = "yearbuilt";
    private static final String LOCATION_PARAM = "location";
    private static final String DISTANCE_PARAM = "distance";
    private static final String PROPERTY_PARAM = "property";



    public static Home getHomeFromJson (JSONObject jsonObject) throws JSONException {
        Home home = new Home();
        home.setAddress(jsonObject.getJSONObject(ADDRESS_PARAM).getString(ONELINE_PARAM));
        home.setPropertyType(jsonObject.getJSONObject(SUMMARY_PARAM).getString(PROPERTYTYPE_PARAM));
        home.setHomeNoOfBedrooms(jsonObject.getJSONObject(BUILDING_PARAM).getJSONObject(ROOMS_PARAM).getString(BED_PARAM));
        home.setHomeNoOfBathrooms(jsonObject.getJSONObject(BUILDING_PARAM).getJSONObject(ROOMS_PARAM).getString(BATHS_PARAM));
        home.setYearBuilt(jsonObject.getJSONObject(SUMMARY_PARAM).getString(YEAR_PARAM));
        home.setDistance(jsonObject.getJSONObject(LOCATION_PARAM).getString(DISTANCE_PARAM));

        return home;
    }
    public static ArrayList<Home> getListOfHomes(JSONObject jsonObject){
        Log.e("getListOfHomes","in method");
        ArrayList<Home> homes = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(PROPERTY_PARAM);
            for (int i = 0; i < jsonArray.length(); i++) {
                homes.add(HomeJsonParser.getHomeFromJson((JSONObject) jsonArray.get(i)));
                Log.d("ArrayList", "home added");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return homes;
    }
}
