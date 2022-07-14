package com.example.home.stream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.home.R;
import com.example.home.models.Home;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<Home> mHomes;
    private Context context;

    public HomeAdapter(Context context, List<Home> mHomes)
    {
        this.context = context;
        this.mHomes = mHomes;
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
        Home home = mHomes.get(position);
        holder.bind(home);
    }

    @Override
    public int getItemCount() {
        return mHomes.size();
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
        private ImageView mHomeImageView;

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
            mHomeImageView = (ImageView) itemView.findViewById(R.id.HomeImageView);
        }

        public void bind(Home home) {
            mAddressTextView.setText(home.getAddress());
            mPropertyTypeText.setText(home.getPropertyType());
            mYearBuiltText.setText(home.getYearBuilt());
            mDistanceText.setText(home.getDistance());
            mBedroomText.setText(home.getHomeNoOfBedrooms());
            mBathroomText.setText(home.getHomeNoOfBathrooms());
            Glide.with(context).load(home.getImageUrl()+context.getString(R.string.google_streetview_key)).into(mHomeImageView);
        }
    }

    public void clear(){
        mHomes.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<Home> mHome)
    {
        mHome.addAll(mHome);
        notifyDataSetChanged();
    }
    }
