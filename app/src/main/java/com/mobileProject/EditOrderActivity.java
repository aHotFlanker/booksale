package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EditOrderActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        ListView lstView = findViewById(R.id.listViewItems);

        ArrayList<OrderInformation> orders = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        //putting the query results into the arraylist
        Cursor c = databaseHelper.viewOrderData(Integer.parseInt(sharedPreferences.getString("userId", "-1")));
        while (c.moveToNext()) {
            orders.add(new OrderInformation(R.drawable.book,
                    c.getString(0),
                    c.getString(1),
                    c.getString(2),
                    c.getDouble(3),
                    c.getString(5),
                    c.getString(4),
                    c.getInt(6),
                    c.getInt(7),
                    c.getInt(8)));
        }

        ListAdapter adapter = new CustomAdapterThanh(this, orders);
        lstView.setAdapter(adapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EditOrderActivity.this, EditViewOrdersActivity.class);
                OrderInformation order = orders.get(position);
                String bookName = order.getBookName();
                String seller = order.getSellerLastName();
                double price = order.getPrice();
                String desc = order.getDesc();
                String delivery = order.getDelivery();
                int orderID = order.getOrderId();
                int sellerId = order.getSellerId();
                int userId = order.getUserId();
                intent.putExtra("BookName", bookName);
                intent.putExtra("Name", seller);
                intent.putExtra("price", price);
                intent.putExtra("desc", desc);
                intent.putExtra("delivery", delivery);
                intent.putExtra("orderID", orderID);
                intent.putExtra("sellerId", sellerId);
                intent.putExtra("buyerId", userId);



                startActivity(intent);
            }
        });






    }
}