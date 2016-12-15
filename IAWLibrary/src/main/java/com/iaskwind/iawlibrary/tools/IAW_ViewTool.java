package com.iaskwind.iawlibrary.tools;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;


public class IAW_ViewTool {

    public static void setViewVisibility(View view, boolean visibility) {
        if (view == null) {
            return;
        }
        if (visibility) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        } else {
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public static void setMenuVisible(MenuItem menuItem, boolean visible) {
        if (menuItem == null) {
            return;
        }
        if (visible) {
            if (!menuItem.isVisible()) {
                menuItem.setVisible(true);
            }
        } else {
            if (menuItem.isVisible()) {
                menuItem.setVisible(false);
            }
        }
    }

    public final static  <V extends View> V findView(Activity activity,int id){
        try {
            return (V) activity.findViewById(id);
        }catch (ClassCastException e){
            throw  e;
        }
    }

}
