package ru.guar7387.todosample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

import ru.guar7387.todosample.database.DatabaseDefaultProvider;
import ru.guar7387.todosample.database.DatabaseProvider;

public class MainActivity extends Activity implements OnTodoCreated, OnCreateTodo, OnTodoRemoved {

    private FragmentManager fragmentManager;

    private DatabaseProvider databaseProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        databaseProvider = new DatabaseDefaultProvider(getApplicationContext());
        openList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseProvider.close();
    }

    /**
     * calls when user has created todoItem in {@link TodoCreationFragment}
     * adds new item to database and again opens list of todos
     */
    @Override
    public void onTodoItemCreated(TodoItem todoItem) {
        onBackPressed();
        databaseProvider.addTodoItem(todoItem);
        /**
         * TODO : put your code here - show notification about this todoItem at specific time (todoItem.getTime())
         * Building notifications - http://developer.android.com/guide/topics/ui/notifiers/notifications.html
         * Using AlarmManager - https://developer.android.com/training/scheduling/alarms.html
         */
        openList();
    }

    /**
     * opens {@link TodoCreationFragment} to create a new todoItem
     */
    @Override
    public void onCreateTodo() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new TodoCreationFragment();
        transaction.addToBackStack("");
        transaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_right_out,
                R.anim.fragment_left_in, R.anim.fragment_left_out);
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    @Override
    public void onTodoRemove(TodoItem todoItem) {
        databaseProvider.removeItem(todoItem);
    }

    /**
     * Opens {@link TodoListAdapter} with all current todoTasks
     */
    private void openList() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new TodoListFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelableArrayList(TodoListFragment.KEY, (ArrayList<? extends Parcelable>) databaseProvider.getAll());
        fragment.setArguments(arguments);
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

}
