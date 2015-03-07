package ru.guar7387.practicelesson2;

import java.util.EmptyStackException;

public class NumbersStackImplementation implements NumbersStack {

    private static final int DEFAULT_CAPACITY = 64;

    private int index;

    private int count;

    private final int[] mStack;

    public NumbersStackImplementation() {
        this(DEFAULT_CAPACITY);
    }

    public NumbersStackImplementation(int capacity) {
        if (capacity < 8) {
            throw new IllegalArgumentException("Capacity should be at least 8");
        }
        this.mStack = new int[capacity];
        index = 0;
        count = 0;
    }

    @Override
    public void add(int number) {
        mStack[index] = number;
        count++;
        if (index == mStack.length) {
            index = 0;
        }
        else {
            index++;
        }
    }

    @Override
    public int get() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        count--;
        if (index == 0) {
            index = mStack.length - 1;
        }
        else {
            index--;
        }
        return mStack[index];
    }

    @Override
    public void clear() {
        index = 0;
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}
