package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
    public static final String TAG = "Register Activity";
    private TextView mtvLogo;
    private TextView mtvSubtitle;
    private EditText metUsername;
    private EditText metEmail;
    private EditText metPassword;
    private ImageView mivBackArrow;
    private TextView mtvSwipeLeft;
    private RelativeLayout mrvRegister;
    private Button mbtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        mtvLogo = findViewById(R.id.LogoTextView);
        mtvSubtitle = findViewById(R.id.SubtitleTextView);
        mbtnRegister = findViewById(R.id.RegisterButton);
        metEmail = findViewById(R.id.EmailEditText);
        metUsername = findViewById(R.id.UserNameEditText);
        metPassword = findViewById(R.id.PassEditText);
        mivBackArrow = findViewById(R.id.BackArrowImageView);
        mrvRegister = findViewById(R.id.RegisterRelativeLayout);
        mtvSwipeLeft = findViewById(R.id.SwipeLeftTextView);

        if(ParseUser.getCurrentUser() !=null ) {
            goLoginActivity();
        }
        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                    //goLoginActivity();
                    String userName = metUsername.getText().toString();
                    String password = metPassword.getText().toString();
                    String email = metEmail.getText().toString();

                    // checking if the entered text is empty or not.
                    if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
                        Toast.makeText(RegisterActivity.this, "Please enter user name and password", Toast.LENGTH_SHORT).show();
                    }

                    // calling a method to register a user.
                    registerUser(userName, password, email);


            }
        });
    }

    private void registerUser (String userName, String password, String email){
        User user = new User();

        // Set the user's username and password,
        // which can be obtained from edit text
        user.setUsername(userName);
        Log.i(TAG, metUsername.getText().toString());
        user.setPassword(password);
        Log.i(TAG, metPassword.getText().toString());
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
                    //i.putExtra("username", userName);
                } else {
                    // if we get any error then we are logging out
                    // our user and displaying an error message
                    ParseUser.logOut();
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(RegisterActivity.this, "Fail to Register User..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void goLoginActivity(){
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}