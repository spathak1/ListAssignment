package com.test.listapp.utils;

import android.util.Log;

/**
 * Created by SPathak on 13-11-2015.
 */
public class Logger {
    private static final boolean IS_DEBUG_ENABLED = true;
    private static final String TAG = "Debug_Tag";

    public static void Debug(String msg) {
        if (IS_DEBUG_ENABLED) {
            Log.d(TAG, msg);
        }
    }
}
