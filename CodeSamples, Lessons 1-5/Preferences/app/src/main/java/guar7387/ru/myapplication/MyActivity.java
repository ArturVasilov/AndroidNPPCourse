package guar7387.ru.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.security.Key;


public class MyActivity extends Activity {

    private static final String PREFERENCES_NAME = "OurPreferences";

    private static final String COUNT_KEY = "count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        saveLogIn();
        toastTest();
    }

    /**
     * store user open app count
     */
    private void saveLogIn() {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        int count = 0;
        if (preferences.contains(COUNT_KEY)) {
            count = preferences.getInt(COUNT_KEY, 0);
        }
        count++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(COUNT_KEY, count);
        editor.apply();
    }

    private void toastTest() {
        String text = getResources().getString(R.string.app_name);
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showMessage("Settings");
                return true;
            case R.id.call:
                showMessage("Call");
                return true;
            case R.id.microphone:
                showMessage("Speak");
                return true;
            case R.id.email:
                showMessage("Send message");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMessage(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
