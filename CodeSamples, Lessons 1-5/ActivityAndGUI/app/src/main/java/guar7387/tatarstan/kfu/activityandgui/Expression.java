package guar7387.tatarstan.kfu.activityandgui;

import android.util.Log;

/**
 * Created by Artur on 10.08.2014.
 */
public class Expression {

    private static final String TAG = "CALCULATOR_EXPRESSION_CLASS";

    private String mExpression;

    private OnExpressionCalculated onExpressionCalculated;

    private int firstNumber;

    private char sign;

    private int secondNumber;

    private int currentIndex;

    private char currentSymbol;

    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTIPLY = '*';
    private static final char DIVISION = '/';

    public Expression(String expression, OnExpressionCalculated onExpressionCalculated) {
        this.mExpression = expression;
        this.onExpressionCalculated = onExpressionCalculated;
        if (this.onExpressionCalculated == null) {
            throw new IllegalArgumentException("onExpressionCalculated shouldn't be null!");
        }
    }

    public void calculate() {
        firstNumber = 0;
        secondNumber = 0;
        currentIndex = 0;
        /*
         * calculating expressions value to result variable
         * and call onExpressionCalculated.success(result)
         * if there are any errors call onExpressionCalculated.fail(errorMessage)
         */
        int length = mExpression.length();
        if (length < 3) {
            onExpressionCalculated.fail("Выражение должно соответствовать шаблону <число><знак><число>");
            Log.i(TAG, "expression is too short");
            return;
        }

        currentSymbol = mExpression.charAt(currentIndex);
        if (!isNumber(currentSymbol)) {
            onExpressionCalculated.fail("Число должно начинаться с цифры");
            Log.i(TAG, "first char isn't number");
            return;
        }

        if (!getFirstNumber(length)) return;

        if (!getSign()) return;

        if (!getSecondNumber(length)) return;

        onExpressionCalculated.success(createExpression());
    }

    private boolean getFirstNumber(int length) {
        firstNumber = getNumberFromChar(currentSymbol);
        currentIndex = 1;
        currentSymbol = mExpression.charAt(currentIndex);
        while (isNumber(currentSymbol)) {
            currentIndex++;
            if (currentIndex >= length) {
                onExpressionCalculated.fail("Выражение должно соответствовать шаблону <число><знак><число>");
                Log.i(TAG, "expression have no sign or second number");
                return false;
            }
            firstNumber *= 10;
            firstNumber += getNumberFromChar(currentSymbol);
            currentSymbol = mExpression.charAt(currentIndex);
        }
        Log.i(TAG, "first number - " + firstNumber);
        return true;
    }

    private boolean getSign() {
        if (!isSign(currentSymbol)) {
            onExpressionCalculated.fail("В строке не должно быть символов кроме цифр и знаков");
            Log.i(TAG, "unexpected sign");
            return false;
        }
        else {
            sign = currentSymbol;
            currentIndex++;
            Log.i(TAG, "sign - " + sign);
        }
        return true;
    }

    private boolean getSecondNumber(int length) {
        if (currentIndex >= length) {
            onExpressionCalculated.fail("Выражение должно соответствовать шаблону <число><знак><число>");
            Log.i(TAG, "expression have no second number");
            return false;
        }
        currentSymbol = mExpression.charAt(currentIndex);
        while (isNumber(currentSymbol)) {
            currentIndex++;
            secondNumber *= 10;
            secondNumber += getNumberFromChar(currentSymbol);
            if (currentIndex >= length) {
                break;
            }
            currentSymbol = mExpression.charAt(currentIndex);
        }
        if (currentIndex < length) {
            onExpressionCalculated.fail("Выражение содержит посторонние символы");
            Log.i(TAG, "some unexpected symbols after second number");
            return false;
        }
        Log.i(TAG, "second number - " + secondNumber);
        return true;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isSign(char c) {
        return c == PLUS || c == MINUS || c == MULTIPLY || c == DIVISION;
    }

    private int getNumberFromChar(char c) {
        return c - '0';
    }

    private int createExpression() {
        switch (sign) {
            case PLUS:
                return firstNumber + secondNumber;

            case MINUS:
                return firstNumber - secondNumber;

            case MULTIPLY:
                return firstNumber * secondNumber;

            case DIVISION:
                return firstNumber / secondNumber;

            default:
                return firstNumber + secondNumber;
        }
    }
}


