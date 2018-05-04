package com.drycapp.finalyearapp;

import android.content.Intent;
import android.content.RestrictionEntry;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    //create variables
    private EditText mUsername, mPassword, mEmail, mPhone;
    private Button mRegister;
    private TextView mLogin;
    private FirebaseAuth mAuth;
    private static int PICK_IMAGE = 123;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ImageView mProfilePicture;
    private FirebaseStorage firebaseStorage;


    FirebaseDatabase firebaseDatabase;
    Uri imagePath;
    String user_email, user_name, user_phone, user_password;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                mProfilePicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //on creation of activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupViews();
        //Add Toolbar to Main screen



        //FireBase instance declared
        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReferenceFromUrl("https://drycapp-91496.firebaseio.com/");


        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        mProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*"); //Application/doc/pdf
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Profile Picture"), PICK_IMAGE);
            }
        });




        //When register is clicked
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if all fields are okay using validation method
                if(validate()){
                    //get user info
                    String user_email = mEmail.getText().toString().trim();
                    String user_password = mPassword.getText().toString().trim();
                    String user_phone = mPhone.getText().toString().trim();
                    String user_name = mUsername.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(user_email, user_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                uploadAvatar();
                                uploadProfileData();
                                sendEmailVerification();
                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        //Return user back to login page
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }


    //setup views by id from xml
    private void setupViews() {
        mUsername = findViewById(R.id.editTextNewUsername);
        mPassword = findViewById(R.id.editTextNewPassword);
        mEmail = findViewById(R.id.editTextNewEmail);
        mPhone = findViewById(R.id.editTextPhone);
        mRegister = findViewById(R.id.buttonRegister);
        mLogin = findViewById(R.id.textViewLogin);
        mProfilePicture = findViewById(R.id.imageViewProfilePicture);
    }

    //validate if all fields are okay and not empty
    private Boolean validate() {
        Boolean result = false;

        //convert inputs to strings
        String user_name = mUsername.getText().toString();
        String user_password = mPassword.getText().toString();
        String user_email = mEmail.getText().toString();
        String user_phone = mPhone.getText().toString();

        //check if any fields are empty, if not then return result
        if (user_name.isEmpty() || user_password.isEmpty() || user_email.isEmpty() || user_phone.isEmpty() || imagePath == null) {
            Toast.makeText(this, "All fields are required for registration.", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void uploadAvatar(){
        //DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        StorageReference imageReference = storageReference.child(mAuth.getCurrentUser().getUid()).child("Images").child("ProfilePicture");//UserID/Images/ProfilePicture/
        UploadTask uploadTask = imageReference.putFile(imagePath);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Something went wrong. Try again later.", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(RegisterActivity.this, "Image Uploaded, Yeah, Big Boy!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadProfileData(){
        DatabaseReference mChild = databaseReference.push();
        UserProfile userProfile = new UserProfile(user_name, user_email, user_phone);
        mChild.setValue(userProfile);

    }
}
