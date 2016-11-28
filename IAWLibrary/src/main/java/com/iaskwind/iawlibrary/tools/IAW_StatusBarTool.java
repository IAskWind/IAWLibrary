package com.iaskwind.iawlibrary.tools;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.List;

/**
 * Created by winston on 16/11/28.
 * 状态栏工具类
 */
public class IAW_StatusBarTool {

    /**
     * 设置状态栏颜色，有时候需要一些activity顶部设置通明，比如启动activity
     * 此方法一般用在BaseActivity里面，设置全局状态栏颜色(沉浸式)
     * @param activity 上下文环境
     * @param transparentActivityList 需要通明的状态栏的activity列表
     * @param statusBarColor
     */
    public static void setStatusBarView(Activity activity, List<Class> transparentActivityList,int statusBarColor){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true,activity);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        if (transparentActivityList.contains(activity.getClass())) {
            tintManager.setStatusBarTintResource(Color.TRANSPARENT);
        }else{
            tintManager.setStatusBarTintResource(statusBarColor);
        }


//        tintManager.setNavigationBarTintEnabled(true);
//        tintManager.setTintColor(R.color.skin_colorAccent);
//        setStatusBarView(this);
//        SkinUtils.registerSkin(this);
    }
    @TargetApi(19)
    private static void setTranslucentStatus(boolean on,Activity mContext) {
        Window win = mContext.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
