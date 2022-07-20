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
import android.widget.LinearLayout;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.AttomData.AttomDataClient;
import com.example.home.JsonParser.HomeJsonParser;
import com.example.home.R;
import com.example.home.ZipCode.ZipcodeClient;
import com.example.home.ZipCode.ZipcodeParser;
import com.example.home.models.Home;
import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class StreamFragment extends Fragment {
    public HomeAdapter adapter;
    public RecyclerView streamRecyclerView;
    public LinearLayout StreamLinearLayout;
    private String zipCode;
    private List<Home> mHomes;
    private List<Home> mHomesRec;
    private UserPreferences mUserPreferences;
    private AttomDataClient attomDataClient = new AttomDataClient();
    private ZipcodeClient zipcodeClient = new ZipcodeClient();


    public StreamFragment() {
    }

    public StreamFragment(UserPreferences userPreferences) {
        mUserPreferences = userPreferences;
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
        ParseQuery<ParseObject> query = new ParseQuery<>("UserPreferences");
        query.whereEqualTo("user", User.getCurrentUser());
        query.setLimit(1);
        query.findInBackground(((objects, e) -> {
            for (ParseObject object : objects) {
                mUserPreferences = (UserPreferences) object;
                zipCode = String.valueOf(mUserPreferences.getZipcode());
                populateZipCode(zipCode);
            }

        }));


    }

    private void populateZipCode(String zipCode) {
        zipcodeClient.getZipcodeClient(getContext(), String.valueOf(mUserPreferences.getZipcode()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    ZipcodeParser.addLatlng(json.jsonObject, mUserPreferences);
                    populateHomeTimeline();
                } catch (JSONException e) {
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
            }
        });
    }

    private void populateHomeTimeline() {
        ParseUser user = ParseUser.getCurrentUser();
        attomDataClient.getHomeTimeline(getContext(), mUserPreferences, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                // Access a JSON array response with `json.jsonArray`
                mHomes.addAll(HomeJsonParser.getListOfHomes(json.jsonObject));
                if(mUserPreferences.getRecommendationSwitch() == true) {
                    UserPreferences recommendations = RecommendationHomes.getRecommendations(mUserPreferences);
                    populateRecommendations(recommendations);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });
    }
    private void populateRecommendations(UserPreferences recommendations){
            mHomesRec = new ArrayList<>();
            List<Home> mHomesCopy = new ArrayList<>();
            mHomesCopy.addAll(mHomes);//to iterate through mHomes while adding to mHomes
            attomDataClient.getHomeTimeline(getContext(), recommendations, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Headers headers, JSON json) {
                    mHomesRec.addAll(HomeJsonParser.getListOfHomes(json.jsonObject));
                    for(Home objRec: mHomesRec){
                        objRec.setIsRecommended(true);
                        for(Home objHome: mHomesCopy)
                            if(!objRec.getAddress().equals(objHome.getAddress()))
                                mHomes.add(objRec);
                    }
                }

                @Override
                public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                }
            });

    }
}
