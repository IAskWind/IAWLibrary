package com.iaskwind.iawlibrary.iaw;

import android.content.Context;
import android.os.Bundle;

import com.blankj.utilcode.utils.ActivityUtils;

/**
 * Created by winston on 16/11/22.
 * Activity相关工具类
 */
public class IAW_ActivityUtils extends IAW{

    /**
     * 判断是否存在Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(Context context, String packageName, String className) {
        return ActivityUtils.isActivityExists(context,packageName,className);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     */
    public static void launchActivity(Context context, String packageName, String className) {
        ActivityUtils.launchActivity(context,packageName,className);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    public static void launchActivity(Context context, String packageName, String className, Bundle bundle) {
        ActivityUtils.launchActivity(context,packageName,className,bundle);
    }

    /**
     * 获取launcher activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @return launcher activity
     */
    public static String getLauncherActivity(Context context, String packageName) {
        return null;
    }

}
