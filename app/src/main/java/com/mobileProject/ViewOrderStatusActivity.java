package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewOrderStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_status);

        String[] bookStatus = new String[]{"Bookname , Delivered 02.02.2022"};
        ListView listView = findViewById(R.id.listViewBookDetails);

        ArrayList<ImgAndTxt> objList = new ArrayList<>();

        objList.add(new ImgAndTxt("\nBookname,\n\nDelivered\n02.02.2022", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDelivered\n03.03.2023", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDelivered\n04.04.2024", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDelivered\n05.05.2025", R.drawable.book));
        objList.add(new ImgAndTxt("\nBookname,\n\nDelivered\n06.06.2026", R.drawable.book));


        ListAdapter adapter = new AdapterBurak(this, objList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                }
            }
        });


    }
}