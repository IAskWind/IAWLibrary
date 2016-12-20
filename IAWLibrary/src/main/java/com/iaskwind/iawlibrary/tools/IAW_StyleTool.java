package com.iaskwind.iawlibrary.tools;

import android.graphics.Paint;
import android.widget.TextView;

/**
 * Created by winston on 16/12/19.
 */

public class IAW_StyleTool {
    /**
     * TextView中间划线 比如淘宝显示的价钱
     */
    public static void setMiddleDash(TextView tv){
        tv.getPaint().setAntiAlias(true);//抗锯齿
        tv.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
    }
}
