package com.itheima31.jdmall.net;

import com.itheima31.jdmall.net.api.BaseApi;
import com.itheima31.jdmall.net.api.UrlConstants;
import com.itheima31.jdmall.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import static com.itheima31.jdmall.net.api.UrlConstants.curBaseUrl;

/**
 * Created by Tony on 2016/10/28.
 */

public class HttpManager {

    private volatile static HttpManager mInstance;
    private static final int TIME_OUT = 500;
    private int retryTimes = 0;

    private HttpManager() {
    }

    /*---------| 单例 |---------*/
    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    return new HttpManager();
                }
            }
        }
        return mInstance;
    }


    public void doHttpDeal(final BaseApi baseApi) {

        /*---------| okhttp |---------*/
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(null)
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .build();

        /*---------| retrofit |---------*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseApi.getBaseUrl())
                .build();
        HttpService service = retrofit.create(HttpService.class);

        /*---------| rxjava |---------*/
        final LoadingSubscriber subscriber = new LoadingSubscriber(baseApi);
        final Observable observable = baseApi.getObservable(service)
//                .retryWhen()
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
                .subscribeOn(Schedulers.immediate())
                .unsubscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate());

        observable.subscribe(new Subscriber() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                if (retryTimes < 3) {
                    LogUtils.e("retryTimes" + retryTimes);
                    curBaseUrl = UrlConstants.baseRulsArray[retryTimes++];
                    doHttpDeal(baseApi);
                } else {
                    retryTimes = 0;
                    baseApi.getListener().onError(e);
                }
            }

            @Override
            public void onNext(Object o) {
                baseApi.getListener().onNext(o);
            }
        });

    }
}
