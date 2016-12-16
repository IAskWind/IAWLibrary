package com.iaskwind.iawlibrary.retrofit;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iaskwind.iawlibrary.retrofit.Progress.ProgressHelper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by winston on 16/11/29.
 * https://github.com/square/retrofit
 */
public class IAW_RetrofitServiceTool {
    //给个默认的有效的url否则会报 java.lang.IllegalArgumentException: Illegal URL:
    public static final String baseUrl = "http://127.0.0.1/";
    static Gson gson = new GsonBuilder()
            //配置Gson
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();
    private static Retrofit.Builder  getRetrofitBuilder(String baseUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return retrofitBuilder;

    }
    public static <T> T createRetrofitService(Class<T> service,String baseUrl) {
        return getRetrofitBuilder(baseUrl).build().create(service);
    }

    public static <T> T createRetrofitService(Class<T> service) {
        return getRetrofitBuilder(baseUrl).build().create(service);
    }

    /**
     * 文件下载获取进度条使用
     * @return
     */
    public static <T> T downloadRetrofitServiceByProgress(Class<T> service){
        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
        return getRetrofitBuilder(baseUrl).client(builder.build()).build().create(service);
    }
}
