package ru.guar7387.todosample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import ru.guar7387.todosample.TodoItem;

/**
 * simple implementation of {@link DatabaseProvider}
 * with add and remove operations;
 * uses {@link TodoDatabaseHelper} to get required sqlite database
 */
public class DatabaseDefaultProvider implements DatabaseConstants, DatabaseProvider {

    private final SQLiteDatabase sqLiteDatabase;

    private final List<TodoItem> todoItems = new ArrayList<>();

    public DatabaseDefaultProvider(Context context) {
        sqLiteDatabase = new TodoDatabaseHelper(context).getWritableDatabase();
    }

    @Override
    public void close() {
        if (sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }

    @Override
    public void addTodoItem(TodoItem todoItem) {
        todoItems.add(todoItem);
        ContentValues values = new ContentValues();
        values.put(TODO_TEXT, todoItem.getTask());
        values.put(TODO_TIME, todoItem.getTime());
        //if row with same id is exist, it will be replaced
        sqLiteDatabase.insertWithOnConflict(TODO_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public List<TodoItem> getAll() {
        //to not open database every time we want to read values, we store current todoItems in list
        if (!todoItems.isEmpty()) {
            return todoItems;
        }
        //select all rows from database, go through when in cycle and add to list
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TODO_TABLE_NAME, new String[]{});
        while (cursor.moveToNext()) {
            TodoItem todoItem = new TodoItem(cursor.getString(1), cursor.getLong(2));
            todoItem.setId(cursor.getInt(0));
            todoItems.add(todoItem);
        }

        return todoItems;
    }

    @Override
    public void removeItem(TodoItem todoItem) {
        todoItems.remove(todoItem);
        sqLiteDatabase.delete(TODO_TABLE_NAME, BaseColumns._ID + " = " + todoItem.getId(), null);
    }

}
