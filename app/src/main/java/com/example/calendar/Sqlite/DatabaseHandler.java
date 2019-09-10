package com.example.calendar.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.calendar.Model.Day;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Holiday_Database";
    private static final String TABLE_CONTACTS = "holiday_tbl";
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DATE = "date";
    private static final String KEY_DAY = "day";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // TODO:- Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACT_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TYPE + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_DAY + " TEXT,"
                + KEY_DESCRIPTION + " TEXT"
                + ")";

        db.execSQL(CREATE_CONTACT_TABLE);
    }

    // TODO:- Up Grating Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    public void addHoliday(Day day) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE,day.getStrDate());
        values.put(KEY_DAY,day.getStrDay());
        values.put(KEY_DESCRIPTION,day.getStrDesc());

        // Inset row
        db.insert(TABLE_CONTACTS,null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // TODO:- get all user in list view
    public List<Day> getAllUsers() {
        List<Day> daysList = new ArrayList<Day>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToNext()) {
            do {
                Day newDay = new Day();
                newDay.setStrDate(cursor.getString(2));
                newDay.setStrDay(cursor.getString(3));
                newDay.setStrDesc(cursor.getString(4));
                // Adding contact to list
                daysList .add(newDay);

            } while (cursor.moveToNext());
        }

        // return contact list
        return daysList ;
    }

    // TODO:- Getting users Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
