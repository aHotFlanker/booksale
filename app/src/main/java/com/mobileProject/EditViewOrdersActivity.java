package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditViewOrdersActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view_orders);
        TextView tvBookName = findViewById(R.id.tvBookName);
        TextView tvSeller = findViewById(R.id.tvSeller);
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvDesc = findViewById(R.id.tvFullDetails);
        RadioButton rdbtnP = findViewById(R.id.rdBtnPickup);
        RadioButton rdbtnD = findViewById(R.id.rdbtnProfileAddr);
        Button btn = findViewById(R.id.btnConfirm);
        TextView tvBuyerAddr = findViewById(R.id.tvBuyerAddress);
        TextView tvSellerAddr = findViewById(R.id.tvSellerAddress);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("BookName");
            String seller = intent.getStringExtra("Name");
            double priceEntered = intent.getDoubleExtra("price", 0);
            String descEntered = intent.getStringExtra("desc");
            String delivery = intent.getStringExtra("delivery");
            int id = intent.getIntExtra("orderID",0);
            int buyerId = intent.getIntExtra("buyerId",0);
            int sellerId = intent.getIntExtra("sellerId", 0);

            tvBookName.setText(name);
            tvSeller.setText("SELLER: " + seller);
            tvPrice.setText("PRICE: " + priceEntered);
            tvDesc.setText(descEntered);

            databaseHelper = new DatabaseHelper(this);
            Cursor buyerAddr = databaseHelper.getAddress(buyerId);
            Cursor sellerAddr = databaseHelper.getAddress(sellerId);

            if (buyerAddr != null && buyerAddr.moveToFirst() && sellerAddr != null && sellerAddr.moveToFirst()) {
                tvBuyerAddr.setText(buyerAddr.getString(0));
                tvSellerAddr.setText(sellerAddr.getString(0));
            }



            if (delivery.equalsIgnoreCase("PickUp")) {
                rdbtnP.setChecked(true);
            } else {
                rdbtnD.setChecked(true);
            }
            databaseHelper = new DatabaseHelper(this);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rdbtnD.isChecked()) {
                        if(databaseHelper.updateDelivery("deliver", id)) {
                            Toast.makeText(EditViewOrdersActivity.this, "success", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(EditViewOrdersActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        if(databaseHelper.updateDelivery("deliver", id)) {
                            Toast.makeText(EditViewOrdersActivity.this, "success", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(EditViewOrdersActivity.this, "error", Toast.LENGTH_LONG).show();
                        }
                    }

                    startActivity(new Intent(EditViewOrdersActivity.this, MainActivity.class));
                }
            });




        }
    }
}