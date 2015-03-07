package com.example.root.nppsimplesqliteexample;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.root.nppsimplesqliteexample.dao.SqlNamesHolder;
import com.example.root.nppsimplesqliteexample.dao.Subject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimetableCursorAdapter extends CursorAdapter {
    public TimetableCursorAdapter(Context context, Cursor c) {
        super(context, c);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private LayoutInflater inflater;

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item, parent, false);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("EEE, HH:mm");
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvId = (TextView) view.findViewById(R.id.timetable_id);
        TextView tvSubject = (TextView) view.findViewById(R.id.subject);
        TextView tvLecturer = (TextView) view.findViewById(R.id.lecturer);
        TextView tvTime = (TextView) view.findViewById(R.id.time);

        long id = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID));
        tvId.setText("" + id);

        tvLecturer.setText(cursor.getString(cursor.getColumnIndex(SqlNamesHolder.COLUMN_LECTURER)));

        Date date = new Date(cursor.getLong(cursor.getColumnIndex(SqlNamesHolder.COLUMN_TIME)));
        String formatedTime = sdf.format(date);

        tvTime.setText(formatedTime);

        Subject subject = Subject.fromString(cursor.getString(cursor.getColumnIndex(SqlNamesHolder.COLUMN_SUBJECT)));
        String enumValue = subject.getValue();
        tvSubject.setText(enumValue);
    }
}
