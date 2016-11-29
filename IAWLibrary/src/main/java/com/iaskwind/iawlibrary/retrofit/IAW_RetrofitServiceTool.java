package com.iaskwind.iawlibrary.retrofit;



import com.iaskwind.iawlibrary.retrofit.Progress.ProgressHelper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by winston on 16/11/29.
 */
public class IAW_RetrofitServiceTool {

    private static Retrofit.Builder  getRetrofitBuilder(String baseUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return retrofitBuilder;

    }
    public static <T> T createRetrofitService(Class<T> service,String baseUrl) {
        return getRetrofitBuilder(baseUrl).build().create(service);
    }

    /**
     * 文件下载获取进度条使用
     * @return
     */
    public static <T> T downloadRetrofitServiceByProgress(Class<T> service,String baseUrl){
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        return getRetrofitBuilder(baseUrl).client(builder.build()).build().create(service);
    }
}
