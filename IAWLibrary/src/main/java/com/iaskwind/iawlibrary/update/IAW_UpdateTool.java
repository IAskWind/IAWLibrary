package com.iaskwind.iawlibrary.update;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.AppUtils;
import com.iaskwind.iawlibrary.R;
import com.iaskwind.iawlibrary.retrofit.IAW_RetrofitServiceTool;
import com.iaskwind.iawlibrary.retrofit.Progress.DownloadProgressHandler;
import com.iaskwind.iawlibrary.retrofit.Progress.ProgressHelper;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import me.drakeet.materialdialog.MaterialDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by winston on 16/11/29.
 * 自动更新的工具类
 */
public class IAW_UpdateTool {

    private static final String TAG = IAW_UpdateTool.class.getSimpleName();
    private static RxAppCompatActivity mContext = null;
    private static String mSavePath;
    private static String apkName;
    public static String getUpdateInfoUrl = "";

    /**
     *
     * @param context
     * @param updateInfoUrl 自动更新的全路径
     * @param manualCheck  true 手动检测 false 自动检测
     */
    public static void initUpdate(RxAppCompatActivity context,String updateInfoUrl,final boolean manualCheck) {
        mContext = context;
        getUpdateInfoUrl= updateInfoUrl;
        IAW_UpdateAPI service = IAW_RetrofitServiceTool.createRetrofitService(IAW_UpdateAPI.class);
        service.getUpdateInfo(updateInfoUrl)
                .compose(mContext.<IAW_UpdateInfoModel>bindToLifecycle())
                .throttleFirst(10, TimeUnit.SECONDS)
                .delay(manualCheck?0:3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<IAW_UpdateInfoModel>() {
                    @Override
                    public void call(IAW_UpdateInfoModel updateInfoReturnBean) {
                        Log.d(TAG, updateInfoReturnBean.getVersionName());
                        if (updateInfoReturnBean.getVersionCode() > AppUtils.getAppVersionCode(mContext)) {
                            showUpdateDialog(updateInfoReturnBean);
                        }else if(manualCheck){
                            Snackbar.make(mContext.findViewById(android.R.id.content),"该版本已经是最新版本啦",Snackbar.LENGTH_LONG).show();
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "检查更新失败");
                    }
                });

    }

    private static void showUpdateDialog(final IAW_UpdateInfoModel updateInfo) {

        final MaterialDialog mMaterialDialog = new MaterialDialog(mContext);
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_updateinfo,
                        null);
        TextView updateUpdateName = (TextView) view.findViewById(R.id.update_updateName);
        TextView updateLog = (TextView) view.findViewById(R.id.updateLog);
        Button btnOkUpdateInfo = (Button) view.findViewById(R.id.btn_ok_updateInfo);
        Button btnCancleUpdateInfo = (Button) view.findViewById(R.id.btn_cancle_updateInfo);
        updateUpdateName.setText(updateInfo.getVersionName());
        updateLog.setText(updateInfo.getUpdateLog());
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.setView(view).show();
        btnCancleUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
            }
        });

        btnOkUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialDialog.dismiss();
                String url = updateInfo.getPath();
                retrofitDownload(url);
            }
        });
    }

    private static void retrofitDownload(String downloadUrl) {
        //监听下载进度
        final ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setProgressNumberFormat("%1d KB/%2d KB");
        dialog.setTitle("下载");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();

        apkName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1) + ".apk";
        Log.d(TAG, downloadUrl);

//        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://msoftdl.360.cn");

//        OkHttpClient.Builder builder = ProgressHelper.addProgress(null);
//        DownloadApi retrofit = retrofitBuilder
//                .client(builder.build())
//                .build().create(DownloadApi.class);
        //这里的baseURL不起作用
        IAW_UpdateAPI service = IAW_RetrofitServiceTool.downloadRetrofitServiceByProgress(IAW_UpdateAPI.class);

        ProgressHelper.setProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long bytesRead, long contentLength, boolean done) {
                Log.e("是否在主线程中运行", String.valueOf(Looper.getMainLooper() == Looper.myLooper()));
                Log.e("onProgress", String.format("%d%% done\n", (100 * bytesRead) / contentLength));
                Log.e("done", "--->" + String.valueOf(done));
                dialog.setMax((int) (contentLength / 1024));
                dialog.setProgress((int) (bytesRead / 1024));

                if (done) {
                    dialog.dismiss();
                    //多次调用
//                    Log.d(TAG, "调用安装程序");
//                    if (!installed) {
//                        installed = true;
//                        installApk();
//                    }

                }
            }
        });

        //Call<ResponseBody> call = retrofit.retrofitDownload();
//        String url="http://msoftdl.360.cn/mobilesafe/shouji360/360safesis/360MobileSafe_6.2.3.1060.apk";
        Call<ResponseBody> call = service.downloadFileWithUrlAsync(downloadUrl);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream is = response.body().byteStream();
                    File file = new File(getMSavePath(), apkName);
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                    fos.close();
                    bis.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //调用安装
                installApk();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private static String getMSavePath() {
        // 外置SD卡处理方式
        // 判断SD卡是否存在，并且是否具有读写权限
        String sdpath = "";
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // 获得存储卡的路径
            sdpath = Environment.getExternalStorageDirectory() + "/";
            mSavePath = sdpath + "download";
        } else {
            // 内置SD卡
            sdpath = mContext.getFilesDir() + "/";
            mSavePath = sdpath;
        }
        File file = new File(mSavePath);
        // 判断文件目录是否存在
        if (!file.exists()) {
            file.mkdir();
        }
        return mSavePath;
    }

    /**
     * 安装APK文件
     */
    private static void installApk() {
        File apkfile = new File(mSavePath, apkName);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        mContext.startActivity(i);
    }

    /**
     * https://github.com/czy1121/update
     * @return
     */
    public static String update(){
        return "网上有update工具:https://github.com/czy1121/update 目前依赖已经加了!";
    }

}
