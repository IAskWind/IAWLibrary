package com.iaskwind.iawlibrary.retrofit;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by winston on 16/12/15.
 */

public class IAW_RxJavaGeneralReqTool{



   public interface GeneralReqListener<T> {
        void startLoading();
        void endLoading();
        void onSuccess(T entity);
        void onFailure(Throwable throwable);
    }

    public  static <T> void generalReq(Observable<T> observable, RxAppCompatActivity mContext, final GeneralReqListener<T> generalReqListener){

        observable.subscribeOn(Schedulers.io())
                .compose(mContext.<T>bindToLifecycle())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        generalReqListener.startLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        generalReqListener.endLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<T>() {
                    @Override
                    public void call(T entity) {
                        generalReqListener.onSuccess(entity);
//                        if (newsEntity1.isTokenInvalid()) {
//                            AppContext.loginOut();
//                            return;
//                        }
//                        view.handlerSuccessReviewInfo(newsEntity1, isRefresh, flag);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
//                        view.handlerFailureCommonView(isRefresh, flag);
                        generalReqListener.onFailure(throwable);
                    }
                });
    }
}
