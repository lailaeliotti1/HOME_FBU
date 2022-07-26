package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private String TAG = "Login Activity";
    private TextView mtvTitle;
    private TextView mtvSubtitle;
    private Button mbtnLogin;
    private EditText metUsername;
    private EditText metPassword;
    private ImageView mivArrow;
    private TextView mtvSwipeRight;
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

        mtvTitle = findViewById(R.id.TitleTextView);
        mtvSubtitle = findViewById(R.id.SubtitleTextView);
        mbtnLogin = findViewById(R.id.LoginButton);
        metUsername = findViewById(R.id.EmailEditText);
        metPassword = findViewById(R.id.PassEditText);
        mivArrow = findViewById(R.id.ArrowImageView);
        mtvSwipeRight = findViewById(R.id.SwipeRightTextView);
        mConstraintLayout = findViewById(R.id.LoginConsLayout);


        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = metUsername.getText().toString();
                String password = metPassword.getText().toString();
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
                    metUsername.startAnimation(shake);
                    metPassword.startAnimation(shake);
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
