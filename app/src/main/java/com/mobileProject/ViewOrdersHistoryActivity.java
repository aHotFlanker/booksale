package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.List;

public class ViewOrdersHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders_history);
        DatabaseHelper db = new DatabaseHelper(this);
        String userId = getUserIdFromSharedPref();
        List<OrderHistory> orderHistories = db.getOrderHistory(userId);
    }



    private String getUserIdFromSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return sharedPreferences.getString("userId", "-1"); // Default value -1 if userId is not found
    }
}