package com.iaskwind.iawlibrary.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;

/**
 * Created by winston on 16/8/5.
 */
public class IAW_DialogTool {

    public interface DialogCallBack{
        void ok();
        void cancle(DialogInterface dialog);
    }


    public static void showSysDialog(Context mContext, String title, View view, String msg, final DialogCallBack dialogCallBack) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle(title);
        if (view != null) {
            builder.setView(view);
        }
        if (msg != null) {
            builder.setMessage(msg);
        }
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                dialogCallBack.cancle(dialog);

            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogCallBack.ok();

            }

        });
        AlertDialog dlg = builder.create();
        dlg.show();
    }
    public static void showDialog(Context mContext, String title, String msg, final DialogCallBack listener){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle(title).setMessage(msg)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        dialog.dismiss();
                          listener.cancle(dialog);

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        IAW_RxBus.getDefault().post(new UpdateEvent(UpdateEvent.EXITAPP));
//                        AppContext.loginOut();
                        listener.ok();
                    }

                });
        AlertDialog dlg = builder.create();
        dlg.show();
    }


    public static void showDialog1(Context mContext, String title, String msg, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
            builder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_LIGHT);
        } else {
            builder = new AlertDialog.Builder(mContext);
        }
        builder.setTitle(title).setMessage(msg)
                .setNegativeButton("取消", listener)
                .setPositiveButton("确定", listener);
        AlertDialog dlg = builder.create();
        dlg.show();
    }
}
