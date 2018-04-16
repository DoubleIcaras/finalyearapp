package com.drycapp.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class QuizEventActivity extends AppCompatActivity {

    private Button mFood;
    private Button mCulture;
    private Button mNightlife;
    private Button mWellness;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type);

        //id to xml
        mFood = findViewById(R.id.buttonFood);
        mCulture = findViewById(R.id.buttonCulture);
        mNightlife = findViewById(R.id.buttonNightlife);
        mWellness = findViewById(R.id.buttonWellness);

        mAuth = FirebaseAuth.getInstance();

        //When register button is clicked...
        mFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizEventActivity.this, QuizPartySizeActivity.class));

            }
        });
        //When register button is clicked...
        mCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizEventActivity.this, QuizPartySizeActivity.class));

            }
        });
        //When register button is clicked...
        mNightlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizEventActivity.this, QuizPartySizeActivity.class));

            }
        });
        //When register button is clicked...
        mWellness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizEventActivity.this, QuizPartySizeActivity.class));

            }
        });
    }
}
