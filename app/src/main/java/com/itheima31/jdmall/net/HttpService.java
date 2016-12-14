package com.itheima31.jdmall.net;

import com.itheima31.jdmall.bean.ClassifyBean;
import com.itheima31.jdmall.bean.SearchBean;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Tony on 2016/10/28.
 */

public interface HttpService {

    @GET("category")
    Observable<ClassifyBean> getClassify();

    @GET("search/recommend")
    Observable<SearchBean> getSearch();

}
