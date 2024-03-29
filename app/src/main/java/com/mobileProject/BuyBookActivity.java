package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class BuyBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_book);
        Intent intent = getIntent();
        TextView bookName = findViewById(R.id.tvBookName);
        TextView sellerName = findViewById(R.id.tvSeller);
        TextView price = findViewById(R.id.tvPrice);
        TextView details = findViewById(R.id.tvFullDetails);

        if(intent !=null){
            intent.getIntExtra("listingID", 0);
            bookName.setText(         intent.getStringExtra("bName")  );
            sellerName.setText(       intent.getStringExtra("sellerName") );
            intent.getIntExtra("sellerID", 0);
            price.setText(       "Price: CAD$" + Double.toString( intent.getDoubleExtra("price", 0.0) ));
            details.setText(     intent.getStringExtra("details"));
        }
    }
}