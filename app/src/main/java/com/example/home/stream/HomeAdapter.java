package com.example.home.stream;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home.R;
import com.example.home.models.Home;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<Home> mHome;
    private Context context;

    public HomeAdapter(Context context, List<Home> mHome)
    {
        this.context = context;
        this.mHome = mHome;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View homeView = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false);
        homeView.setVisibility(View.VISIBLE);
        return new ViewHolder(homeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Home home = mHome.get(position);
        holder.bind(home);



    }

    @Override
    public int getItemCount() {
        return mHome.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private TextView mAddressTextView;
        private TextView mPropertyTypeText;
        private TextView mYearBuiltText;
        private TextView mDistanceText;
        private TextView mBedroomText;
        private TextView mBathroomText;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            mAddressTextView = (TextView) itemView.findViewById(R.id.AddressTextView);
            mPropertyTypeText = (TextView) itemView.findViewById(R.id.PropertyTypeText);
            mYearBuiltText = (TextView) itemView.findViewById(R.id.YearBuiltText);
            mDistanceText = (TextView) itemView.findViewById(R.id.DistanceTextView);
            mBedroomText = (TextView) itemView.findViewById(R.id.BedroomText);
            mBathroomText = (TextView) itemView.findViewById(R.id.BathroomText);
        }

        public void bind(Home home) {
            mAddressTextView.setText(home.mAddress);
            mPropertyTypeText.setText(home.mPropertyType);
            mYearBuiltText.setText(home.mYearBuilt);
            mDistanceText.setText(home.mDistance);
            mBedroomText.setText(home.mHomeNoOfBedrooms);
            mBathroomText.setText(home.mHomeNoOfBathrooms);
        }
    }

    public void clear(){
        mHome.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<Home> mHome)
    {
        mHome.addAll(mHome);
        notifyDataSetChanged();
    }
    }
