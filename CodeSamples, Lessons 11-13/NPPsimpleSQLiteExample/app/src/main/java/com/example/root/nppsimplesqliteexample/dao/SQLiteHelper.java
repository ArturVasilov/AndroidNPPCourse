package com.example.root.nppsimplesqliteexample.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by root on 25.11.14.
 */
public class SQLiteHelper extends SQLiteOpenHelper implements BaseColumns{

//    SqlNamesHolder names = SqlNamesHolder.getInstance();
    public SQLiteHelper(Context context) {
        super(context, SqlNamesHolder.TABLE_TIMETABLE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SqlNamesHolder.TABLE_TIMETABLE + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SqlNamesHolder.COLUMN_SUBJECT + " STRING NOT NULL, " +
            SqlNamesHolder.COLUMN_TIME + " LONG NOT NULL, " +
            SqlNamesHolder.COLUMN_LECTURER + " TEXT);");
        //triggers, migration
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SqlNamesHolder.TABLE_TIMETABLE);
        onCreate(db);
    }

}
