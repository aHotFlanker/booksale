package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BuyBookActivity extends AppCompatActivity {

    int listingID;
    int buyerID = 0;
    String deliverOption = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_book);
        Intent intent = getIntent();
        TextView bookName = findViewById(R.id.tvBookName);
        TextView sellerName = findViewById(R.id.tvSeller);
        TextView price = findViewById(R.id.tvPrice);
        TextView details = findViewById(R.id.tvFullDetails);
        Button confirm = findViewById(R.id.btnConfirm);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        RadioButton pickUp = findViewById(R.id.rdBtnPickup);
        TextView pickUpText = findViewById(R.id.rdBtnPickup);
        TextView deliverText = findViewById(R.id.rdbtnProfileAddr);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int sellerID =0;

        if(intent !=null){
            listingID = intent.getIntExtra("listingID", 0);
            bookName.setText(         intent.getStringExtra("bName")  );
            sellerName.setText(       intent.getStringExtra("sellerName") );
            sellerID = intent.getIntExtra("sellerID", 0);
            price.setText(       "Price: CAD$" + Double.toString( intent.getDoubleExtra("price", 0.0) ));
            details.setText(     intent.getStringExtra("details"));
        }
        buyerID = Integer.parseInt(sharedPreferences.getString("userId", "-1")); // Default value -1 if userId is not found
        String deliverAddress = dbHelper.getUserAddress(buyerID);
        deliverText.setText("Deliver to:\n" + deliverAddress);
        String sellerAddress = dbHelper.getUserAddress(sellerID);
        pickUpText.setText("Pick Up at:\n" + sellerAddress);
        confirm.setOnClickListener(new View.OnClickListener() {
            boolean inserted;
            @Override
            public void onClick(View v) {


                if( pickUp.isChecked()){
                    deliverOption="pickUp";
                }else {
                    deliverOption="deliver";
                }



                inserted = dbHelper.insertOrderRecord( listingID, buyerID,  deliverOption, false,  "ordered");
                if (inserted) {
                    Toast.makeText(BuyBookActivity.this,"Order Completed! ",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(BuyBookActivity.this,"Order Failed! ",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}