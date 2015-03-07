package ru.guar7387.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity
implements SwipeDismissListViewTouchListener.DismissCallbacks {

    private ListView listView;

    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        List<Person> persons = new ArrayList<Person>() {{
            add(new Person(
                    android.R.drawable.btn_star_big_on,
                    "Artur Vasilov", "vasilovartur@gmail.com"));
            add(new Person(
                    android.R.drawable.btn_star_big_on,
                    "Artur Vasilov", "vasilovartur@gmail.com"));
            add(new Person(
                    android.R.drawable.btn_star_big_off,
                    "Artur Vasilov", "vasilovartur@gmail.com"));
            add(new Person(
                    android.R.drawable.btn_star_big_on,
                    "Artur Vasilov", "vasilovartur@gmail.com"));
            add(new Person(
                    android.R.drawable.btn_star_big_off,
                    "Artur Vasilov", "vasilovartur@gmail.com"));
            add(new Person(
                    android.R.drawable.btn_star_big_on,
                    "Artur Vasilov", "vasilovartur@gmail.com"));
        }};
        adapter = new PersonAdapter(
                this,
                R.layout.person_item,
                persons
        );
        listView.setAdapter(adapter);


        listView.setOnTouchListener(
                new SwipeDismissListViewTouchListener(
                        listView, this)
        );
    }

    @Override
    public boolean canDismiss(int position) {
        return true;
    }

    @Override
    public void onDismiss(ListView listView,
                  int[] reverseSortedPositions) {
        for (int index : reverseSortedPositions) {
            adapter.remove(index);
        }
        adapter.notifyDataSetChanged();
    }
}