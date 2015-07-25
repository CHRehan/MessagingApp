package com.example.rehan.rehansaapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends ActionBarActivity {
    Button loginButton, signUpButton;
    EditText usernameField, passwordField;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        // Parse.initialize(this, "a8bIY4CUEXV8GV4kJYnAekbiM7RhmuTRfqzqr4cM", "CsVoPpQP5jzfLh73KMEtYvdF7XKnQpMMKealtih5");

       // ParseObject testObject = new ParseObject("TestObject");
        //testObject.put("foo", "bar");
        //testObject.saveInBackground();
        // Check the user is already Login or not
        //final Intent intent = new Intent("com.example.rehan.rehansaapp.ListUsersActivity");
        final Intent intent = new Intent(getApplicationContext(), ListUsersActivity.class);
        final Intent serviceIntent = new Intent(getApplicationContext(), MessageService.class);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            startService(serviceIntent);
            startActivity(intent);
        }

        // Code for login and Sign Up
        loginButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signupButton);
        usernameField = (EditText) findViewById(R.id.loginUsername);
        passwordField = (EditText) findViewById(R.id.loginPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            startService(serviceIntent);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),"There was an error logging in.",Toast.LENGTH_LONG).show();
                            e.getMessage();
                        }
                    }
                });
            }
        });
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);
                user.signUpInBackground(new SignUpCallback() {
                    public void done(com.parse.ParseException e) {
                        if (e == null) {
                            //start sinch service
                            //start next activity
                            startService(serviceIntent);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "There was an error signing up."
                                    , Toast.LENGTH_LONG).show();
                           Log.v("message:",e.getMessage());
                            e.getMessage();
                        }
                    }
                });
            }
        });


    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, MessageService.class));
        super.onDestroy();
    }


    }
