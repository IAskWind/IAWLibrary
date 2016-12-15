package com.iaskwind.iawlibrarydemo;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Dandy on 2016/10/27.
 */

public interface NewService {


    @GET("http://gank.io/api/data/Android/10/1")
    Observable<News> getNews();
}
