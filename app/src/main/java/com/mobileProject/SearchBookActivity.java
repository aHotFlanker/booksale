package com.mobileProject;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchBookActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    protected void listListing(String searchBy){
        databaseHelper = new DatabaseHelper(this);
        ListView listView = findViewById(R.id.listView);
        ArrayList<ImageAndText> list = new ArrayList<>();
        ListAdapter adapter = new CustomAdapter(this,list);
        list.removeAll(list);
        Cursor c = databaseHelper.listListingItems(searchBy);
        int row=0;
        List<ListResult> listResults = new ArrayList<>();
        while(c.moveToNext()){
            ListResult result = new ListResult(
                    row,
                    Integer.parseInt(c.getString(4)),
                    c.getString(0),
                    c.getString(1),
                    Integer.parseInt(c.getString(5)),
                    Double.parseDouble(c.getString(2)),
                    c.getString(3)
            );
            listResults.add(result);

            StringBuffer str = new StringBuffer();
            str.append("Name: " + c.getString(0));
            str.append("\nSeller: " + c.getString(1));
            str.append("\nPrice " + c.getString(2));
            str.append("\nDetails: " + c.getString(3));

            list.add(new ImageAndText(String.valueOf(str),R.drawable.book));
            row++;
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Toast.makeText(SearchBookActivity.this,listResults.get(position).toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SearchBookActivity.this,BuyBookActivity.class);
                //int row, int listingID, String bName, String sellerName, int sellerID, double price, String details
                intent.putExtra("listingID", listResults.get(position).getListingID());
                intent.putExtra("bName", listResults.get(position).getbName());
                intent.putExtra("sellerName", listResults.get(position).getSellerName());
                intent.putExtra("sellerID", listResults.get(position).getSellerID());
                intent.putExtra("price", listResults.get(position).getPrice());
                intent.putExtra("details", listResults.get(position).getDetails());
                startActivity(intent);

            }
        });
        Toast.makeText(SearchBookActivity.this,"Search Result Updated!",
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);
        EditText searchBy = findViewById(R.id.edt_searchBook);
        Button search= findViewById(R.id.btnSearch);
        listListing("");
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listListing(searchBy.getText().toString());
            }

        });
    }
}