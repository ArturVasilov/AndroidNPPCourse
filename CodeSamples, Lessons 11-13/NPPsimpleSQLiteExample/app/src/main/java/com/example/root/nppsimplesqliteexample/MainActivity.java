package com.example.root.nppsimplesqliteexample;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.root.nppsimplesqliteexample.dao.SqlNamesHolder;
import com.example.root.nppsimplesqliteexample.dao.Subject;
import com.example.root.nppsimplesqliteexample.dao.TimetableDAO;


public class MainActivity extends ListActivity {

    TimetableDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dao = new TimetableDAO(this);

        insertSampleRows();

//        CursorAdapter adapter = getSimpleAdapter();
//        CursorAdapter adapter = getCustomAdapter();
        CursorAdapter adapter = getContactsAdapter();

        getListView().setAdapter(adapter);
    }

    private void insertSampleRows() {
//        Timetable timetable = new Timetable(0, Subject.DATABASES,
//                Long.valueOf("125129765972"), "ALBSDXGYKSAUXDDSA");
        dao.getAll();
        dao.insert(0, Subject.DATABASES,
                Long.valueOf("125129765972"), "ALBSDXGYKSAUXDDSA");
        dao.insert(1, Subject.MATH_LOGICS,
                Long.valueOf("125129762"), "nashdjnshakdjnhs");
        dao.insert(2, Subject.DATABASES,
                Long.valueOf("125129972"), "SecondName");
        dao.getAll();
//        dao.update(1, Subject.DATABASES,
//                Long.valueOf("125129765972"), "Egorchev");
        dao.delete(0);

        dao.getRow(2);
        dao.getList();
    }

    private CursorAdapter getCustomAdapter(){
        return new TimetableCursorAdapter(this, dao.getAll());
    }

    private CursorAdapter getSimpleAdapter() {
        Cursor cursor = dao.getAll();
        String[] columns = new String[] {
                BaseColumns._ID,
                SqlNamesHolder.COLUMN_SUBJECT,
                SqlNamesHolder.COLUMN_TIME,
                SqlNamesHolder.COLUMN_LECTURER
        };

        int[] views = new int[] {
                R.id.timetable_id,
                R.id.subject,
                R.id.time,
                R.id.lecturer
        };

        return new SimpleCursorAdapter(
                this,
                R.layout.list_item,
                cursor,
                columns,
                views,
                0
        );

    }

    public CursorAdapter getContactsAdapter() {
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
                + " COLLATE LOCALIZED"; //or COLLATE LOCALIZED ASC, both right
        Cursor phones = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                sortOrder);
        while (phones.moveToNext())
        {
            String id = phones.getString(
                    phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID));
            String name=phones.getString(
                    phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number=phones.getString(
                    phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        String[] columns = new String[]{
                ContactsContract.CommonDataKinds.Phone._ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        int[] views = new int[]{
                R.id.timetable_id,
                R.id.subject,
                R.id.lecturer
        };
        return new SimpleCursorAdapter(this, R.layout.list_item, phones, columns, views, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
