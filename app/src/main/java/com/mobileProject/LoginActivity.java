package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;


import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        Button btnLogin = findViewById(R.id.btnLogin);
        Button btn_SignUp = findViewById(R.id.btn_SignUp);
        EditText edt_login_email = findViewById(R.id.edt_login_email);
        EditText edt_password = findViewById(R.id.edt_password);
        DatabaseHelper db = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref",
                MODE_PRIVATE);



        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = edt_login_email.getText().toString();
               String password = db.getPassword(email);
               String hashedPassword = hashPassword(edt_password.getText().toString());
               if(password.equals(hashedPassword)){
                   SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userId", db.getUserId(email));
                    editor.putString("email",email);
                    editor.apply();
                   startActivity(new Intent(LoginActivity.this, MainActivity.class));
               }
               else {
                   Toast.makeText(LoginActivity.this, "Incorrect Password " + hashedPassword +" " + password, Toast.LENGTH_LONG).show();
               }
            }

        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
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