package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.home.preferences.PreferenceFragment;
import com.example.home.stream.StreamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Main Activity";
    public String mNoOfBedrooms;
    public String mPropertyType;
    public Integer mZipCode;
    public final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private PreferenceFragment preferenceFragment = new PreferenceFragment(this);
    private StreamFragment streamFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_preferences:
                        fragment = preferenceFragment;
                        break;
                    case R.id.action_stream:
                        fragment = streamFragment;
                        break;
                    default:
                        fragment = preferenceFragment;
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
                return true;
            }
        });



    }
    public void startStream(String mNoOfBedrooms, String mPropertyTypeText, ArrayList latlng ){
        streamFragment = new StreamFragment(mNoOfBedrooms, mPropertyTypeText, latlng);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, streamFragment).commit();

    }
}