package com.iaskwind.iawlibrary.tools;

import java.text.DecimalFormat;

/**
 * Created by winston on 16/11/25.
 */
public class IAW_formatTool {
    /**
     * double 格式化
     * @param params
     * @return
     */
    public static String getDoubleStr(double params){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(params);
    }
}
