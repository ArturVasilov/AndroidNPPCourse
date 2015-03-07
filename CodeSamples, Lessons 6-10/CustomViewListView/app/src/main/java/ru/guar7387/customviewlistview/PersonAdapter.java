package ru.guar7387.customviewlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private Activity activity;

    private List<Person> persons;

    public PersonAdapter(Activity activity, int resource, List<Person> persons) {
        super(activity, resource, persons);
        this.activity = activity;
        this.persons = persons;
    }

    public void remove(int index) {
        persons.remove(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonView personView;
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            personView = (PersonView) inflater.inflate(R.layout.person_item, null, true);
        } else {
            personView = (PersonView) convertView;
        }
        Person person = persons.get(position);
        personView.loadPerson(person);
        return personView;
    }
}







