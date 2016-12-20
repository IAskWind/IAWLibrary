package com.iaskwind.iawlibrary.customView;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 重写NestedScrollView不拦截触摸事件
 * Created by winston on 16/12/19.
 */

public class IAW_NestedScrollView extends NestedScrollView{

    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;
    public IAW_NestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IAW_NestedScrollView(Context context) {
        super(context);
    }

    public IAW_NestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new IAW_ScrollDetector());
        setFadingEdgeLength(0);
    }

    class IAW_ScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}
