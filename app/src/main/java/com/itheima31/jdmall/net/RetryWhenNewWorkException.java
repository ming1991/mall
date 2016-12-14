package com.itheima31.jdmall.net;

import com.itheima31.jdmall.net.api.BaseApi;
import com.itheima31.jdmall.net.api.UrlConstants;

import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Tony on 2016/10/28.
 */

public class RetryWhenNewWorkException implements Func1<Observable<? extends Throwable>, Observable<?>> {

    private final BaseApi mBaseApi;
    private int times = 3;
    private final HttpService mService;

    public RetryWhenNewWorkException(BaseApi baseApi) {
        mBaseApi = baseApi;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(UrlConstants.curBaseUrl)
                .build();

        mService = retrofit.create(HttpService.class);
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {

        return observable.flatMap(new Func1<Throwable, Observable<?>>() {
            @Override
            public Observable<?> call(Throwable throwable) {
                if ((throwable instanceof SocketTimeoutException
                        || throwable instanceof TimeoutException) && times-- > 0) {
//                    Observable<ClassifyEntity> classify = mService.getClassify();
//                    return classify;
                }
                return Observable.error(throwable);
            }
        });
    }
}
