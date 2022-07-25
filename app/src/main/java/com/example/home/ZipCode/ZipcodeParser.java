package com.example.home.ZipCode;

import com.example.home.models.UserPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ZipcodeParser {
    public static final String LAT_PARAM = "lat";
    public static final String LONG_PARAM = "lng";

    public static void addLatlng(JSONObject jsonObject, UserPreferences userPreferences) throws JSONException {
        userPreferences.setLat(Double.parseDouble(String.valueOf(jsonObject.getDouble(LAT_PARAM))));
        userPreferences.setLng(Double.parseDouble(String.valueOf(jsonObject.getDouble(LONG_PARAM))));
        userPreferences.saveInBackground();
    }

}
