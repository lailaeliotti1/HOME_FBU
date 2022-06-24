package com.example.home.preferences;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.home.R;
import com.example.home.login.LoginActivity;
import com.parse.ParseUser;


public class PreferenceFragment extends Fragment {
    private ConstraintLayout mPrefConLayout;
    private TextView mPreferenceTextView;
    private Button mLogoutButton;
    private TextView mBedTextView;
    private Spinner mSpinnerBedrooms;
    private TextView mBathTextView;
    private Spinner mSpinnerBathrooms;
    private TextView mHouseStyleTextView;
    private Spinner mSpinnerHouse;
    private TextView mBudgetTextView;
    private Spinner mSpinnerBudget;

    public PreferenceFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PreferenceFragment newInstance(String param1, String param2) {
        PreferenceFragment fragment = new PreferenceFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preference, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPrefConLayout = view.findViewById(R.id.prefConLayout);
        mPreferenceTextView =  view.findViewById(R.id.PreferenceTextView);

        mBedTextView = view.findViewById(R.id.BedTextView);
        mSpinnerBedrooms = view.findViewById(R.id.spinBed);
        ArrayAdapter<CharSequence>bedAdapter=ArrayAdapter.createFromResource(getContext(), R.array.NoOfBedrooms, android.R.layout.simple_spinner_item);
        bedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerBedrooms.setAdapter(bedAdapter);

        mBathTextView = view.findViewById(R.id.BathTextView);
        mSpinnerBathrooms = view.findViewById(R.id.spinBath);
        ArrayAdapter<CharSequence>bathAdapter=ArrayAdapter.createFromResource(getContext(), R.array.NoOfBathrooms, android.R.layout.simple_spinner_item);
        bathAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerBathrooms.setAdapter(bathAdapter);

        mHouseStyleTextView = view.findViewById(R.id.HouseStyleTextView);
        mSpinnerHouse = view.findViewById(R.id.spinHouse);
        ArrayAdapter<CharSequence>locationAdapter=ArrayAdapter.createFromResource(getContext(), R.array.houseStyle, android.R.layout.simple_spinner_item);
        bedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        mSpinnerBedrooms.setAdapter(bedAdapter);


        mLogoutButton = view.findViewById(R.id.LogoutButton);
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }
}