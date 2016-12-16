package com.iaskwind.iawlibrary.rxjava;

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

    public interface ReqListener<T> {

        void onSuccess(T entity);
        void onFailure(Throwable throwable);
    }

   public interface GeneralReqListener<T> extends ReqListener<T>{
        void startLoading();
        void endLoading();
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
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        generalReqListener.onFailure(throwable);
                    }
                });
    }

    public  static <T> void generalReq(Observable<T> observable, RxAppCompatActivity mContext, final ReqListener<T> reqListener){

        observable.subscribeOn(Schedulers.io())
                .compose(mContext.<T>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<T>() {
                    @Override
                    public void call(T entity) {
                        reqListener.onSuccess(entity);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        reqListener.onFailure(throwable);
                    }
                });
    }
}
