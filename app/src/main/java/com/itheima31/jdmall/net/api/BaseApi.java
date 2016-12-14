package com.itheima31.jdmall.net.api;

import com.itheima31.jdmall.net.HttpOnNextListener;
import com.itheima31.jdmall.net.HttpService;

import rx.Observable;

/**
 * Created by Tony on 2016/10/28.
 */

public abstract class BaseApi {

    private HttpOnNextListener mListener;

    public BaseApi(HttpOnNextListener listener){
        mListener = listener;
    }


    public abstract Observable getObservable(HttpService service);

    public String getBaseUrl() {
        return UrlConstants.curBaseUrl;
    }

    public HttpOnNextListener getListener() {
        return mListener;
    }


}
