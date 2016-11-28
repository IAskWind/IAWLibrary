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
        String[] strs = str.split(symbol);
        return  Arrays.asList(strs);
    }

    /**
     * List<String>转String 用指定分隔符分隔
     * @param list
     * @param symbol
     * @return
     */
    public static String listToString(List<String> list,String symbol){
        String listString = "";
        for (int i = 0;i <list.size(); i++){
            if(i != list.size()-1) {
                listString += list.get(i) + symbol;
            }else{
                listString += list.get(i);
            }
        }
        return listString;
    }
}
