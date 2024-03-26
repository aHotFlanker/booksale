package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edt_firstname = findViewById(R.id.edt_firstname);
        EditText edt_lastName = findViewById(R.id.edt_lastName);
        EditText edt_address = findViewById(R.id.edt_address);
        EditText edt_phone = findViewById(R.id.edt_phone);
        EditText edt_email = findViewById(R.id.edt_email);
        EditText edt_repeat_password = findViewById(R.id.edt_repeat_password);
        EditText edt_password = findViewById(R.id.edt_password);
        Button btn_register = findViewById(R.id.btn_register);
        Button btn_cancel = findViewById(R.id.btn_cancel);

        db = new DatabaseHelper(this);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString();
                String firstName = edt_firstname.getText().toString();
                String lastName = edt_lastName.getText().toString();
                String phoneNumber = edt_phone.getText().toString();
                String address = edt_address.getText().toString();
                String password = edt_password.getText().toString();
                String repeatPassword = edt_repeat_password.getText().toString();

                // Check if password and repeat password match
                if (!password.equals(repeatPassword)) {
                    // Show toast message if passwords do not match
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                String hashedPassword = hashPassword(password);

                boolean isCreated = db.CreateUser(email, firstName, lastName, phoneNumber, address, hashedPassword);
                if (isCreated) {
                    // User created successfully
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                    // Show a toast message for user creation completion
                    Toast.makeText(RegisterActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // User creation failed, show an error message if needed
                    Toast.makeText(RegisterActivity.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}