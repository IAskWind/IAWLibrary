package com.iaskwind.iawlibrary.tools;

import com.blankj.utilcode.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by winston on 16/11/28.
 */
public class IAW_StringTool {

    /**
     * String转List<String> 按照指定分隔符
     * @param str
     * @param symbol
     * @return
     */
    public static List<String> stringToList(String str,String symbol){
        List<String> strList =new ArrayList<>();
        String[] strs = str.split(symbol);
        for(String s : strs){
            strList.add(s);
        }
        return  strList;
    }


}
