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
    private static final String LATITUDE_PARAM = "latitude";
    private static final String LONGITUDE_PARAM = "longitude";

    private static final String STREET_LOCATION_PARAM = "&location=";
    private static final String APIKEY_PARAM = "&key=";
    private static final String SIZE_PARAM = "size=";
    private static final String SIZE_DIMENSIONS = "150x150";
    private static String LOCATION_LATLNG;

    private static final String API_URL = "https://maps.googleapis.com/maps/api/streetview?";


    public static Home getHomeFromJson(JSONObject jsonObject) throws JSONException {
        Home home = new Home();
        home.setAddress(jsonObject.getJSONObject(ADDRESS_PARAM).getString(ONELINE_PARAM));
        home.setPropertyType(jsonObject.getJSONObject(SUMMARY_PARAM).getString(PROPERTYTYPE_PARAM));
        home.setHomeNoOfBedrooms(jsonObject.getJSONObject(BUILDING_PARAM).getJSONObject(ROOMS_PARAM).getString(BED_PARAM));
        home.setHomeNoOfBathrooms(jsonObject.getJSONObject(BUILDING_PARAM).getJSONObject(ROOMS_PARAM).getString(BATHS_PARAM));
        if (!jsonObject.getJSONObject(SUMMARY_PARAM).has(YEAR_PARAM))
            home.setYearBuilt("N/A");
        else
            home.setYearBuilt(jsonObject.getJSONObject(SUMMARY_PARAM).getString(YEAR_PARAM));
        home.setDistance(jsonObject.getJSONObject(LOCATION_PARAM).getString(DISTANCE_PARAM));
        home.setLatitude(jsonObject.getJSONObject(LOCATION_PARAM).getString(LATITUDE_PARAM));
        home.setLongitude(jsonObject.getJSONObject(LOCATION_PARAM).getString(LONGITUDE_PARAM));
        home.setImageUrl(getStreetView(home));
        home.setIsRecommended(false);

        return home;
    }

    public static ArrayList<Home> getListOfHomes(JSONObject jsonObject) {
        ArrayList<Home> homes = new ArrayList<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(PROPERTY_PARAM);
            for (int i = 0; i < jsonArray.length(); i++) {
                homes.add(HomeJsonParser.getHomeFromJson((JSONObject) jsonArray.get(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return homes;
    }

    public static String getStreetView(Home home) {
        LOCATION_LATLNG = "";
        LOCATION_LATLNG += home.getLatitude() + "," + home.getLongitude();
        return API_URL + SIZE_PARAM + SIZE_DIMENSIONS + STREET_LOCATION_PARAM + LOCATION_LATLNG +
                APIKEY_PARAM;
    }
}
