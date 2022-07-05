package com.example.home.stream;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.R;
import com.example.home.models.Home;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class StreamFragment extends Fragment {

    public String mNoOfBedrooms;
    public String mPropertyTypeText;
    public Integer mZipCode;
    public List<Home> mHome;
    public HomeAdapter adapter;
    public RecyclerView streamRecyclerView;
    public static final String LATITUDE_PARAM = "latitude";
    public static final String LONGITUDE_PARAM = "longitude";
    public static final String RADIUS_PARAM = "radius";
    public static final String MIN_BEDS_PARAM = "minbeds";
    public static final String MAX_BEDS_PARAM = "maxbeds";
    public static final String PROPERTY_TYPE_PARAM = "propertytype";


    public StreamFragment(String mNoOfBedrooms, String mPropertyTypeText, Integer mZipCode) {
        this.mNoOfBedrooms = mNoOfBedrooms;
        this.mPropertyTypeText = mPropertyTypeText;
        this.mZipCode = mZipCode;
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stream, container, false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        streamRecyclerView = view.findViewById(R.id.HomesRecyclerView);
        //initializes list of homes and adapter
        mHome = new ArrayList<>();
        //recycler view setup
        adapter = new HomeAdapter(getContext(), mHome);
        streamRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        streamRecyclerView.setAdapter(adapter);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestHeaders headers = new RequestHeaders();
        headers.put("apikey", getContext().getString(R.string.attomdata_api_key));
        RequestParams params = new RequestParams();

        params.put(LATITUDE_PARAM, "47.628891");
        params.put(LONGITUDE_PARAM, "-122.341408");
        params.put(RADIUS_PARAM, "1");
        params.put(MIN_BEDS_PARAM, mNoOfBedrooms);
        params.put(MAX_BEDS_PARAM, mNoOfBedrooms);
        params.put(PROPERTY_TYPE_PARAM, mPropertyTypeText);
        client.get("https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/snapshot?", headers, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                // Access a JSON array response with `json.jsonArray`
                //Log.d("DEBUG ARRAY", json.jsonArray.toString());
                //adapter.notifyDataSetChanged();
                // Access a JSON object response with `json.jsonObject`
                Log.d("DEBUG OBJECT", json.jsonObject.toString());
            }
            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e("ERROR:", response.toString());

            }
        });
    }

}