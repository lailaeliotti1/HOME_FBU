package com.example.home.ZipCode;

import android.content.Context;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.R;
import com.example.home.models.UserPreferences;
import com.parse.ParseUser;

public class ZipcodeClient {
    private static final String INFO_PARAM = "/info.json/";
    private static final String DEGREES_PARAM = "/degrees";
    private static final String API_URL = "https://www.zipcodeapi.com/rest/";

    public static void getZipcodeClient(Context context, String zipcode, JsonHttpResponseHandler jsonHttpResponseHandler){
        AsyncHttpClient mClient = new AsyncHttpClient();
        mClient.get(API_URL+context.getString(R.string.zipcode_api_key)+INFO_PARAM+zipcode+DEGREES_PARAM, jsonHttpResponseHandler);
    }
}
