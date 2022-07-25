package com.example.home.stream;

import android.util.Log;

import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RecommendationHomes {
    private static final String APARTMENT = "APARTMENT";
    private static final String CONDO = "CONDOMINIUM";
    private static final String DUPLEX = "DUPLEX";
    private static final String RESIDENTIAL = "RESIDENTIAL ACREAGE";
    private static final String TOWNHOUSE = "TOWNHOUSE/ROWHOUSE";
    private static final HashMap<String, List<String>> mRecommendPropertyType = new HashMap<>();
    private static final Double METERS_CHANGED = 8046.72; //altering user lat and long by 5 miles, 5 miles == 8046.72 meters

    public static UserPreferences getRecommendations(UserPreferences mUserPreferences) {
        UserPreferences recommendations = new UserPreferences();
        recommendations.setUser((User) ParseUser.getCurrentUser());
        recommendations.setNoOfBedrooms(mUserPreferences.getMaxNoOfBedrooms());
        recommendations.setMaxNoOfBedrooms(mUserPreferences.getMaxNoOfBedrooms());
        recommendations.setZipcode(mUserPreferences.getZipcode());
        recommendations.setRadius((int) (Math.random() * 5) + 1);
        recommendations.setPropertyType(changePropertyType(mUserPreferences, mRecommendPropertyType));
        recommendations.setLat(changeLatLng(mUserPreferences, METERS_CHANGED));
        recommendations.setLng(mUserPreferences.getLng());
        recommendations.setRecommendationSwitch(false);
        return recommendations;
    }

    public static String changePropertyType(UserPreferences mUserPreferences, HashMap<String, List<String>> mRecommendPropertyType) {
        // if user selects apartment, show condo and duplex, and townhouse and residential acreage
        mRecommendPropertyType.put(APARTMENT, Arrays.asList(CONDO, DUPLEX));
        mRecommendPropertyType.put(CONDO, Arrays.asList(APARTMENT, DUPLEX));
        mRecommendPropertyType.put(DUPLEX, Arrays.asList(APARTMENT, CONDO));
        mRecommendPropertyType.put(TOWNHOUSE, Arrays.asList(RESIDENTIAL));
        mRecommendPropertyType.put(RESIDENTIAL, Arrays.asList(TOWNHOUSE));
        String key = mUserPreferences.getPropertyType();
        List<String> propertyTypes = mRecommendPropertyType.get(key);
        String newPropertyType = propertyTypes.get((int) (Math.random() * propertyTypes.size()));
        return newPropertyType;
    }

    public static Double changeLatLng(UserPreferences mUserPreferences, Double metersChanged) {
        //radius of Earth in km == 6378.137 //
        double earthRadius = 6378.147;
        double pi = Math.PI;
        //latitude conversion
        double degreePerMeter = (180 / pi) / (earthRadius * 1000); //earth km to m
        double newLatitude = ((double) mUserPreferences.getLat() + (metersChanged * degreePerMeter));
        String formatLat = String.format("%.5f", newLatitude);
        newLatitude = Double.parseDouble(formatLat);
        return newLatitude;

    }
}
