package com.iaskwind.iawlibrary.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by winston on 16/11/18.
 */
public class IAW_ActivityTool {

//    private static IAW_ActivityTool instance;
//    /**
//     *
//     * 获取实例 ,单例模式
//     * @return
//     */
//    public synchronized static IAW_ActivityTool getInstance() {
//        if (instance == null) {
//            instance = new IAW_ActivityTool();
//        }
//        return instance;
//    }


    public static void startActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void startActivityByNewTask(Context context,Class activity) {
        Intent intent = new Intent(context, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

    /**
     * @param context
     * @Desc 跳转到指定的Activity 多级跳转
     */
    public static void startActivityByClearTop(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class activity, HashMap<String, Parcelable> hashMap) {
        Intent intent = new Intent(context, activity);
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Parcelable val = (Parcelable) entry.getValue();
            intent.putExtra(key, val);
        }
        context.startActivity(intent);
    }


    public static void startActivity(Context context, Class activity, String arrayListKey, ArrayList<Parcelable> parcelables) {
        Intent intent = new Intent(context, activity);
        intent.putParcelableArrayListExtra(arrayListKey, parcelables);
        context.startActivity(intent);
    }

    public static void startWebActivity(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public  static void startEmailActivity(Context context, int toResId, int subjectResId, int bodyResId) {
        startEmailActivity(context, context.getString(toResId), context.getString(subjectResId),
                context.getString(bodyResId));
    }

    public static void startEmailActivity(Context context, String to, String subject, String body) {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");

        if (!TextUtils.isEmpty(to)) {
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] { to });
        }
        if (!TextUtils.isEmpty(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }
        if (!TextUtils.isEmpty(body)) {
            intent.putExtra(Intent.EXTRA_TEXT, body);
        }

        final PackageManager pm = (PackageManager) context.getPackageManager();
        try {
            if (pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() == 0) {
                intent.setType("text/plain");
            }
        }
        catch (Exception e) {
            IAW_LogTool.e("Exception encountered while looking for email intent receiver."+e);
        }

        context.startActivity(intent);
    }

    private static HashMap<String, WeakReference<Activity>> mContexts = new HashMap<String, WeakReference<Activity>>();
    //一般在BaseActivity的onCreate里执行
    public static  synchronized void pushActivity(Activity context) {
        WeakReference<Activity> reference = new WeakReference<Activity>(context);
        mContexts.put(context.getClass().getSimpleName(), reference);
    }
    //一般在BaseActivity的onDestroy里执行
    public static synchronized void popActivity(Activity context) {
        mContexts.remove(context.getClass().getSimpleName());
    }

    public static Activity getActivity(String className) {
        WeakReference<Activity> reference = mContexts.get(className);
        if (reference == null) {
            return null;
        }
        final Activity context = reference.get();
        if (context == null) {
            mContexts.remove(className);
        }
        return context;
    }

    public static  void finishAllActivity(){
        Iterator iter = mContexts.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            WeakReference<Activity> reference = (WeakReference<Activity>) entry.getValue();
            if (reference != null) {
                Activity activity = reference.get();
                if (activity != null) {
                    activity.finish();
                }
            }
        }
        mContexts.clear();

    }

   public static void appExit(){
       try {
           finishAllActivity();
           //退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
           System.exit(0);
           //从操作系统中结束掉当前程序的进程
           android.os.Process.killProcess(android.os.Process.myPid());
       } catch (Exception e) {
           e.printStackTrace();
       }

   }

    public static HashMap<String, WeakReference<Activity>> getMContexts() {
        return mContexts;
    }


}
