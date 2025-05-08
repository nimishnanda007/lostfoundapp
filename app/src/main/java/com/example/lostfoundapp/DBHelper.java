package com.example.lostfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LostFoundDB.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Items";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";
    public static final String COL_DESC = "description";
    public static final String COL_DATE = "date";
    public static final String COL_LOCATION = "location";
    public static final String COL_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT, " +
            COL_PHONE + " TEXT, " +
            COL_DESC + " TEXT, " +
            COL_DATE + " TEXT, " +
            COL_LOCATION + " TEXT, " +
            COL_STATUS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertItem(String name, String phone, String desc, String date, String location, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_PHONE, phone);
        values.put(COL_DESC, desc);
        values.put(COL_DATE, date);
        values.put(COL_LOCATION, location);
        values.put(COL_STATUS, status);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getItemById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public boolean deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }
}
