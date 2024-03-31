package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView userName = findViewById(R.id.tvUserName);
        TextView remainder = findViewById(R.id.tvRemainder);
        ImageView buyBook = findViewById(R.id.imgBuyBook);
        ImageView sellShareBook = findViewById(R.id.imgSellShareBook);
        ImageView viewOrderDetail = findViewById(R.id.imgViewOrderDetail);
        ImageView viewOrderStatus = findViewById(R.id.imgViewOrderStatus);
        ImageView viewOrderHistory = findViewById(R.id.imgViewOrderHistory);
        ImageView editProfile = findViewById(R.id.imgEditProfile);
        Button btnLogout = findViewById(R.id.btnLogOut);
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
        SharedPreferences sharedPreferences = getApplicationContext().
                getSharedPreferences("MyPref", MODE_PRIVATE);

        String logedInUserId = sharedPreferences.getString("userId", "");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        userName.setText("Welcome, " + logedInUserId);
       
        //String userName1 = (databaseHelper.userName(logedInUserId)).getString(0);
<<<<<<< Updated upstream
        
=======
>>>>>>> Stashed changes
        //userName.setText(userName1);

        try {
            Cursor cursorBuyerId = databaseHelper.getBuyerId();
            int a = 0;
            while (cursorBuyerId.moveToNext()) {

                if (cursorBuyerId.getString(2).equals(logedInUserId)) {
                    String getBookName1 = (databaseHelper.getBookinfo1(logedInUserId).getString(a).toString());
                    a++;
                    if (cursorBuyerId.getString(5).equals("Ready for pickup") || cursorBuyerId.getString(5).equals("Shipped")) {
                        remainder.setText("Check the orders");
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "No content to show for buying", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes


        buyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchBookActivity.class));
            }
        });

        sellShareBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SellShareActivity.class));
            }
        });

        viewOrderDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderDetailActivity.class));

            }
        });

        viewOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewOrderStatusActivity.class));
            }
        });

        viewOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewOrdersHistoryActivity.class));
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }
}