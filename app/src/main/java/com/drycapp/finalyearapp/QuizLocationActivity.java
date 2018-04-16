package com.drycapp.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class QuizLocationActivity extends AppCompatActivity {

    private Button mNorth;
    private Button mEast;
    private Button mSouth;
    private Button mWest;
    private FirebaseAuth mAuth;
    android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //id to xml
        mNorth = findViewById(R.id.buttonNorth);
        mEast = findViewById(R.id.buttonEast);
        mSouth = findViewById(R.id.buttonSouth);
        mWest = findViewById(R.id.buttonWest);

        mAuth = FirebaseAuth.getInstance();

        //When register button is clicked...
        mNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizLocationActivity.this, QuizEventActivity.class));

            }
        });
        //When register button is clicked...
        mEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizLocationActivity.this, QuizEventActivity.class));

            }
        });
        //When register button is clicked...
        mSouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizLocationActivity.this, QuizEventActivity.class));

            }
        });
        //When register button is clicked...
        mWest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizLocationActivity.this, QuizEventActivity.class));

            }
        });
    }
}
