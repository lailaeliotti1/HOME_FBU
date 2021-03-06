package com.example.home.AttomData;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.R;
import com.example.home.ZipCode.ZipcodeParser;
import com.example.home.models.UserPreferences;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;

public class AttomDataClient {
    private static final String LATITUDE_PARAM = "latitude";
    private static final String LONGITUDE_PARAM = "longitude";
    private static final String MAX_BEDS_PARAM = "maxbeds";
    private static final String RADIUS_PARAM = "radius";
    private static final String ACCEPT_PARAM = "accept";
    private static final String MIN_BEDS_PARAM = "minbeds";
    private static final String PROPERTY_TYPE_PARAM = "propertytype";
    private static final String ORDERBY_PARAM = "orderby";
    private static final String DISTANCE = "distance";
    private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/snapshot";

    public static void getHomeTimeline(Context context, UserPreferences userPreferences, JsonHttpResponseHandler jsonHttpResponseHandler) {
        AsyncHttpClient mClient = new AsyncHttpClient();
        RequestHeaders headers = new RequestHeaders();
        headers.put("apikey", context.getString(R.string.attomdata_api_key));
        headers.put(ACCEPT_PARAM, "application/json");
        RequestParams params = new RequestParams();
        params.put(LATITUDE_PARAM, String.valueOf(userPreferences.getLat()));
        params.put(LONGITUDE_PARAM, String.valueOf(userPreferences.getLng()));
        params.put(RADIUS_PARAM, String.valueOf(userPreferences.getRadius()));
        params.put(MIN_BEDS_PARAM, String.valueOf(userPreferences.getNoOfBedrooms()));
        if (userPreferences.getRecommendationSwitch() == true)
            params.put(MAX_BEDS_PARAM, String.valueOf(userPreferences.getMaxNoOfBedrooms()));
        else
            params.put(MAX_BEDS_PARAM, String.valueOf(userPreferences.getNoOfBedrooms()));
        params.put(PROPERTY_TYPE_PARAM, userPreferences.getPropertyType());
        params.put(ORDERBY_PARAM, DISTANCE);
        mClient.get(API_URL, headers, params, jsonHttpResponseHandler);
    }

}
