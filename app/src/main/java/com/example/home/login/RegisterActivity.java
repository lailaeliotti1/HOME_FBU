package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class RegisterActivity extends AppCompatActivity {
    private String TAG = "Register Activity";
    private TextView mtvLogo;
    private TextView mtvSubtitle;
    private EditText metName;
    private EditText metEmail;
    private EditText metPassword;
    private EditText metRepassword;
    private ImageView mivBackArrow;
    private TextView mtvSwipeLeft;
    private RelativeLayout mrvRegister;
    private Button mbtnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mtvLogo = findViewById(R.id.LogoTextView);
        mtvSubtitle = findViewById(R.id.SubtitleTextView);
        mbtnRegister = findViewById(R.id.RegisterButton);
        metEmail = findViewById(R.id.UsernameEditText);
        metPassword = findViewById(R.id.PassEditText);
        metRepassword = findViewById(R.id.RepasswordEditText);
        mivBackArrow = findViewById(R.id.BackArrowImageView);
        mtvSwipeLeft = findViewById(R.id.SwipeLeftTextView);

        if(ParseUser.getCurrentUser() !=null ) {
            goLoginActivity();
        }
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                if(metPassword.toString().equals(metRepassword)){
                    goLoginActivity();
                }

            }
        });
    }
    public void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user" + username + " " + password);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(RegisterActivity.this, "Issues with login!", Toast.LENGTH_SHORT).show();
                    return;
                }
                goLoginActivity();
                Toast.makeText(RegisterActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goLoginActivity(){
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}