package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.home.MainActivity;
import com.example.home.R;

public class LoginFragment extends Fragment {
    private TextView mtvTitle;
    private TextView mtvSubtitle;
    private Button mbtnLogin;
    private EditText metEmail;
    private EditText metPassword;
    private ImageView mivArrow;
    private TextView mtvSwipeRight;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mtvTitle = view.findViewById(R.id.TitleTextView);
        mtvSubtitle = view.findViewById(R.id.SubtitleTextView);
        mbtnLogin = view.findViewById(R.id.LoginButton);
        metEmail = view.findViewById(R.id.EmailEditText);
        metPassword = view.findViewById(R.id.PassEditText);
        mivArrow = view.findViewById(R.id.ArrowImageView);
        mtvSwipeRight = view.findViewById(R.id.SwipeRightTextView);
        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);

            }
        });
    }
}