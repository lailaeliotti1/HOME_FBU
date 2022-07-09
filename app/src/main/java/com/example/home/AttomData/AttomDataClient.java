package com.example.home.AttomData;

import android.content.Context;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.R;
import com.example.home.models.UserPreferences;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AttomDataClient {
    public static final String LATITUDE_PARAM = "latitude";
        private static final String LONGITUDE_PARAM = "longitude";
        private static final String MAX_BEDS_PARAM = "maxbeds";
        private static final String RADIUS_PARAM = "radius";
        private static final String MIN_BEDS_PARAM = "minbeds";
        private static final String PROPERTY_TYPE_PARAM = "propertytype";
        private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/snapshot?";

        public static void getHomeTimeline(Context context, UserPreferences userPreferences, JsonHttpResponseHandler jsonHttpResponseHandler){
            AsyncHttpClient mClient = new AsyncHttpClient();
            RequestHeaders headers = new RequestHeaders();
            headers.put("apikey", context.getString(R.string.attomdata_api_key));
            RequestParams params = new RequestParams();
            params.put(LATITUDE_PARAM, "47.628891");
            params.put(LONGITUDE_PARAM, "-122.341408");
            params.put(RADIUS_PARAM, "1");
            ParseUser user = ParseUser.getCurrentUser();
            //grabbed from server
            params.put(MIN_BEDS_PARAM, (Integer) userPreferences.getNoOfBedrooms());
            params.put(MAX_BEDS_PARAM, (Integer) userPreferences.getNoOfBedrooms());
            params.put(PROPERTY_TYPE_PARAM, userPreferences.getPropertyType());
            Log.e("api", API_URL + headers+ params);
            mClient.get(API_URL, headers, params, jsonHttpResponseHandler);
        }

}
