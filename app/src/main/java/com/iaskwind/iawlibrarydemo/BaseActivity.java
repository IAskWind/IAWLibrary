package com.iaskwind.iawlibrarydemo;

import android.os.Bundle;

import com.iaskwind.iawlibrary.base.IAW_BaseActivity;


/**
 * 如果重新定义ToolBar上面的HomeAsUp图标只需重写{@link #setBackEvent}即可
 * Created by Winston on 2016/4/5.
 */

public abstract class BaseActivity extends IAW_BaseActivity {
//    private static final String TAG = IAW_BaseActivity.class.getSimpleName();
//    protected final RxAppCompatActivity mContext = this;
//    protected Toolbar mToolbar;
//    protected InputMethodManager mImm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    protected void setToolBarTitle(String title) {
        mToolbar.setTitle(title);
    }
    @Override
    protected int getToolbarRes() {
        return R.id.toolbar;
    }
}
