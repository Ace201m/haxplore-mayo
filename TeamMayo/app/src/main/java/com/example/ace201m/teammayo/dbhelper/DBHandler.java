package com.example.ace201m.teammayo.dbhelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";
    private static final String TABLE_NAME = "user";
    private static final String PIN = "pin";
    private static final String PHONE_NO = "phoneNo";

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + PHONE_NO +
                " VARCHAR(20) PRIMARY KEY," + PIN + " VARCHAR(14) )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<User> select(){
        String query = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle")
        Cursor rs = db.rawQuery(query,null);

        ArrayList<User> ret = new ArrayList<>();
        while (rs.moveToNext()){
            ret.add(new User(rs.getString(0),rs.getString(1)));
        }
        rs.close();
        db.close();
        return ret;
    }

    public void insert(User user){
        ContentValues values = new ContentValues();
        values.put(PHONE_NO,user.getPhoneNo());
        values.put(PIN,user.getPin());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);

        db.close();
    }
}