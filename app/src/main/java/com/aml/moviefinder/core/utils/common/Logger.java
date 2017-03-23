package com.aml.moviefinder.core.utils.common;

import android.util.Log;

import com.google.gson.Gson;

public class Logger {

    private static final boolean DEBUG = true;
    static Gson gson = new Gson();

    public static void d(String TAG, String string) {
        if (DEBUG) {
            Log.d(TAG, string);
        }
    }

    public static void v(String TAG, String string) {
        if (DEBUG) {
            Log.v(TAG, string);
        }
    }

    public static void i(String TAG, String string) {
        if (DEBUG) {
            Log.i(TAG, string);
        }
    }

    public static void e(String TAG, String string) {
        if (DEBUG) {
            Log.e(TAG, string);
        }
    }

    public static void w(String TAG, String string) {
        if (DEBUG) {
            Log.w(TAG, string);

        }
    }

    public static <T> T o(String TAG, Object object, Class<T> mClass) {
        if (DEBUG) {
            Log.v(TAG, "Printing Object : " + mClass.getSimpleName() + "\n" + gson.toJson(object, mClass));
        }
        return null;
    }

}