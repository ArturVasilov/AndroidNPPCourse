package ru.guar7387.listview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter
        extends ArrayAdapter<Person> {

    private Activity activity;

    private List<Person> persons;

    public PersonAdapter(Activity activity,
                         int resource,
                         List<Person> persons) {
        super(activity, resource, persons);
        this.activity = activity;
        this.persons = persons;
    }

    public void remove(int index) {
        persons.remove(index);
    }

    private class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView email;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            item = inflater.inflate(R.layout.person_item, null, true);
            holder = new ViewHolder();
            holder.image = (ImageView) item.findViewById(R.id.icon);
            holder.name = (TextView) item.findViewById(R.id.name);
            holder.email = (TextView) item.findViewById(R.id.email);
            item.setTag(holder);
        } else {
            holder = (ViewHolder) item.getTag();
        }
        Person person = persons.get(position);
        holder.image.setImageResource(person.getDrawableId());
        holder.name.setText(person.getName());
        holder.email.setText(person.getEmail());
        return item;
    }
}







