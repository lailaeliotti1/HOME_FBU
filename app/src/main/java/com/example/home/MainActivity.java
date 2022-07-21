package com.example.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.example.home.preferences.PreferenceFragment;
import com.example.home.profile.ProfileFragment;
import com.example.home.stream.StreamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    private PreferenceFragment preferenceFragment = new PreferenceFragment(this);
    private StreamFragment streamFragment = new StreamFragment(this);
    private ProfileFragment profileFragment = new ProfileFragment(ParseUser.getCurrentUser());

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
                    case R.id.action_profile:
                        fragment = profileFragment;
                        break;
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
    public void startStream(UserPreferences userPreferences){
        streamFragment = new StreamFragment(this, userPreferences);

    }
}