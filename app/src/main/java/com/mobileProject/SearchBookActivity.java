package com.mobileProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        ListView listView = findViewById(R.id.listView);
        ArrayList<ImageAndText> list = new ArrayList<>();


        ListAdapter adapter = new CustomAdapter(this,list);


        EditText searchBy = findViewById(R.id.edt_searchBook);

        Button search= findViewById(R.id.btnSearch);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                list.removeAll(list);
                if(searchBy.getText().toString().matches("A")){
                    list.add(new ImageAndText("Book A\nSeller:Alien\nPrice:CAD$10\nDetails: Good book...",R.drawable.book));
                    list.add(new ImageAndText("Book A\nSeller:Alien\nPrice:CAD$10\nDetails: Good book...",R.drawable.book));
                    listView.setAdapter(adapter);
                }else {
                    list.add(new ImageAndText("Book B\nSeller:Alien\nPrice:CAD$10\nDetails: Good book...",R.drawable.book));
                    listView.setAdapter(adapter);
                }
            }

        });
    }
}