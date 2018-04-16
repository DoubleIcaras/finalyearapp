package com.drycapp.finalyearapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //Creation of variables
    private EditText mName;
    private EditText mPassword;
    private TextView mLost;
    private TextView mAttempts;
    private Button mLogin;
    private Button mSignUp;
    private int mCounter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    //On activity creation...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set variables to views in layout.
        mName = findViewById(R.id.editTextName);
        mPassword = findViewById(R.id.editTextNewPassword);
        mLost = findViewById(R.id.textViewLost);
        mAttempts = findViewById(R.id.textViewAttempts);
        mLogin = findViewById(R.id.buttonLogin);
        mSignUp = findViewById(R.id.buttonSignUp);

        //Hide attempts text until incorrect attempt and instance firebase auth
        mAttempts.setText("");
        firebaseAuth = FirebaseAuth.getInstance();


        //progress bar
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);


        //check if the user is logged in or not
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //if they are logged in, send them to the landing page
        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, LandingActivity.class));
        }


        //When login button is clicked...
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Using the validate method, confirm details are correct.
                validate(mName.getText().toString(), mPassword.getText().toString());
            }
        });

        //When register button is clicked...
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));

            }
        });

        //When lost password button is clicked...
        mLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(MainActivity.this, LostActivity.class));

            }
        });

    }

    //method to check if user and password are correct, display loading bar during wait
    private void validate(String userName, String userPassword) {
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //if login is successful, show a toast and move to next activity
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, LandingActivity.class));
                    //if it's not successful, show error toast and end loading bar.
                }else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    //after 5 unsuccessful login's, lock the user from logging in for 15 minutes.
                    mCounter--;
                    mAttempts.setText("Login attempts remaining " + mCounter);
                    if(mCounter == 0){
                        mLogin.setEnabled(false);
                    }

                }
            }
        });



    }


}
