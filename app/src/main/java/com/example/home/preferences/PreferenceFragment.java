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
import android.widget.TextView;
import android.widget.Toast;

import com.example.home.MainActivity;
import com.example.home.R;
import com.example.home.login.LoginActivity;
import com.example.home.models.User;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.widget.AutoCompleteTextView;


public class PreferenceFragment extends Fragment {
    private ConstraintLayout mPrefConLayout;
    private TextView mPreferenceTextView;
    private Button mLogoutButton;
    private AutoCompleteTextView mBedroomTextView;
    private ArrayAdapter bedroomAdapter;
    MainActivity mainActivity;
    private AutoCompleteTextView mProperyType;
    private ArrayAdapter propertyTypeAdapter;
    private EditText mZipcodeEditText;
    private Button mSaveButton;

    private int mZipCode;
    private String mZipCodeText;
    private String mNoOfBedrooms;
    private String mPropertyTypeText;
    //private UserPreferences mUserPreferences = new UserPreferences();



    public PreferenceFragment(){
    }

    public PreferenceFragment(MainActivity mainActivity) {
        // Required empty public constructor
        this.mainActivity = mainActivity;
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
        ParseUser user = ParseUser.getCurrentUser(); //grabbed from server
        //mUserPreferences.setParseObject(user.getParseObject("UserPreferences"));//grabbed uP from server, the column in User

        mBedroomTextView = view.findViewById(R.id.BedroomTextView);
        initBedroomTextView();

        mProperyType = view.findViewById(R.id.PropertyTypeTextView);
        initPropertyTypeText();
        mZipcodeEditText = (EditText) view.findViewById(R.id.ZipcodeEditText);

        mSaveButton = view.findViewById(R.id.SaveButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user.put("UserPreferences", mUserPreferences.getUserPreferences());
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.e("startStream", "run");
                        } else {
                            // Error
                        }
                    }
                });
                ((MainActivity)getActivity()).startStream(mNoOfBedrooms, mPropertyTypeText, mZipCode);
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
    public void initBedroomTextView(){
        bedroomAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.NoOfBedrooms));

        mBedroomTextView.setAdapter(bedroomAdapter);
        mBedroomTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNoOfBedrooms = parent.getItemAtPosition(position).toString();
                //Log.e("bed", mNoOfBedrooms);
                //mUserPreferences.setNoOfBedrooms(mNoOfBedrooms);
                Toast.makeText(getContext(), "Item: "+ mNoOfBedrooms, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initPropertyTypeText(){
        propertyTypeAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.PropertyType));

        mProperyType.setAdapter(propertyTypeAdapter);
        mProperyType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPropertyTypeText = parent.getItemAtPosition(position).toString();
                //mUserPreferences.setPropertyType(mPropertyTypeText);
                Toast.makeText(getContext(), "Item: "+ mPropertyTypeText, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initZipcodeEditText(){
        mZipCodeText = mZipcodeEditText.getText().toString();
        if(!mZipCodeText.equals("")){
            mZipCode = Integer.parseInt(mZipCodeText);
        }
    }

}