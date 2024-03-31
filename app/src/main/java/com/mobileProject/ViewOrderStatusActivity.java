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

public class ViewOrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        SharedPreferences sharedPreferences = getApplicationContext().
                getSharedPreferences("MyPref", MODE_PRIVATE);

        String logedInUserId = sharedPreferences.getString("userId", "");


        String[] bookStatus = new String[]{"Bookname , Delivered 02.02.2022"};
        ListView listView = findViewById(R.id.listViewBookDetails);

        ArrayList<ImgAndTxt> objList = new ArrayList<>();
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        try {
            Cursor cursorSellerId = databaseHelper.getSellerId();
            int c = 0;
            while (cursorSellerId.moveToNext()) {

                if (cursorSellerId.getString(0).equals(logedInUserId)) {

                    String getBookName = (databaseHelper.getBookinfo(logedInUserId)).getString(c).toString();
                    c++;

                    objList.add(new ImgAndTxt(getBookName + ",\n " + cursorSellerId.getString(1) + ",\n" +
                            cursorSellerId.getString(2), R.drawable.book));
                }
            }


        } catch (Exception e) {
            Toast.makeText(ViewOrderStatusActivity.this, "No content to show Selling or Sharing", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        try {
            Cursor cursorBuyerId = databaseHelper.getBuyerId();
            int a = 0;
            while (cursorBuyerId.moveToNext()) {

                if (cursorBuyerId.getString(2).equals(logedInUserId)) {
                    String getBookName1 = (databaseHelper.getBookinfo1(logedInUserId).getString(a).toString());
                    a++;


                    objList.add(new ImgAndTxt(getBookName1 + ",\n " + cursorBuyerId.getString(3) + ",\n" +
                            cursorBuyerId.getString(5), R.drawable.book));
                }

            }

        } catch (Exception e) {
            Toast.makeText(ViewOrderStatusActivity.this, "No content to show for buying", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


        ListAdapter adapter = new AdapterBurak(this, objList);

        listView.setAdapter(adapter);
       
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class);
                intent.putExtra("position", position); // position added to intent for to be able to use in edit order activity
                startActivity(intent);
            }
        });

       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(ViewOrderStatusActivity.this, OrderDetailActivity.class));
                        break;
                }
            }
        });*/


    }

}