package com.iaskwind.iawlibrary.rxjava;

import android.content.ComponentCallbacks;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by winston on 16/12/15.
 * https://github.com/ReactiveX/RxJava
 * https://github.com/trello/RxLifecycle
 */

public class IAW_RxJavaGeneralReqTool{

    public interface ReqListener<T> {

        void onSuccess(T entity);
        void onFailure(Throwable throwable);

        /**
         * Rxlifecycle (https://github.com/trello/RxLifecycle)就使被用来严格控制由于发布了一个订阅后，由于没有及时取消，导致Activity/Fragment无法销毁导致的内存泄露。
         * @param <T>
         * @return
         */
        <T> Observable.Transformer<T, T> lifecycle();
    }

   public interface GeneralReqListener<T> extends ReqListener<T>{
        void startLoading();
        void endLoading();

    }

    public  static <T> void generalReq(Observable<T> observable, final GeneralReqListener<T> generalReqListener){

        observable.subscribeOn(Schedulers.io())
                .compose(generalReqListener.<T>lifecycle())
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

    //    mContext.<T>bindToLifecycle()
    public  static <T> void generalReq(Observable<T> observable, final ReqListener<T> reqListener){

        observable.subscribeOn(Schedulers.io())
                .compose(reqListener.<T>lifecycle())
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
