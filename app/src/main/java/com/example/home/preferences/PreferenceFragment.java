package com.example.home.preferences;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.MainActivity;
import com.example.home.R;
import com.example.home.login.LoginActivity;
import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.widget.AutoCompleteTextView;


public class PreferenceFragment extends Fragment {
    private ConstraintLayout mPrefConLayout;
    private TextView mPreferenceTextView;
    private Button mLogoutButton;
    private AutoCompleteTextView mBedroomTextView;
    private ArrayAdapter bedroomAdapter;
    MainActivity mainActivity;
    private AutoCompleteTextView mPropertyType;
    private ArrayAdapter mPropertyTypeAdapter;
    private EditText mZipcodeEditText;
    private Button mSaveButton;
    private Switch mRecommendationSwitch;

    private int mZipCode;

    private Bundle latlngBundle;
    private String mLatitude;
    private String mLongitude;

    public static final String FORMAT_ZIP = "info.json";
    public static final String DEGREES_ZIP = "degrees";
    private String mZipCodeText;
    private String mNoOfBedrooms;
    private String mPropertyTypeText;
    UserPreferences mUserPreferences = new UserPreferences();


    public PreferenceFragment() {
    }

    public PreferenceFragment(MainActivity mainActivity) {
        // Required empty public constructor
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserPreferences.setUser((User) User.getCurrentUser());
        //grabbed from server
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
        ParseUser user = ParseUser.getCurrentUser(); //grabbed from server

        mBedroomTextView = view.findViewById(R.id.BedroomTextView);
        initBedroomTextView();

        mPropertyType = view.findViewById(R.id.PropertyTypeTextView);
        initPropertyTypeText();
        mZipcodeEditText = (EditText) view.findViewById(R.id.ZipcodeEditText);

        mRecommendationSwitch = view.findViewById(R.id.RecommendationsSwitch);
        mRecommendationSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->{
            if(isChecked) {
                Toast.makeText(getContext(), "Recommendations ON ", Toast.LENGTH_SHORT).show();
                mUserPreferences.setRecommendationSwitch(isChecked);
            }
        });



        mSaveButton = view.findViewById(R.id.SaveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mZipCodeText = mZipcodeEditText.getText().toString();
                mUserPreferences.setZipcode(Integer.parseInt(mZipCodeText));
                mUserPreferences.saveInBackground();
                Toast.makeText(getContext(), "Preferences Saved", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).startStream(mUserPreferences);
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
        ParseQuery<UserPreferences> query = new ParseQuery<UserPreferences>(UserPreferences.class);
        query.setLimit(1);
        query.getFirstInBackground(new GetCallback<UserPreferences>() {
            public void done(UserPreferences userPreferences, ParseException e) {
                if (e == null) {
                    mUserPreferences = userPreferences;
                    mZipcodeEditText.setText(String.valueOf(mUserPreferences.getZipcode()));
                    mBedroomTextView.setText(String.valueOf(mUserPreferences.getNoOfBedrooms()),false);
                    mPropertyType.setText(mUserPreferences.getPropertyType(), false);

                    mRecommendationSwitch.setChecked(mUserPreferences.getRecommendationSwitch());
                    //set uaer pref as initial stuff
                } else {
                    // Something is wrong
                }
            }
        });

    }

    public void initBedroomTextView() {
        bedroomAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.NoOfBedrooms));

        mBedroomTextView.setAdapter(bedroomAdapter);
        mBedroomTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNoOfBedrooms = parent.getItemAtPosition(position).toString();
                mUserPreferences.setNoOfBedrooms(Integer.parseInt(mNoOfBedrooms));
                Toast.makeText(getContext(), "Item: " + mNoOfBedrooms, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initPropertyTypeText() {
        mPropertyTypeAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.PropertyType));
        mPropertyType.setAdapter(mPropertyTypeAdapter);
        mPropertyType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPropertyTypeText = parent.getItemAtPosition(position).toString();
                mUserPreferences.setPropertyType(mPropertyTypeText);
                Toast.makeText(getContext(), "Item: " + mPropertyTypeText, Toast.LENGTH_SHORT).show();
            }
        });
    }

}