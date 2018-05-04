package com.drycapp.finalyearapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LostActivity extends AppCompatActivity {
    // establish variables
    private EditText mEmail;
    private Button mRecoverPassword;
    private FirebaseAuth firebaseAuth;
    // on activity creation...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        //refference variables to xml
        mEmail = findViewById(R.id.editTextLostPassword);
        mRecoverPassword = findViewById(R.id.buttonResetPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        //when recover password is clicked...
        mRecoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = mEmail.getText().toString().trim();

                if(user_email.equals("")){
                    Toast.makeText(LostActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LostActivity.this, "Your password reset has been sent to your inbox!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(LostActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(LostActivity.this, "Problem sending password reset, try again in a few minutes.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }


}
