package com.drycapp.finalyearapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class LandingActivity extends AppCompatActivity {


    //create variables
    private Button mToday;
    private Button mTomorrow;
    private Button mSettings;
    private Button mProfile;
    private FirebaseAuth mAuth;
    android.support.v7.widget.Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        //id to xml
        mToday = findViewById(R.id.buttonToday);
        mTomorrow = findViewById(R.id.buttonTomorrow);
        mSettings = findViewById(R.id.buttonSettings);
        mProfile = findViewById(R.id.buttonProfile);
        toolbar = findViewById(R.id.toolbarLanding);


        Toolbar toolbar = findViewById(R.id.toolbarLanding);
        setSupportActionBar(toolbar);


        mAuth = FirebaseAuth.getInstance();

        //When register button is clicked...
        mToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(LandingActivity.this, QuizLocationActivity.class));

            }
        });
        //When register button is clicked...
        mTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(LandingActivity.this, QuizLocationActivity.class));

            }
        });
        //When register button is clicked...
        mSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(LandingActivity.this, SettingsActivity.class));

            }
        });
        //When register button is clicked...
        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to register page
                startActivity(new Intent(LandingActivity.this, ProfileActivity.class));

            }
        });
    }





    private void Profile() {
        startActivity(new Intent(LandingActivity.this, ProfileActivity.class));
    }
    //Method that logs user out and returns them to login screen.
    private void Logout() {
        mAuth.signOut();
        finish();
        startActivity(new Intent(LandingActivity.this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
            case R.id.profileMenu:
                startActivity(new Intent(LandingActivity.this, ProfileActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
