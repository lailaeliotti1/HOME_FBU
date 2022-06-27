package com.example.home.preferences;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.home.R;
import com.example.home.login.LoginActivity;
import com.parse.ParseUser;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class PreferenceFragment extends Fragment {
    private ConstraintLayout mPrefConLayout;
    private TextView mPreferenceTextView;
    private Button mLogoutButton;
    private AutoCompleteTextView mBedroomTextView;
    private ArrayAdapter bedroomAdapter;

    private AutoCompleteTextView mBathroomTextView;
    private ArrayAdapter bathroomAdapter;

    private AutoCompleteTextView mHouseStyleTextView;
    private ArrayAdapter houseStyleAdapter;

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
        mBedroomTextView = view.findViewById(R.id.BedroomTextView);
        bedroomAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.NoOfBedrooms));

        mBedroomTextView.setAdapter(bedroomAdapter);
        mBedroomTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Item: "+ bedItem, Toast.LENGTH_SHORT).show();
            }
        });

        mBathroomTextView = view.findViewById(R.id.BathroomTextView);
        bathroomAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.NoOfBathrooms));

        mBathroomTextView.setAdapter(bathroomAdapter);
        mBathroomTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bathItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Item: "+ bathItem, Toast.LENGTH_SHORT).show();
            }
        });

        mHouseStyleTextView = view.findViewById(R.id.HouseStyleTextView);
        houseStyleAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.houseStyle));

        mHouseStyleTextView.setAdapter(houseStyleAdapter);
        mHouseStyleTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String houseItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Item: "+ houseItem, Toast.LENGTH_SHORT).show();
            }
        });

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