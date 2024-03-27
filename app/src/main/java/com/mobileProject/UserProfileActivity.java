package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private String userId;

    private EditText edtFirstName, edtLastName, edtPhoneNumber, edtAddress,edt_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        dbHelper = new DatabaseHelper(this);

        userId = getUserIdFromSharedPref();

        // Initialize views
        edtFirstName = findViewById(R.id.edt_firstname);
        edtLastName = findViewById(R.id.edt_lastName);
        edtPhoneNumber = findViewById(R.id.edt_phone_profile);
        edtAddress = findViewById(R.id.edt_address_profile);
        edt_email = findViewById(R.id.edt_email);

        // Populate user details
        populateUserDetails();
        // Set OnClickListener for the save button
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void populateUserDetails() {
        UserDetails userDetails = dbHelper.GetUserDetails(userId);
        if (userDetails != null) {
            edt_email.setText(userDetails.getEmail());
            edtFirstName.setText(userDetails.getFirstName());
            edtLastName.setText(userDetails.getLastName());
            edtPhoneNumber.setText(userDetails.getPhoneNumber());
            edtAddress.setText(userDetails.getAddress());
        }
    }

    // Implement this method to get userId
    private String getUserIdFromSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId", "-1"); // Default value -1 if userId is not found
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database helper
        dbHelper.close();
    }


    private void saveUserProfile() {
        String firstName = edtFirstName.getText().toString().trim();
        String lastName = edtLastName.getText().toString().trim();
        String phoneNumber = edtPhoneNumber.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();


        // Update user details in the database
        boolean isUpdated = dbHelper.UpdateUserDetails(userId, firstName, lastName, phoneNumber, address);

        if (isUpdated) {

            // Show a toast message indicating success
            Toast.makeText(UserProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Show a toast message indicating failure
            Toast.makeText(UserProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
        }
    }
}