package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.home.MainActivity;
import com.example.home.R;
import com.google.android.material.snackbar.Snackbar;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    private TextView mTitleTextView;
    private TextView mSubtitleTextView;
    private Button mLoginButton;
    private EditText mUsernameEditText;

    private EditText mPasswordEditText;
    private ImageView mArrowImageView;
    private TextView mSwipeRightTextView;
    private ConstraintLayout mConstraintLayout;
    private float firstTouchX, lastTouchX, firstTouchY, lastTouchY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //if user is already logged in
        if (ParseUser.getCurrentUser() != null) {
            goMainActivity();
        }

        mTitleTextView = findViewById(R.id.TitleTextView);
        mSubtitleTextView = findViewById(R.id.SubtitleTextView);
        mLoginButton = findViewById(R.id.LoginButton);
        mUsernameEditText = findViewById(R.id.EmailEditText);
        mPasswordEditText = findViewById(R.id.PassEditText);
        mArrowImageView = findViewById(R.id.ArrowImageView);
        mSwipeRightTextView = findViewById(R.id.SwipeRightTextView);
        mConstraintLayout = findViewById(R.id.LoginConsLayout);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                loginUser(username, password);

            }
        });
    }

    public void loginUser(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    //shakes edit text boxes if incorrect user/password
                    Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);
                    mUsernameEditText.startAnimation(shake);
                    mPasswordEditText.startAnimation(shake);
                    Snackbar.make(mConstraintLayout, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                goMainActivity();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
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
                if (firstTouchX > lastTouchX) {
                    Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
