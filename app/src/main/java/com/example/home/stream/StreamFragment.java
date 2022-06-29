package com.example.home.stream;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.R;

import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StreamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StreamFragment extends Fragment {

    public String mNoOfBedrooms;
    public static final String LATITUDE_PARAM = "latitude=";
    public static final String LONGITUDE_PARAM = "&longitude=";
    public static final String RADIUS_PARAM = "&radius=";
    public static final String MIN_BEDS_PARAM = "&minbeds=";
    public static final String MAX_BEDS_PARAM = "&maxbeds=";
    public static final String PROPERTY_TYPE_PARAM = "&propertytype=";


    public StreamFragment(String mNoOfBedrooms) {
        this.mNoOfBedrooms = mNoOfBedrooms;
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StreamFragment newInstance(String param1, String param2) {
        StreamFragment fragment = new StreamFragment("");
        return fragment;
    }

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
        return inflater.inflate(R.layout.fragment_stream, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvMovies = view.findViewById(R.id.HomesRecyclerView);



        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put(LATITUDE_PARAM, "5");
        params.put(LONGITUDE_PARAM, "5");
        params.put(RADIUS_PARAM, "20");
        params.put(MIN_BEDS_PARAM, getActivity().getIntent().getParcelableExtra("mNoOfBedrooms"));
        params.put(MAX_BEDS_PARAM, getActivity().getIntent().getParcelableExtra("mNoOfBedrooms"));
        params.put(PROPERTY_TYPE_PARAM, getActivity().getIntent().getParcelableExtra("mPropertyType"));
        client.get("https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/snapshot?", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                // Access a JSON array response with `json.jsonArray`
                Log.d("DEBUG ARRAY", json.jsonArray.toString());
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