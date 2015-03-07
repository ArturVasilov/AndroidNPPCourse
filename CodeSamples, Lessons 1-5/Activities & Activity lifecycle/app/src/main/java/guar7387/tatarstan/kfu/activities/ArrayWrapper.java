package guar7387.tatarstan.kfu.activities;

import java.io.Serializable;

/**
 * Created by Artur on 22.08.2014.
 */
public class ArrayWrapper implements Serializable {

    public static final String ARRAY_WRAPPER_KEY = "haha";

    private String text;

    private int[] array;

    private int sum = 0;

    public ArrayWrapper(String text, int[] array) {
        this.text = text;
        this.array = array;
    }

    public void calculateSum() {
        sum = 0;
        for (int i : array) {
            sum += i;
        }
    }

    @Override
    public String toString() {
        return text + sum;
    }
}

