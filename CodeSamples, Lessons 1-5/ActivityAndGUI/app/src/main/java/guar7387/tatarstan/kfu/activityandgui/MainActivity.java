package guar7387.tatarstan.kfu.activityandgui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener, OnExpressionCalculated {

    private static final String TAG = "CALCULATOR_MAIN_ACTIVITY";

    private EditText mExpressionText;

    private TextView mResultText;

    private StringBuilder expression = new StringBuilder();

    private int[] mNumbersIds = new int[] {
            R.id.number0Button, R.id.number1Button,
            R.id.number2Button, R.id.number3Button,
            R.id.number4Button, R.id.number5Button,
            R.id.number6Button, R.id.number7Button,
            R.id.number8Button, R.id.number9Button,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mExpressionText = (EditText) findViewById(R.id.expressionEditText);
        mResultText = (TextView) findViewById(R.id.expressionResultTextView);

        for (int i = 0; i < mNumbersIds.length; i++) {
            findViewById(mNumbersIds[i]).setOnClickListener(this);
        }

        findViewById(R.id.plusSignButton).setOnClickListener(this);
        findViewById(R.id.minusSignButton).setOnClickListener(this);
        findViewById(R.id.multiplySignButton).setOnClickListener(this);
        findViewById(R.id.divisionSignButton).setOnClickListener(this);

        findViewById(R.id.clearLastSymbolButton).setOnClickListener(this);

        findViewById(R.id.calculateButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        for (int i = 0; i < mNumbersIds.length; i++) {
            if (id == mNumbersIds[i]) {
                Log.i(TAG, "clicked number - " + i);
                expression.append(i);
                mExpressionText.setText(expression);
                return;
            }
        }

        if (id == R.id.plusSignButton) {
            expression.append("+");
            mExpressionText.setText(expression);
            return;
        }

        if (id == R.id.minusSignButton) {
            expression.append("-");
            mExpressionText.setText(expression);
            return;
        }

        if (id == R.id.multiplySignButton) {
            expression.append("*");
            mExpressionText.setText(expression);
            return;
        }

        if (id == R.id.divisionSignButton) {
            expression.append("/");
            mExpressionText.setText(expression);
            return;
        }

        if (id == R.id.clearLastSymbolButton) {
            if (expression.length() == 0) {
                return;
            }
            expression.deleteCharAt(expression.length() - 1);
            mExpressionText.setText(expression);
            return;
        }

        if (id == R.id.calculateButton) {
            Log.i(TAG, "expression - " + expression);
            Expression expr = new Expression(expression.toString(), this);
            expr.calculate();
        }
    }

    @Override
    public void success(int result) {
        mResultText.setText(String.valueOf(result));
    }

    @Override
    public void fail(String errorMessage) {
        mResultText.setText(errorMessage);
    }
}




