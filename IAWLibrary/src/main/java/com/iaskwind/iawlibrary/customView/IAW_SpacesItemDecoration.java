package com.iaskwind.iawlibrary.customView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by winston on 16/12/19.
 * 设置RevyclerView的item距离上下左右的距离
 * mRecyclerView.addItemDecoration(new SpacesItemDecoration(5))
 */

public class IAW_SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int space;

    public IAW_SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0)
            outRect.top = space;
    }
}
