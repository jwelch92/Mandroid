package com.sinch.messagingtutorialskeleton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.messagingtutorialskeleton.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends Activity {

    private Button signUpButton;
    private Button loginButton;
    private EditText usernameField;
    private EditText passwordField;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_login);
        Parse.initialize(this, "IMksGrBUs3Tt8ObxYnaZlvsBWk8PHpA59iLVtlrb", "7GidxRxsrv3ZVzOc6hbOUnUuaTdLYMPLEykAy00i");

        loginButton = (Button)findViewById(R.id.loginButton);
        signUpButton = (Button)findViewById(R.id.signupButton);
        usernameField = (EditText)findViewById(R.id.loginUsername);
        passwordField = (EditText)findViewById(R.id.loginPassword);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong username/password combo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // start new activity
                            Toast.makeText(getApplicationContext(), "Sign Up successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "There was an error signing up.",
                                   Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

    }

}
