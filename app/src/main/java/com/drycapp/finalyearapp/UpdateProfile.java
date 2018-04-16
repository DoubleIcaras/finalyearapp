package com.drycapp.finalyearapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    private EditText mUpdateName, mUpdateEmail, mUpdatePhone;
    private Button mSave;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mUpdateName = findViewById(R.id.editTextUserNameUpdate);
        mUpdateEmail = findViewById(R.id.editTextUserEmailUpdate);
        mUpdatePhone = findViewById(R.id.editTextUserNumberUpdate);
        mSave = findViewById(R.id.buttonUpdateProfileSave);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(mAuth.getCurrentUser().getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                if (userProfile != null) {
                    mUpdatePhone.setText(userProfile.getUser_phone());
                    mUpdateEmail.setText(userProfile.getUser_email());
                    mUpdateName.setText(userProfile.getUser_name());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mUpdateName.getText().toString();
                String email = mUpdateEmail.getText().toString();
                String phone = mUpdatePhone.getText().toString();

                UserProfile userProfile = new UserProfile(phone, email, name);

                databaseReference.setValue(userProfile);

                finish();
            }
        });


    }
}