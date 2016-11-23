package com.iaskwind.iawlibrary.tools;

import com.iaskwind.iawlibrary.BuildConfig;

/**
 * Created by winston on 16/11/21.
 */
public class IAW_LogTool {

    private static String tag = BuildConfig.TAG;
    private static boolean debugLog = true;

    public static void setTag(String tag) {
        IAW_LogTool.tag = tag;
    }

    public static void setDebugLog(boolean log) {
        IAW_LogTool.debugLog = log;
    }

    public static void i(String msg) {
        if (debugLog)
            android.util.Log.i(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (debugLog)
            android.util.Log.i(tag, msg);
    }

    public static void d(String msg) {
        if (debugLog)
            android.util.Log.d(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (debugLog)
            android.util.Log.d(tag, msg);
    }

    public static void w(String msg) {
        if (debugLog)
            android.util.Log.w(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (debugLog)
            android.util.Log.w(tag, msg);
    }

    public static void v(String msg) {
        if (debugLog)
            android.util.Log.v(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (debugLog)
            android.util.Log.v(tag, msg);
    }

    public static void e(String msg) {
        android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }
}
