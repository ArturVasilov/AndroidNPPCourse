package ru.guar7387.customviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnclickableButton unclickableButton = (UnclickableButton) findViewById(R.id.unclickableButton);
        unclickableButton.setText("Aaa");
        unclickableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this won't work
                Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_LONG).show();
            }
        });

    }

}
