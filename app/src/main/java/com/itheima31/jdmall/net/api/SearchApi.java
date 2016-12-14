package com.itheima31.jdmall.net.api;

import com.itheima31.jdmall.net.HttpOnNextListener;
import com.itheima31.jdmall.net.HttpService;

import rx.Observable;

/**
 * Created by Tony on 2016/10/29.
 */

public class SearchApi extends BaseApi {

    public SearchApi(HttpOnNextListener listener) {
        super(listener);
    }

    @Override
    public Observable getObservable(HttpService service) {
       return  service.getSearch() ;
    }
}
