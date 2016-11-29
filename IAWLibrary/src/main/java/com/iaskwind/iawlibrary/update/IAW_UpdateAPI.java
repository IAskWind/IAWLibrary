package com.iaskwind.iawlibrary.update;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by winston on 16/11/29.
 */
public interface IAW_UpdateAPI {

    @GET
    Call<ResponseBody> downloadFileWithUrlAsync(@Url String fileUrl);
    //获取自动更新的基本信息
    @GET
    Observable<IAW_UpdateInfoModel> getUpdateInfo(@Url String updateInfoUrl);
}
