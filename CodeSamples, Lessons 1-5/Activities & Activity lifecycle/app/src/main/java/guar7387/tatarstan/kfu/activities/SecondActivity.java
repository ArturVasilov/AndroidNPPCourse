package guar7387.tatarstan.kfu.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Artur on 19.08.2014.
 */
public class SecondActivity extends Activity {

    static final String ANSWER = "answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle args = getIntent().getExtras();
        ArrayWrapper arrayWrapper = (ArrayWrapper) args.getSerializable(ArrayWrapper.ARRAY_WRAPPER_KEY);
        arrayWrapper.calculateSum();

        TextView textView = (TextView) findViewById(R.id.secondActivityText);
        textView.setText(arrayWrapper.toString());

        Button button = (Button) findViewById(R.id.okButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ANSWER, true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}

