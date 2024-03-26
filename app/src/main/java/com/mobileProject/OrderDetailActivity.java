package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        String[] bookStatus = new String[]{"Bookname , Delivered 02.02.2022"};
        ListView listView = findViewById(R.id.listViewBookDetails);

        ArrayList<ImgAndTxt> objList = new ArrayList<>();

        objList.add(new ImgAndTxt("\nBookname,\n\nDeliver profile address", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDeliver profile address", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDeliver profile address", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDeliver profile address", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDeliver profile address", R.drawable.book));

        ListAdapter adapter = new AdapterBurak(this, objList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                }
            }
        });
    }
}