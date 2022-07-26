package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.home.models.UserPreferences;
import com.example.home.preferences.PreferenceFragment;
import com.example.home.profile.ProfileFragment;
import com.example.home.stream.StreamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    public final FragmentManager mFragmentManager = getSupportFragmentManager();
    private BottomNavigationView mBottomNavigationView;
    private PreferenceFragment mPreferenceFragment = new PreferenceFragment(this);
    private StreamFragment mStreamFragment = new StreamFragment(this);
    private ProfileFragment mProfileFragment = new ProfileFragment(ParseUser.getCurrentUser());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        fragment = mProfileFragment;
                        break;
                    case R.id.action_preferences:
                        fragment = mPreferenceFragment;
                        break;
                    case R.id.action_stream:
                        fragment = mStreamFragment;
                        break;
                    default:
                        fragment = mPreferenceFragment;
                        break;
                }
                mFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
                return true;
            }
        });
    }

    public void startStream(UserPreferences userPreferences) {
        mStreamFragment = new StreamFragment(this, userPreferences);

    }
}