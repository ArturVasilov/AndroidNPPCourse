package ru.guar7387.practicelesson2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private static final String PREFS_NAME = "prefs";
    private static final String NUMBER_KEY = "number";

    private int mNumber;

    private TextView mCountTextView;

    private NumbersStack mStack;

    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountTextView = (TextView) findViewById(R.id.count);

        mStack = new NumbersStackImplementation();
        mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        initButtons();

        initNumber();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER_KEY, mNumber);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mNumber == 0) {
            mNumber = savedInstanceState.getInt(NUMBER_KEY, 0);
        }
        updateCountTextView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPrefs.edit().putInt(NUMBER_KEY, mNumber).apply();
    }

    private void initButtons() {
        findViewById(R.id.add1Button).setOnClickListener(this);
        findViewById(R.id.add2Button).setOnClickListener(this);
        findViewById(R.id.add5Button).setOnClickListener(this);
        findViewById(R.id.add10Button).setOnClickListener(this);
        findViewById(R.id.add50Button).setOnClickListener(this);
        findViewById(R.id.add100Button).setOnClickListener(this);

        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.backButton).setOnClickListener(this);
    }

    private void initNumber() {
        mNumber = mPrefs.getInt(NUMBER_KEY, 0);
        updateCountTextView();
    }

    private void updateCountTextView() {
        String text = getString(R.string.count);
        text += mNumber;
        mCountTextView.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backButton) {
            if (!mStack.isEmpty()) {
                mNumber -= mStack.get();
                updateCountTextView();
                return;
            }
        }

        if (v.getId() == R.id.clear) {
            mNumber = 0;
            mStack.clear();
            mPrefs.edit().clear().apply();
            updateCountTextView();
        }

        int number = 0;
        switch (v.getId()) {
            case R.id.add1Button:
                number = 1;
                break;

            case R.id.add2Button:
                number = 2;
                break;

            case R.id.add5Button:
                number = 5;
                break;

            case R.id.add10Button:
                number = 10;
                break;

            case R.id.add50Button:
                number = 50;
                break;

            case R.id.add100Button:
                number = 100;
                break;
        }
        mNumber += number;
        mStack.add(number);
        updateCountTextView();
    }
}
