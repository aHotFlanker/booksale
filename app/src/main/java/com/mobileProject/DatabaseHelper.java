package com.mobileProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    final  static String DB_NAME = "BOOK.db";
    final static int DATABASE_VERSION = 3;
    final static String TABLE1_NAME = "UserTable";
    final static String TABLE2_NAME = "UserAuthTable";
    final static String TABLE3_NAME = "BookTable";
    final static String TABLE4_NAME = "OrderTable";
    final static String TABLE5_NAME = "ListingTable";

    // Table 1 User
    final static String T1COL1 = "UserId";
    final static String T1COL2 = "FName";
    final static String T1COL3 = "LName";
    final static String T1COL4 = "Email";
    final static String T1COL5 = "PNUmber";
    final static String T1COL6 = "Address";

    // Table 2 UserAuth
    final static String T2COL1 = "UserId";
    final static String T2COL2 = "Email";
    final static String T2COL3 = "Password";

    // Table 3 Book
    final static String T3COL1 = "BookId";
    final static String T3COL2 = "BName";
    final static String T3COL3 = "Writer";
    final static String T3COL4 = "Genre";

    // Table 4 Order
    final static String T4COL1 = "OrderId";
    final static String T4COL2 = "ListingId";
    final static String T4COL3 = "BuyerId";
    final static String T4COL4 = "DeliveryOption";
    final static String T4COL5 = "Completed";
    final static String T4COL6 = "DeliveryStatus";

    // Table 5 Listing
    final static String T5COL1 = "ListingId";
    final static String T5COL2 = "BookId";
    final static String T5COL3 = "Description";
    final static String T5COL4 = "Intent";
    final static String T5COL5 = "ListingDate";
    final static String T5COL6 = "SellerId";
    final static String T5COL7 = "Price";
    final static String T5COL8 = "IsOrdered";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
        SQLiteDatabase database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE " + TABLE1_NAME
                + "(" + T1COL1 + " INTEGER PRIMARY KEY,"
                + T1COL2 + " TEXT, "
                + T1COL3 + " TEXT,"
                + T1COL4 + " TEXT,"
                + T1COL5 + " TEXT,"
                + T1COL6 + " TEXT)";
        db.execSQL(query);

        query ="CREATE TABLE " + TABLE2_NAME
                + "(" + T2COL1 + " INTEGER PRIMARY KEY,"
                + T2COL2 + " TEXT, "
                + T2COL3 + " TEXT)";
        db.execSQL(query);

        query ="CREATE TABLE " + TABLE3_NAME
                + "(" + T3COL1 + " INTEGER PRIMARY KEY,"
                + T3COL2 + " TEXT, "
                + T3COL3 + " TEXT,"
                + T3COL4 + " TEXT)";
        db.execSQL(query);

        query ="CREATE TABLE " + TABLE4_NAME
                + "(" + T4COL1 + " INTEGER PRIMARY KEY,"
                + T4COL2 + " INTEGER, "
                + T4COL3 + " INTEGER,"
                + T4COL4 + " TEXT,"
                + T4COL5 + " TEXT,"
                + T4COL6 + " TEXT)";
        db.execSQL(query);
        query ="CREATE TABLE " + TABLE5_NAME
                + "(" + T5COL1 + " INTEGER PRIMARY KEY,"
                + T5COL2 + " INTEGER, "
                + T5COL3 + " TEXT,"
                + T5COL4 + " TEXT,"
                + T5COL5 + " TEXT,"
                + T5COL6 + " INTEGER,"
                + T5COL7 + " INTEGER,"
                + T5COL8 + " TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE4_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE5_NAME);
        onCreate(db);
    }

    public String getPassword(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String password = "";
        String query = "SELECT " + T2COL3 + " FROM " + TABLE2_NAME + " WHERE " + T2COL2 + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if(cursor.moveToFirst()){
            try {
                int passwordIndex = cursor.getColumnIndexOrThrow(T2COL3);
                password = cursor.getString(passwordIndex);
            } catch (IllegalArgumentException e) {
                // Handle the case where the column does not exist
                e.printStackTrace();
            }
        }
        cursor.close();
        db.close();
        return password;
    }
    public String getUserId(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        String userId = null;
        String query = "SELECT " + T2COL1 + " FROM " + TABLE2_NAME + " WHERE " + T2COL2 + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        if(cursor.moveToFirst()){
            try {
                int IdIndex = cursor.getColumnIndexOrThrow(T2COL1);
                userId = cursor.getString(IdIndex);
            } catch (IllegalArgumentException e) {
                // Handle the case where the column does not exist
                System.out.println("Could not retrieve id, something went wrong");
                e.printStackTrace();

            }
        }
        cursor.close();
        db.close();
        return userId;
    }


    public boolean CreateUser(String Email, String FName, String LName, String PNumber, String Address, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2, FName);
        values.put(T1COL3, LName);
        values.put(T1COL4, Email);
        values.put(T1COL5, PNumber);
        values.put(T1COL6, Address);

        long result = db.insert(TABLE1_NAME, null, values);

        ContentValues authValues = new ContentValues();
        authValues.put(T2COL2, Email);
        authValues.put(T2COL3, password);
        long result2 = db.insert(TABLE2_NAME, null, authValues);

        db.close();

        // Check if the insertion was successful
        return result > 0 && result2 > 0;
    }

    public boolean EditUser(int userId, String FName, String LName, String PNumber, String Address){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL2, FName);
        values.put(T1COL3, LName);
        values.put(T1COL5, PNumber);
        values.put(T1COL6, Address);

        // Update the user information based on userId
        int result = db.update(TABLE1_NAME, values, T1COL1 + " = ?", new String[]{String.valueOf(userId)});

        db.close();

        // Check if the update was successful
        return result > 0;
    }

    public UserDetails GetUserDetails(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        UserDetails userDetails = null;

        try {
            // Define the columns to be retrieved
            String[] columns = {T1COL4, T1COL2, T1COL3, T1COL5, T1COL6};

            // Query the user information based on userId
            Cursor cursor = db.query(TABLE1_NAME, columns, T1COL1 + " = ?", new String[]{userId}, null, null, null);

            if (cursor.moveToFirst()) {
                // Retrieve user details from the cursor
                String email = cursor.getString(cursor.getColumnIndexOrThrow(T1COL4));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(T1COL2));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(T1COL3));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(T1COL5));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(T1COL6));

                // Create a UserDetails object
                userDetails = new UserDetails(email, firstName, lastName, phoneNumber, address);
            }

            cursor.close();
        } catch (Exception e) {
            // Handle any exceptions that occur during the query
            Log.e("GetUserDetails", "Error retrieving user details: " + e.getMessage());
        } finally {
            db.close();
        }

        return userDetails;
    }
    public boolean UpdateUserDetails(String userId, String firstName, String lastName, String phoneNumber, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean success = false;

        try {
            ContentValues values = new ContentValues();
            values.put(T1COL2, firstName);
            values.put(T1COL3, lastName);
            values.put(T1COL5, phoneNumber);
            values.put(T1COL6, address);

            // Update the user details based on userId
            int rowsAffected = db.update(TABLE1_NAME, values, T1COL1 + " = ?", new String[]{userId});

            // Check if the update was successful
            success = rowsAffected > 0;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error updating user details: " + e.getMessage());
        } finally {
            db.close();
        }

        return success;
    }
    public List<OrderHistory> getOrderHistory(String userId) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            // Join the necessary tables to retrieve the required information
            String query = "SELECT " +
                    "lt." + T5COL5 + " AS ListingDate, " +
                    "lt." + T5COL7 + " AS Price, " +
                    "CASE " +
                    "WHEN ot." + T4COL3 + " = ? THEN 'Bought' " +
                    "WHEN lt." + T5COL6 + " = ? THEN 'Sold' " +
                    "ELSE 'Unknown' " +
                    "END AS Status, " +
                    "bt." + T3COL2 + " AS BookName " +
                    "FROM " + TABLE4_NAME + " ot " +
                    "JOIN " + TABLE5_NAME + " lt ON ot." + T4COL2 + " = lt." + T5COL1 + " " +
                    "JOIN " + TABLE3_NAME + " bt ON lt." + T5COL2 + " = bt." + T3COL1 + " " +
                    "WHERE (ot." + T4COL3 + " = ? OR lt." + T5COL6 + " = ?) " +
                    "AND ot." + T4COL5 + " = 'true'";

            Cursor cursor = db.rawQuery(query, new String[]{userId, userId, userId, userId});
            if (cursor.moveToFirst()) {
                do {
                    String listingDate = cursor.getString(cursor.getColumnIndexOrThrow("ListingDate"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("Price"));
                    String status = cursor.getString(cursor.getColumnIndexOrThrow("Status"));
                    String bookName = cursor.getString(cursor.getColumnIndexOrThrow("BookName"));

                    // Create an OrderHistory object and add it to the list
                    OrderHistory orderHistory = new OrderHistory(listingDate, price, status, bookName);
                    orderHistoryList.add(orderHistory);
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error retrieving order history: " + e.getMessage());
        } finally {
            db.close();
        }

        return orderHistoryList;
    }

    public Cursor listListingItems(String searchBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        /*
        ContentValues values4 = new ContentValues();
        values4.put(T3COL1, 3); //BookID
        values4.put(T3COL2, "Crazy BookA"); //Name
        values4.put(T3COL3, "Daniel"); //writer
        values4.put(T3COL4, "Adventure"); //genre
        long l4 =  db.insert(TABLE3_NAME,null, values4);
        ContentValues values = new ContentValues();
        values.put(T5COL2, 3); //BookID
        values.put(T5COL3, "100% NEW!"); //Description
        values.put(T5COL4, "sell"); //Intent
        values.put(T5COL5, "3/28/2024"); //ListingDate
        values.put(T5COL6, 1); //sellerID
        values.put(T5COL7, 99.1); //Price
        values.put(T5COL8, false); //isOrdered
        long l =  db.insert(TABLE5_NAME,null, values);*/
        /*
        ContentValues values2 = new ContentValues();
        values2.put(T5COL2, 2); //BookID
        values2.put(T5COL3, "test1"); //Description
        values2.put(T5COL4, "sell"); //Intent
        values2.put(T5COL5, "3/28/2024"); //ListingDate
        values2.put(T5COL6, 1); //sellerID
        values2.put(T5COL7, 10.1); //Price
        values2.put(T5COL8, false); //isOrdered
        long l2 =  db.insert(TABLE5_NAME,null, values2);
        ContentValues values3 = new ContentValues();
        values3.put(T3COL1, 1); //BookID
        values3.put(T3COL2, "Harry"); //Name
        values3.put(T3COL3, "WriterA"); //writer
        values3.put(T3COL4, "GenreA"); //genre
        long l3 =  db.insert(TABLE3_NAME,null, values3);
        ContentValues values4 = new ContentValues();
        values4.put(T3COL1, 2); //BookID
        values4.put(T3COL2, "Potter"); //Name
        values4.put(T3COL3, "WriterB"); //writer
        values4.put(T3COL4, "GenreB"); //genre
        long l4 =  db.insert(TABLE3_NAME,null, values4);
*/
        String query;
        if( searchBy.equals("")) {
            query = "SELECT BNAME, fName, PRICE, DESCRIPTION, listingId, sellerID  FROM "
                    + TABLE5_NAME
                    + " INNER JOIN " + TABLE3_NAME
                    + " ON " + TABLE5_NAME + ".BookId = " + TABLE3_NAME + ".BookId"
                    + " INNER JOIN " + TABLE1_NAME
                    + " ON " + TABLE5_NAME + ".SellerID = " + TABLE1_NAME + ".UserId";
        }else{
            query = "SELECT BNAME, fName, PRICE, DESCRIPTION, listingId, sellerID  FROM "
                    + TABLE5_NAME
                    + " INNER JOIN " + TABLE3_NAME
                    + " ON " + TABLE5_NAME + ".BookId = " + TABLE3_NAME + ".BookId"
                    + " INNER JOIN " + TABLE1_NAME
                    + " ON " + TABLE5_NAME + ".SellerID = " + TABLE1_NAME + ".UserId"
                    + " WHERE BNAME = '" + searchBy +"'";

        }
        Cursor c = db.rawQuery(query, null);
        return c;

    }

    public boolean insertOrderRecord(int listingID, int buyerID, String deliveryOption, boolean completed, String deliveryStatus) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(T4COL2, listingID); //Listing ID
        values.put(T4COL3, buyerID); //Buyer ID
        values.put(T4COL4, deliveryOption); //DeliveryOption
        values.put(T4COL5, false); //Completed
        values.put(T4COL6, deliveryStatus); //DeliveryStatus
        long l =  sqLiteDatabase.insert(TABLE4_NAME,null, values);
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }
    public String getUserAddress(int userID){
        String userAddress = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + T1COL6 + " FROM " + TABLE1_NAME + " WHERE " + T1COL1 + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userID)});
        if(cursor.moveToFirst()){
            userAddress= cursor.getString(0);
        }
        cursor.close();
        db.close();
        return userAddress;
    }

}

