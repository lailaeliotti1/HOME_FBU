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
    public static final String APARTMENT = "APARTMENT";
    public static final String CONDO = "CONDOMINIUM";
    public static final String DUPLEX = "DUPLEX";
    public static final String RESIDENTIAL = "RESIDENTIAL ACREAGE";
    public static final String TOWNHOUSE = "TOWNHOUSE/ROWHOUSE";

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
                    Log.e("ZipcodeAPI", e.toString());
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e("Zipcode failure", response);

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
                if(mUserPreferences.getRecommendationSwitch() == true)
                    populateRecommendations(user);
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
    private void populateRecommendations(ParseUser user){
            UserPreferences recommendations = new UserPreferences();
            mHomesRec = new ArrayList<>();
            List<Home> mHomesCopy = new ArrayList<>();
            mHomesCopy.addAll(mHomes);
            recommendations.setUser((User) user);
            recommendations.setNoOfBedrooms(mUserPreferences.getNoOfBedrooms());
            recommendations.setMaxNoOfBedrooms(mUserPreferences.getMaxNoOfBedrooms());
            recommendations.setZipcode(mUserPreferences.getZipcode());
            recommendations.setRadius((int)(Math.random()*5)+1);
        // if user selects apartment, show condo and duplex, and townhouse and residential acreage
            if (mUserPreferences.getPropertyType() == APARTMENT){
                int random = (int)(Math.random()*2);
                if(random == 1)
                    recommendations.setPropertyType(CONDO);
                else
                    recommendations.setPropertyType(DUPLEX);
            }
            if (mUserPreferences.getPropertyType() == CONDO){
                int random = (int)(Math.random()*2);
                if(random == 1)
                    recommendations.setPropertyType(APARTMENT);
                else
                    recommendations.setPropertyType(DUPLEX);
            }
            if (mUserPreferences.getPropertyType() == DUPLEX){
                int random = (int)(Math.random()*2);
                if(random == 1)
                    recommendations.setPropertyType(CONDO);
                else
                    recommendations.setPropertyType(APARTMENT);
            }
            if (mUserPreferences.getPropertyType() == TOWNHOUSE)
                recommendations.setPropertyType(RESIDENTIAL);
            if(mUserPreferences.getPropertyType() == RESIDENTIAL)
                recommendations.setPropertyType(TOWNHOUSE);
            //one degree of latitude is 69 miles
            //5 miles would be .0725 approx
            recommendations.setLat(mUserPreferences.getLat());
            recommendations.setLng(mUserPreferences.getLng());
            recommendations.setRecommendationSwitch(false);
            attomDataClient.getHomeTimeline(getContext(), recommendations, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Headers headers, JSON json) {
                    mHomesRec.addAll(HomeJsonParser.getListOfHomes(json.jsonObject));
                    for(Home objRec: mHomesRec){
                        for(Home objHome: mHomesCopy)
                            if(!objRec.getAddress().equals(objHome.getAddress()))
                                mHomes.add(objRec);
                    }
                }

                @Override
                public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                }
            });

            //Creating a new UP recommendations
            //change radius
            //change bedrooms
            //change property type
            //if mUP getBedroom < 2, give an apartment/condominum... else give residential acreage
            //recommendations.setNoOfBedrooms(userP.getMaxBedrooms());
            //attomDataClient.getHomeTimeline(getContext(), recommendations);
            //in on success mHomes.addAll()
            //remove dupes
            //adapter notify data set changed
            //if mHomes[i].getAddress()


    }
}
