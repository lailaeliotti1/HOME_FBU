package com.example.home.AppModels;

import android.app.Application;
import android.content.res.Resources;

import com.example.home.R;
import com.example.home.login.RegisterActivity;
import com.example.home.models.Home;
import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Home.class);
        ParseUser.registerSubclass(User.class);
        ParseObject.registerSubclass(UserPreferences.class);


        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);



        // Your initialization code from above here
        Resources res = getResources();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(res.getString(R.string.back4app_app_id))
                // if defined
                .clientKey(res.getString(R.string.back4app_client_key))
                .server("https://parseapi.back4app.com")
                .build());

    }
}
