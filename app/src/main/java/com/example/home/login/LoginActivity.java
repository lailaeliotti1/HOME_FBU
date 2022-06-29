package com.example.home.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.home.MainActivity;
import com.example.home.R;
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
    public float x1, x2, y1, y2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mtvTitle = findViewById(R.id.TitleTextView);
        mtvSubtitle = findViewById(R.id.SubtitleTextView);
        mbtnLogin = findViewById(R.id.LoginButton);
        metUsername = findViewById(R.id.EmailEditText);
        metPassword = findViewById(R.id.PassEditText);
        mivArrow = findViewById(R.id.ArrowImageView);
        mtvSwipeRight = findViewById(R.id.SwipeRightTextView);


        mbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = metUsername.getText().toString();
                String password = metPassword.getText().toString();
                loginUser(username, password);

            }
        });
    }
    public void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user " + username + " " + password);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goMainActivity(){
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
    public boolean onTouchEvent(MotionEvent touchevent){
        switch(touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x1 > x2){
                    Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }

}
