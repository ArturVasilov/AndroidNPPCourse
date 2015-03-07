package com.example.root.nppsimplesqliteexample.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.root.nppsimplesqliteexample.Timetable;

import java.util.List;

public class TimetableDAO {
    private SQLiteDatabase db;
    public TimetableDAO(Context context){
        SQLiteOpenHelper helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    public Timetable getRow(long id){
        Cursor cursor = db.rawQuery("SELECT * FROM " + SqlNamesHolder.TABLE_TIMETABLE +
                    " WHERE " + BaseColumns._ID + " = '" + id + "'", null);
//        cursor.moveToFirst();
//        while (cursor.moveToNext())
        cursor.moveToFirst();
        Timetable timetable = new Timetable(
                id,
                Subject.fromString(cursor.getString(cursor.getColumnIndex(SqlNamesHolder.COLUMN_SUBJECT))),
                cursor.getLong(2),
                cursor.getString(cursor.getColumnIndex(SqlNamesHolder.COLUMN_LECTURER)));
        return timetable;
    }

    public Long insert(long _id, Subject subject, long timeMillisec, String lecturer){
        ContentValues cv = new ContentValues();
        cv.put(BaseColumns._ID, _id);
        cv.put(SqlNamesHolder.COLUMN_SUBJECT, subject.getValue());
        cv.put(SqlNamesHolder.COLUMN_TIME, timeMillisec);
        cv.put(SqlNamesHolder.COLUMN_LECTURER, lecturer);
        return db.insert(SqlNamesHolder.TABLE_TIMETABLE, null, cv);
    }

    public int update(long _id, Subject subject, long timeMillisec, String lecturer){
        ContentValues cv = new ContentValues();
        cv.put(BaseColumns._ID, _id);
        cv.put(SqlNamesHolder.COLUMN_SUBJECT, subject.getValue());
        cv.put(SqlNamesHolder.COLUMN_TIME, timeMillisec);
        cv.put(SqlNamesHolder.COLUMN_LECTURER, lecturer);
        return db.update(SqlNamesHolder.TABLE_TIMETABLE, cv, "_id = " + _id, null);
    }

    public void delete(long id){
        db.delete(SqlNamesHolder.TABLE_TIMETABLE, "_id = " + id, null);
    }

    public Cursor getAll(){
        return db.rawQuery("SELECT * FROM " + SqlNamesHolder.TABLE_TIMETABLE + " " +
                "ORDER BY " + SqlNamesHolder.COLUMN_TIME, new String[] {});
    }

    // ?
    public void close() {
        db.close();
    }

    public List<Timetable> getList() {

        return null;
    }
}
