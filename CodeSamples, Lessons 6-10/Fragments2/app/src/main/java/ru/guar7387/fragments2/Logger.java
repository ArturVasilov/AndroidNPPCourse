package ru.guar7387.fragments2;

import android.util.Log;

/**
 * Wrapper on class Log.
 * Release version shouldn't print logs. So we use this wrapper on Log class.
 * In release time we'll just change isDebug value to false
 */
public class Logger {

    private Logger() { }

    private static boolean isDebug = true;

    public static void i(String tag, String message) {
        if (isDebug) {
            Log.i(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (isDebug) {
            Log.e(tag, message);
        }
    }

}
