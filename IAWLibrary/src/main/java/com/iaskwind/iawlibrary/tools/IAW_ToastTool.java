package com.iaskwind.iawlibrary.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by winston on 16/8/10.
 */
public class IAW_ToastTool {

    private static Toast mToast = null;
    public static void showToast(Context mContext,String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext.getApplicationContext(),text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }

        mToast.show();
    }


}
