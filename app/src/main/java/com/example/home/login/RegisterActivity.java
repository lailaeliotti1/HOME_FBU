package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.home.R;
//import com.example.home.models.User;
import com.example.home.models.User;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends AppCompatActivity {
    private TextView LogoTextView;
    private TextView mSubtitleTextView;
    private EditText mUsernameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private ImageView mBackArrowImageView;
    private TextView mSwipeLeftTextView;
    private RelativeLayout mRegisterRelativeLayout;
    private Button mbtnRegister;
    private float firstTouchX, lastTouchX, firstTouchY, lastTouchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        LogoTextView = findViewById(R.id.LogoTextView);
        mSubtitleTextView = findViewById(R.id.SubtitleTextView);
        mbtnRegister = findViewById(R.id.RegisterButton);
        mEmailEditText = findViewById(R.id.EmailEditText);
        mUsernameEditText = findViewById(R.id.UserNameEditText);
        mPasswordEditText = findViewById(R.id.PassEditText);
        mBackArrowImageView = findViewById(R.id.BackArrowImageView);
        mRegisterRelativeLayout = findViewById(R.id.RegisterRelativeLayout);
        mSwipeLeftTextView = findViewById(R.id.SwipeLeftTextView);

        if (ParseUser.getCurrentUser() != null) {
            goLoginActivity();
        }
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goLoginActivity();
                String userName = setUserName(mUsernameEditText);
                String password = setPassword(mPasswordEditText);
                String email = setEmail(mEmailEditText);
                // checking if the entered text is empty or not.
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.please_enter_username_and_password), Toast.LENGTH_SHORT).show();
                }
                // calling a method to register a user.
                registerUser(userName, password, email);
            }
        });
    }

    private void registerUser(String userName, String password, String email) {
        ParseUser user = new ParseUser();
        // Set the user's username and password,
        // which can be obtained from edit text
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);

        // calling a method to register the user.
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                // on user registration checking if
                // the error is null or not.
                if (e == null) {
                    // if the error is null we are displaying a toast message and
                    // redirecting our user to new activity and passing the user name.
                    Toast.makeText(RegisterActivity.this, "User Registered successfully", Toast.LENGTH_SHORT).show();
                    goLoginActivity();
                } else {
                    // if we get any error then we are logging out
                    // our user and displaying an error message
                    ParseUser.logOut();
                    Toast.makeText(RegisterActivity.this, "Fail to Register User..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private String setUserName(EditText userName) {
        return userName.getText().toString();
    }

    private String setPassword(EditText password) {
        return password.getText().toString();
    }

    private String setEmail(EditText email) {
        return email.getText().toString();
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstTouchX = touchevent.getX();
                firstTouchY = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                lastTouchX = touchevent.getX();
                lastTouchY = touchevent.getY();
                if (firstTouchX < lastTouchX) {
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}