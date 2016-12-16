package com.iaskwind.iawlibrary.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.iaskwind.iawlibrary.tools.IAW_ActivityTool;
import com.iaskwind.iawlibrary.tools.IAW_ViewTool;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;



/**
 * 如果重新定义ToolBar上面的HomeAsUp图标只需重写{@link #setBackEvent}即可
 * Created by Winston on 2016/4/5.
 * https://github.com/JakeWharton/butterknife
 * https://github.com/avast/android-butterknife-zelezny 插件
 */

public abstract class IAW_BaseActivity extends RxAppCompatActivity {
    private static final String TAG = IAW_BaseActivity.class.getSimpleName();
    protected final RxAppCompatActivity mContext = this;
    protected Toolbar mToolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IAW_ActivityTool.pushActivity(this);
        int layout = setContentView();
        if (layout != 0) {
            setContentView(layout);
            ButterKnife.bind(this);

            if (hasToolBar()) {
                mToolbar = IAW_ViewTool.findView(this,getToolbarRes());
                ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);
            }


            initView();
            initToolBar();
            setBackListener();
            setOnclick();
            initRxBus();

        }
    }


    /**
     * 设置view
     *
     * @return
     */
    protected abstract int setContentView();



    protected boolean hasToolBar() {
        return true;
    }
    //在项目的BaseActivity处理即可，后续的子activity直接调这个方法来给toolbar赋值
    protected abstract void setToolBarTitle(String title);
    //在项目的BaseActivity处理即可，返回toolbar的资源id
    protected abstract int getToolbarRes();


    protected void initToolBar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            //不显示原有的title
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         }
    }



    protected void setBackListener() {
        if (mToolbar != null) {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setBackEvent();
                }
            });
        }
    }

    /**
     * 设置返回按钮的事件，默认返回到前一个页面
     */
    protected void setBackEvent() {
//        mContext.finish();
        onBackPressed();
    }




    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 设置监听事件
     */
    protected abstract void setOnclick();

    /**
     * 设置RxBus
     */
    protected abstract void initRxBus();


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        IAW_ActivityTool.popActivity(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
             finish();
        }
        return false;
    }

    public String tip(){
        //annotationProcessor "com.jakewharton:butterknife-compiler:${butterknifeVersion}"
        return "需要在项目的build.gradle的dependencies中添加上面那句，保证butterknife在使用的时候不会出现空指针";
    }

}
