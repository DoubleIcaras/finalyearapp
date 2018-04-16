package com.drycapp.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class QuizPartySizeActivity extends AppCompatActivity {

    private Button mOne;
    private Button mTwo;
    private Button mThree;
    private Button mFour;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_party_size);

        //id to xml
        mOne = findViewById(R.id.buttonCheap);
        mTwo = findViewById(R.id.buttonModerate);
        mThree = findViewById(R.id.buttonExpensive);
        mFour = findViewById(R.id.buttonFour);

        mAuth = FirebaseAuth.getInstance();

        //When register button is clicked...
        mOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizPartySizeActivity.this, QuizBudgetActivity.class));

            }
        });
        //When register button is clicked...
        mTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizPartySizeActivity.this, QuizBudgetActivity.class));

            }
        });
        //When register button is clicked...
        mThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizPartySizeActivity.this, QuizBudgetActivity.class));

            }
        });
        //When register button is clicked...
        mFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizPartySizeActivity.this, QuizBudgetActivity.class));

            }
        });
    }
}
