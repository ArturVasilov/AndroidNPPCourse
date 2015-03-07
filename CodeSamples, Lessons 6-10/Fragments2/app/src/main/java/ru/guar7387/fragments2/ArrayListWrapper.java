package ru.guar7387.fragments2;

import java.util.ArrayList;

/**
 * Wrapper for ArrayList. It's just the same ArrayList, but after
 * add and remove methods it prints it's content.
 * Just for example, of course there are can be a lot of
 * more useful additions for ArrayList or other class in general.
 */
public class ArrayListWrapper<T> extends ArrayList<T> {

    private static final String TAG = "ArrayListWrapper";

    @Override
    public boolean add(T object) {
        boolean result = super.add(object);
        Logger.i(TAG, toString());
        return result;
    }

    @Override
    public T remove(int index) {
        T t = super.remove(index);
        Logger.i(TAG, toString());
        return t;
    }
}
