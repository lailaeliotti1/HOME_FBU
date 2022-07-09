package com.example.home.stream;

import android.content.Context;
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
import android.widget.LinearLayout;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.AttomData.AttomDataClient;
import com.example.home.JsonParser.HomeJsonParser;
import com.example.home.R;
import com.example.home.models.Home;
import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class StreamFragment extends Fragment {

    private String noOfBedrooms;
    private String propertyTypeText;
    private Integer zipCode;
    private List<Home> mHomes;
    private HomeAdapter adapter;
    private RecyclerView streamRecyclerView;
    private LinearLayout StreamLinearLayout;
    private AttomDataClient attomDataClient = new AttomDataClient();

    public StreamFragment(String noOfBedrooms, String propertyTypeText, Integer zipCode) {
        this.noOfBedrooms = noOfBedrooms;
        this.propertyTypeText = propertyTypeText;
        this.zipCode = zipCode;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        StreamLinearLayout = view.findViewById(R.id.StreamLinearLayout);
        //initializes list of homes and adapter
        mHomes = new ArrayList<>();
        //recycler view setup
        adapter = new HomeAdapter(getContext(), mHomes);
        streamRecyclerView.setAdapter(adapter);
        streamRecyclerView.setLayoutManager(new LinearLayoutManager(StreamLinearLayout.getContext()));
        populateHomeTimeline();

    }

    private void populateHomeTimeline(){
        ParseUser user = ParseUser.getCurrentUser();//grabbed from server
        ParseQuery<UserPreferences> query = new ParseQuery<UserPreferences>(UserPreferences.class);
        query.whereEqualTo("user", user);
        query.findInBackground(new FindCallback<UserPreferences>() {
            @Override
            public void done(List<UserPreferences> objects, ParseException e) {
                for(UserPreferences obj: objects){
                    attomDataClient.getHomeTimeline(getContext(), obj, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Headers headers, JSON json) {
                            // Access a JSON array response with `json.jsonArray`
                            mHomes.addAll(HomeJsonParser.getListOfHomes(json.jsonObject));
                            adapter.notifyDataSetChanged();
                            Log.d("Home size", String.valueOf(mHomes.size()));
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
        });
    }
}