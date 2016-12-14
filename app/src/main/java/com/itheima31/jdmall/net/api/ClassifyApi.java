package com.itheima31.jdmall.net.api;

import com.itheima31.jdmall.net.HttpOnNextListener;
import com.itheima31.jdmall.net.HttpService;

import rx.Observable;

/**
 * Created by Tony on 2016/10/28.
 */

public class ClassifyApi extends BaseApi {

    public ClassifyApi(HttpOnNextListener listener) {
        super(listener);
    }

    @Override
    public Observable getObservable(HttpService service) {
        return service.getClassify();
    }
}
