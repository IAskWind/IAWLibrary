package com.iaskwind.iawlibrary.tools;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * Created by winston on 16/4/25.
 */
public class IAW_ResourceTool {

    private static final String DEFTYPE_DRAWABLE = "drawable";
    private static final String DEFTYPE_COLOR = "color";


    public static Drawable getDrawableByName(Activity mActivity,String name)
    {
        try
        {

            return mActivity.getResources() .getDrawable(mActivity.getResources().getIdentifier(name, DEFTYPE_DRAWABLE,mActivity.getPackageName() ));
        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }



    public static int getColor(Activity mActivity,String name){
        try
        {

            return mActivity.getResources().getColor(mActivity.getResources().getIdentifier(name, DEFTYPE_COLOR, mActivity.getPackageName()));

        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static ColorStateList getColorStateList(Activity mActivity,String name)
    {
        try
        {

            return mActivity.getResources().getColorStateList(mActivity.getResources().getIdentifier(name, DEFTYPE_COLOR, mActivity.getPackageName()));

        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
