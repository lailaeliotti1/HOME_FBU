package com.example.home.preferences;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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
import com.example.home.stream.StreamFragment;
import com.parse.ParseUser;

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
    private int mZipCode;
    private String mZipCodeText;

    public PreferenceFragment(){
    }

    public PreferenceFragment(MainActivity mainActivity) {
        // Required empty public constructor
        this.mainActivity = mainActivity;
    }

    // TODO: Rename and change types and number of parameters
    public static PreferenceFragment newInstance(String param1, String param2) {
        PreferenceFragment fragment = new PreferenceFragment(null);
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
                String mNoOfBedrooms = parent.getItemAtPosition(position).toString();
                StreamFragment stream = new StreamFragment(mNoOfBedrooms);
                mainActivity.fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, stream).commit();
//                Intent i = new Intent(getContext(), StreamFragment.class);
//                i.putExtra("NoOfBedrooms", mNoOfBedrooms);
//                startActivity(i);
                Toast.makeText(getContext(), "Item: "+ mNoOfBedrooms, Toast.LENGTH_SHORT).show();
            }
        });

        mProperyType = view.findViewById(R.id.PropertyTypeTextView);
        propertyTypeAdapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, getResources().getStringArray(R.array.PropertyType));

        mProperyType.setAdapter(propertyTypeAdapter);
        mProperyType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mPropertyType = parent.getItemAtPosition(position).toString();
                Intent i = new Intent(getContext(), StreamFragment.class);
                i.putExtra("PropertyType", mPropertyType);
                startActivity(i);
                Toast.makeText(getContext(), "Item: "+ mPropertyType, Toast.LENGTH_SHORT).show();
            }
        });
        mZipcodeEditText = (EditText) view.findViewById(R.id.ZipcodeEditText);
        mZipCodeText = mZipcodeEditText.getText().toString();
        if(!mZipCodeText.equals("")){
            mZipCode = Integer.parseInt(mZipCodeText);
        }





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