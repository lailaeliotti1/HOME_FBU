package com.example.home.stream;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.home.AttomData.AttomDataClient;
import com.example.home.JsonParser.HomeJsonParser;
import com.example.home.MainActivity;
import com.example.home.R;
import com.example.home.ZipCode.ZipcodeClient;
import com.example.home.ZipCode.ZipcodeParser;
import com.example.home.models.Home;
import com.example.home.models.User;
import com.example.home.models.UserPreferences;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class StreamFragment extends Fragment {
    public HomeAdapter mAdapter;
    public RecyclerView mStreamRecyclerView;
    public LinearLayout mStreamLinearLayout;
    private String zipCode;
    private List<Home> mHomes;
    private List<Home> mHomesRec;
    private UserPreferences mUserPreferences;
    private AttomDataClient mAttomDataClient = new AttomDataClient();
    private ZipcodeClient mZipcodeClient = new ZipcodeClient();
    MenuItem mActionProgressItem;
    MainActivity mMainActivity;


    public StreamFragment() {
        // Required empty public constructor
    }

    public StreamFragment(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    public StreamFragment(MainActivity activity, UserPreferences userPreferences) {
        this.mMainActivity = activity;
        mUserPreferences = userPreferences;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stream, container, false);


        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStreamRecyclerView = view.findViewById(R.id.HomesRecyclerView);
        mStreamLinearLayout = view.findViewById(R.id.StreamLinearLayout);
        //initializes list of homes and adapter
        mHomes = new ArrayList<>();
        //recycler view setup
        mAdapter = new HomeAdapter(getContext(), mHomes);
        mStreamRecyclerView.setAdapter(mAdapter);
        mStreamRecyclerView.setLayoutManager(new LinearLayoutManager(mStreamLinearLayout.getContext()));
        ParseQuery<ParseObject> query = new ParseQuery<>("UserPreferences");
        query.whereEqualTo("user", User.getCurrentUser());
        query.setLimit(1);
        query.findInBackground(((objects, e) -> {
            for (ParseObject object : objects) {
                mUserPreferences = (UserPreferences) object;
                zipCode = String.valueOf(mUserPreferences.getZipcode());
                populateZipCode(zipCode);
            }

        }));

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        mMainActivity.getMenuInflater().inflate(R.menu.activity_main_actionbar, menu);
        mActionProgressItem = menu.findItem(R.id.miActionProgress);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void populateZipCode(String zipCode) {
        mZipcodeClient.getZipcodeClient(getContext(), String.valueOf(mUserPreferences.getZipcode()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                try {
                    ZipcodeParser.addLatlng(json.jsonObject, mUserPreferences);
                    populateHomeTimeline();
                } catch (JSONException e) {
                }

            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
            }
        });
    }

    private void populateHomeTimeline() {
        ParseUser user = ParseUser.getCurrentUser();
        mAttomDataClient.getHomeTimeline(getContext(), mUserPreferences, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                // Access a JSON array response with `json.jsonArray`
                mHomes.addAll(HomeJsonParser.getListOfHomes(json.jsonObject));
                if (mUserPreferences.getRecommendationSwitch() == true) {
                    UserPreferences recommendations = RecommendationHomes.getRecommendations(mUserPreferences);
                    populateRecommendations(recommendations);
                }
                mActionProgressItem.setVisible(false);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Toast.makeText(getContext(), "No Homes found", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void populateRecommendations(UserPreferences recommendations) {
        mHomesRec = new ArrayList<>();
        List<Home> mHomesCopy = new ArrayList<>();
        mHomesCopy.addAll(mHomes);//to iterate through mHomes while adding to mHomes
        mAttomDataClient.getHomeTimeline(getContext(), recommendations, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                mHomesRec.addAll(HomeJsonParser.getListOfHomes(json.jsonObject));
                for (Home objRec : mHomesRec) {
                    objRec.setIsRecommended(true);
                    // Checks if this recommendation is not in the current homes list.
                    boolean isInCurrentHomeList = false;  // Assumes it's not in the list, until we see it being repeated
                    for (Home objHome : mHomesCopy)
                        if (objRec.getAddress().equals(objHome.getAddress()))
                            isInCurrentHomeList = true;  // We see the home repeated, so it's not a new home
                    // Add the recommended home if it's not in the list
                    if (!isInCurrentHomeList)
                        mHomes.add(objRec);
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });

    }
}
