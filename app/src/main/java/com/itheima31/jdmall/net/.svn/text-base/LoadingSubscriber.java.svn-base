package com.itheima31.jdmall.net;

import com.itheima31.jdmall.net.api.BaseApi;

import rx.Subscriber;

/**
 * Created by Tony on 2016/10/28.
 */

public class LoadingSubscriber<T> extends Subscriber<T> {

    private BaseApi mBaseApi;

    public LoadingSubscriber(BaseApi api) {
        mBaseApi = api;

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (mBaseApi.getListener() != null){
            mBaseApi.getListener().onError(e);
        }
    }

    @Override
    public void onNext(T t) {
        if (mBaseApi.getListener() != null){
            mBaseApi.getListener().onNext(t);
        }
    }
}
