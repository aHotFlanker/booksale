package com.mobileProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Console;

public class DatabaseHelper extends SQLiteOpenHelper {
    final  static String DB_NAME = "BOOK.db";
    final static int DATABASE_VERSION = 2;
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
                + T5COL6 + " INTEGER)";
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


}

