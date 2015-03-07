package ru.guar7387.customviewlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonView extends LinearLayout {

    private Context context;

    private ImageView icon;
    private TextView name;
    private TextView email;

    public PersonView(Context context) {
        super(context);
        this.context = context;
    }

    public PersonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PersonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        icon = (ImageView) findViewById(R.id.icon);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
    }

    public void loadPerson(Person person) {
        Drawable drawable = context.getResources().getDrawable(person.getDrawableId());
        if (drawable != null) {
            icon.setImageDrawable(drawable);
            icon.setVisibility(View.VISIBLE);
        }
        else {
            icon.setVisibility(View.GONE);
        }

        String name = person.getName();
        if (name != null && !name.isEmpty()) {
            this.name.setText(name);
            this.name.setVisibility(View.VISIBLE);
        }
        else {
            this.name.setVisibility(View.GONE);
        }

        String email = person.getEmail();
        if (email != null && !email.isEmpty()) {
            this.email.setText(email);
            this.email.setVisibility(View.VISIBLE);
        }
        else {
            this.email.setVisibility(View.GONE);
        }
    }

}
