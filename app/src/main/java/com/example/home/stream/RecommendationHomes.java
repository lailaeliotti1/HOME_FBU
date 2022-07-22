package com.example.home.stream;

import android.util.Log;

import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.ParseUser;

public class RecommendationHomes {
    public static final String APARTMENT = "APARTMENT";
    public static final String CONDO = "CONDOMINIUM";
    public static final String DUPLEX = "DUPLEX";
    public static final String RESIDENTIAL = "RESIDENTIAL ACREAGE";
    public static final String TOWNHOUSE = "TOWNHOUSE/ROWHOUSE";
    public static UserPreferences getRecommendations(UserPreferences mUserPreferences){
        UserPreferences recommendations = new UserPreferences();
        recommendations.setUser((User) ParseUser.getCurrentUser());
        recommendations.setNoOfBedrooms(mUserPreferences.getNoOfBedrooms());
        recommendations.setMaxNoOfBedrooms(mUserPreferences.getMaxNoOfBedrooms());
        recommendations.setZipcode(mUserPreferences.getZipcode());
        recommendations.setRadius((int)(Math.random()*5)+1);
        //
        // if user selects apartment, show condo and duplex, and townhouse and residential acreage
        if (mUserPreferences.getPropertyType().equals(APARTMENT)){
            int random = (int)(Math.random()*2);
            if(random == 1)
                recommendations.setPropertyType(CONDO);
            else
                recommendations.setPropertyType(DUPLEX);
        }
        if (mUserPreferences.getPropertyType().equals(CONDO)){
            int random = (int)(Math.random()*2);
            if(random == 1)
                recommendations.setPropertyType(APARTMENT);
            else
                recommendations.setPropertyType(DUPLEX);
        }
        if (mUserPreferences.getPropertyType().equals(DUPLEX)){
            int random = (int)(Math.random()*2);
            if(random == 1)
                recommendations.setPropertyType(CONDO);
            else
                recommendations.setPropertyType(APARTMENT);
        }
        if (mUserPreferences.getPropertyType().equals(TOWNHOUSE))
            recommendations.setPropertyType(RESIDENTIAL);
        if(mUserPreferences.getPropertyType().equals(RESIDENTIAL))
            recommendations.setPropertyType(TOWNHOUSE);

        recommendations.setLat(changeLatLng(mUserPreferences));
        recommendations.setLng(mUserPreferences.getLng());
        recommendations.setRecommendationSwitch(false);
        return recommendations;
    }
    public static Double changeLatLng(UserPreferences mUserPreferences){
        /*altering user lat and long by 5 miles
            5 miles == 8046.72 meters
            radius of Earth in km == 6378.137 */
        double earthRadius = 6378.147;
        double pi = Math.PI;
        double metersChanged = 8046.72;
        //latitude conversion
        double degreePerMeter =  (180/pi) / (earthRadius * 1000); //earth km to m
        double newLatitude = ((double)mUserPreferences.getLat() + (metersChanged * degreePerMeter));
        String formatLat = String.format("%.5f",newLatitude);
        newLatitude = Double.parseDouble(formatLat);
        return newLatitude;

    }
}
