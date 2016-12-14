package com.itheima31.jdmall.net;

/**
 * Created by Tony on 2016/10/28.
 */

public abstract class HttpOnNextListener<T> {

    public abstract void onNext(T t);

    public void onError(Throwable e){
    }
}
