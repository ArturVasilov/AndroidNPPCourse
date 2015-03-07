package guar7387.tatarstan.kfu.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.startActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });
    }

    private static final int SECOND_ACTIVITY_REQUEST_CODE = 112;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Правильно, молодец!", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Думаешь, я могу ошибаться???", Toast.LENGTH_LONG).show();
                startNewActivity();
            }
        }
    }

    private void startNewActivity() {
        Intent intent = new Intent(this.getApplicationContext(), SecondActivity.class);
        ArrayWrapper arrayWrapper = new ArrayWrapper("Сумма всех чисел - ", new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, } );
        intent.putExtra(ArrayWrapper.ARRAY_WRAPPER_KEY, arrayWrapper);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
    }
}




