package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        SharedPreferences sharedPreferences = getApplicationContext().
                getSharedPreferences("MyPref", MODE_PRIVATE);

        String logedInUserId = sharedPreferences.getString("userId", "");
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        String[] bookStatus = new String[]{"Bookname , Delivered 02.02.2022"};
        ListView listView = findViewById(R.id.listViewBookDetails);

        ArrayList<ImgAndTxt> objList = new ArrayList<>();


        try {
            Cursor cursorBuyerId = databaseHelper.getBuyerId();
            int a = 0;
            while (cursorBuyerId.moveToNext()) {

                if (cursorBuyerId.getString(2).equals(logedInUserId)) {
                    String getBookName1 = (databaseHelper.getBookinfo1(logedInUserId).getString(a).toString());
                    a++;

                    if (cursorBuyerId.getString(3).equals("PickUp")) {
                        Cursor address = databaseHelper.getSellerAddress(logedInUserId);
                        objList.add(new ImgAndTxt(getBookName1 + ",\n " + cursorBuyerId.getString(3) + ",\n " +
                                address.getString(0), R.drawable.book));

                    } else {
                        Cursor address = databaseHelper.buyerAddress(logedInUserId);
                        objList.add(new ImgAndTxt(getBookName1 + ",\n " + cursorBuyerId.getString(3) + ",\n " +
                                address.getString(0), R.drawable.book));

                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(OrderDetailActivity.this, "No content to show for buying", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        ListAdapter adapter = new AdapterBurak(this, objList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderDetailActivity.this, EditOrderActivity.class);
                intent.putExtra("position", position); // position added to intent for to be able to use in edit order activity
                startActivity(intent);
            }
        });





        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(OrderDetailActivity.this, EditOrderActivity.class));
                        break;
                }
            }
        });*/
    }
}