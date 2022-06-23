package com.example.home.AppModels;

import android.app.Application;

import com.example.home.R;
import com.example.home.models.Home;
import com.example.home.models._User;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Home.class);
        ParseUser.registerSubclass(_User.class);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        // Use for monitoring Parse OkHttp traffic
        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        // See https://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);


        // Your initialization code from above here
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("G90mbl2Izwog6wDodvDxkSzFPKPkiPzTR6zAt6Fc")
                // if defined
                .clientKey("rKN3dsIyz3zEKWU4DnE8Ctva78zfSMCgx2lpNyRp")
                .server("https://parseapi.back4app.com")
                .build());

        // New test creation of object below
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();
    }
}
