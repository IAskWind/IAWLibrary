package com.iaskwind.iawlibrary.tools;

import java.util.List;

/**
 * Created by winston on 16/11/28.
 */
public class IAW_ListTool{

    /**
     * List<T> 随机取一个 T
     * @param list
     * @return
     */
    public static Object randomList(List list){
        return list.get((int)(Math.random() * list.size()));
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
