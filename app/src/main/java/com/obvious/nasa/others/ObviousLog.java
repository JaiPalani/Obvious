package com.obvious.nasa.others;

import android.util.Log;

/**
 * Created by Jai on 27/11/2021.
 * Location India
 */

public class ObviousLog {
    /**
     * Verbose level logcat message (only output for debug builds)
     *
     * @param tag     Tag
     * @param message Message
     */
    public static void v(String tag, String message) {
        if (ObviousBuildConfig.DEBUG) {
            Log.v(tag, message);
        }
    }

    /**
     * Debug level logcat message (only output for debug builds)
     *
     * @param tag     Tag
     * @param message Message
     */
    public static void d(String tag, String message) {
        if (ObviousBuildConfig.DEBUG) {
            Log.d(tag, message);
        }
    }

    /**
     * Error level logcat message (always output)
     *
     * @param tag     Tag
     * @param message Message
     */

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }
}
