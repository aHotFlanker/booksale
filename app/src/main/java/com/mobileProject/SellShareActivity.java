package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SellShareActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_share);

        RadioButton rdBtnSell = findViewById(R.id.rdbtnSell);
        RadioButton rdbtnShare = findViewById(R.id.rdbtnShare);

        EditText edTxtName = findViewById(R.id.edTxtName);
        EditText edTxtPrice = findViewById(R.id.edTxtPrice);
        EditText edTxtDetail = findViewById(R.id.edTxtDetail);

        RadioGroup rdGr = findViewById(R.id.radioGroup2);
        Button btn = findViewById(R.id.btnConfirmSellShare);
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);

        rdGr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = findViewById(checkedId);
                if (selected == rdbtnShare) {
                    edTxtPrice.setEnabled(false);
                    edTxtPrice.setBackground(getDrawable(R.drawable.ed_txt_bg_disabled));
                    edTxtPrice.getText().clear();
                    edTxtPrice.setHintTextColor(getResources().getColor(R.color.white));
                } else {
                    edTxtPrice.setEnabled(true);
                    edTxtPrice.setBackground(getDrawable(R.drawable.ed_txt_bg));
                    edTxtPrice.setHintTextColor(getResources().getColor(R.color.gray));
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdBtnSell.isChecked()) { //Condition if the sell option is checked.
                    if(TextUtils.isEmpty(edTxtName.getText().toString()) ||
                            TextUtils.isEmpty(edTxtPrice.getText().toString())) //form validation to ensure all the input are inserted
                    {
                        Toast.makeText(SellShareActivity.this, "Please fill in all the required information.", Toast.LENGTH_LONG).show();
                        edTxtName.setError("Please enter the information for name.");
                        edTxtPrice.setError("Please enter the information");
                    } else { //Order Processing

                        //Getting inputs from the EditText.
                        String bookName = edTxtName.getText().toString();
                        Cursor bookFound = databaseHelper.findBookName(bookName);
                        double price = Double.parseDouble(edTxtPrice.getText().toString());
                        String orderDesc = edTxtDetail.getText().toString();

                        //If the book has been found on the database condition
                        if (bookFound != null && bookFound.moveToFirst()) { //Check if the book has been found on the database
                            //Get the book id from the database
                            int bookId = Integer.parseInt(bookFound.getString(0));
                            //Create an order
                            String sellerId = sharedPreferences.getString("userId", "-1");
                            databaseHelper.insertListingRecord(bookId, orderDesc, "Sell", Integer.parseInt(sellerId), price, "No");
                            Toast.makeText(SellShareActivity.this, "Listing Posted.", Toast.LENGTH_LONG).show();
                        }
                        //If the book has not been found on the record.
                        else {
                            //insert the book into the database, and get it's auto-generated id
                            long inserted = databaseHelper.insertBookRecord(bookName, null, null);

                            if (inserted == -1) { //Set an error message when it is not inserted successfully
                                Toast.makeText(SellShareActivity.this, "Insert book error, please contact the admin", Toast.LENGTH_LONG).show();

                            } else { //Create an order
                                String sellerId = sharedPreferences.getString("userId", "-1");
                                databaseHelper.insertListingRecord((int)inserted, orderDesc, "Sell", Integer.parseInt(sellerId), price, "No");
                                Toast.makeText(SellShareActivity.this, "Listing Posted.", Toast.LENGTH_LONG).show();
                            }

                        }

                    }


                } else {
                    if(TextUtils.isEmpty(edTxtName.getText().toString())) {
                        Toast.makeText(SellShareActivity.this, "Please fill in all the required information.", Toast.LENGTH_LONG).show();
                        edTxtName.setError("Please enter the information for name.");
                    } else {
                        String bookName = edTxtName.getText().toString();
                        Cursor bookFound = databaseHelper.findBookName(bookName);
                        String orderDesc = edTxtDetail.getText().toString();

                        if (bookFound != null && bookFound.moveToFirst()) {
                            int bookId = Integer.parseInt(bookFound.getString(0));
                            String sellerId = sharedPreferences.getString("userId", "-1");
                            databaseHelper.insertListingRecord(bookId, orderDesc, "Share", Integer.parseInt(sellerId), 0, "No");
                            Toast.makeText(SellShareActivity.this, "Listing Posted.", Toast.LENGTH_LONG).show();
                        } else {
                            long inserted = databaseHelper.insertBookRecord(bookName, null, null);

                            if (inserted == -1) {
                                Toast.makeText(SellShareActivity.this, "Insert book error, please contact the admin", Toast.LENGTH_LONG).show();
                            } else {
                                String sellerId = sharedPreferences.getString("userId", "-1");
                                databaseHelper.insertListingRecord((int)inserted, orderDesc, "Share", Integer.parseInt(sellerId), 0, "No");
                                Toast.makeText(SellShareActivity.this, "Listing Posted.", Toast.LENGTH_LONG).show();
                            }

                        }

                    }
                }


            }
        });



    }
}