package com.iaskwind.iawlibrary.tools;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.blankj.utilcode.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.iaskwind.iawlibrary.R;
import com.iaskwind.iawlibrary.iaw.IAW;

import java.util.List;

/**
 * Created by winston on 16/11/28.
 * 设置一个样式启动页面,在onCreate,删除默认的setContentView,然后直接调用IAW_LaunchTool.launchView
 */
public class IAW_LaunchTool {

    public interface ActivityCallBack{
        void setView(View view); //设置的view
        void end(); //结束执行的方法
    }
    /**
     *设置一个样式启动页面,在onCreate,删除默认的setContentView,然后直接调用IAW_LaunchTool.launchView,在回调里面处理setContentView
     * @param context
     * @param spUtils  用来保存网络图片的名字，记录那些已经被缓存了
     * @param defaultLaunchImg //默认启动图片 没有下载网络图片，用本地
     * @param logo 底部的logo图片
     * @param logoWidth 单位dp
     * @param logoHeight 单位dp
     * @param baseUrl 网络的图片的url
     * @param splashNames 网络图片的名字list
     * @param activityCallBack 回调
     */
    public static void  launchView(Activity context, final SPUtils spUtils, int defaultLaunchImg, int logo, int logoWidth, int logoHeight, String baseUrl, List<String> splashNames,final ActivityCallBack activityCallBack){
        View launchview = View.inflate(context, R.layout.launchview, null);
        activityCallBack.setView(launchview);
        final ImageView launchImageView = (ImageView) launchview.findViewById(R.id.launchImage);
        View launchLogo = launchview.findViewById(R.id.launchLogo);
        launchLogo.setBackgroundResource(logo);

        ViewGroup.LayoutParams lp=launchLogo.getLayoutParams();
        lp.width= IAW_DisplayTool.dip2px(context,logoWidth);
        lp.height=IAW_DisplayTool.dip2px(context,logoHeight);
        launchLogo.setLayoutParams(lp);
        View foreMask =launchview.findViewById(R.id.launchMask);
        String splashStr = spUtils.getString(IAW_ContantTool.LAUNCH_IMAGES,"");
        final List<String> splashs = IAW_StringTool.stringToList(splashStr,",");
        final String currentSplash = (String) IAW_ListTool.randomList(splashNames);
//        final String currentSplash = splashNames.get((int)(Math.random() * splashNames.size()));
        final String imageUrl = baseUrl +currentSplash;
        if(splashs.contains(currentSplash)){
            Log.d("StartHomeActivity","加载本地图片");
            Glide.with(context)
                    .load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(launchImageView);
        }else{
            Glide.with(context)
                    .load(defaultLaunchImg)
                    .into(launchImageView);

            Glide.with(context)
                    .load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new SimpleTarget<GlideDrawable>() {
                        //图片下载成功
                        @Override
                        public void onResourceReady(GlideDrawable drawable, GlideAnimation animation) {

                            if(!splashs.contains(currentSplash)){
                                Log.d("StartHomeActivity",imageUrl+ "下载完成");
                                splashs.add(currentSplash);
                                String spImg = IAW_ListTool.listToString(splashs,",");
                                spUtils.putString(IAW_ContantTool.LAUNCH_IMAGES,spImg);
                            }

                        }
                        //图片下载失败
                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable){
                            Log.d("StartHomeActivity",imageUrl+ "下载报错");
                            if(splashs.contains(currentSplash)){
                                splashs.remove(currentSplash);
                                String spImg = IAW_ListTool.listToString(splashs,",");
                                spUtils.putString(IAW_ContantTool.LAUNCH_IMAGES,spImg);
                            }
                        }
                    });

        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        foreMask.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activityCallBack.end();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
