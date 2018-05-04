package com.drycapp.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class QuizBudgetActivity extends AppCompatActivity {

    private Button mCheap;
    private Button mModerate;
    private Button mExpensive;
    private Button mLavish;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_budget);

        //id to xml
        mCheap = findViewById(R.id.buttonCheap);
        mModerate = findViewById(R.id.buttonModerate);
        mExpensive = findViewById(R.id.buttonExpensive);
        mLavish = findViewById(R.id.buttonLavish);

        Toolbar toolbar = findViewById(R.id.toolbarBudget);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        //When register button is clicked...
        mCheap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizBudgetActivity.this, EventsDisplayActivity.class));

            }
        });
        //When register button is clicked...
        mModerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizBudgetActivity.this, QuizResultsActivity.class));

            }
        });
        //When register button is clicked...
        mExpensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizBudgetActivity.this, QuizResultsActivity.class));

            }
        });
        //When register button is clicked...
        mLavish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(QuizBudgetActivity.this, QuizResultsActivity.class));

            }
        });
    }
}
