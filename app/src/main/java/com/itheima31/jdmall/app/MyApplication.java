package com.itheima31.jdmall.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Tony on 2016/10/20.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static Handler mHandler;
    private static long mMianThreadId;


    @Override
    public void onCreate() {
        //获取上下文
        mContext = getApplicationContext();
        //获取Hanlder
        mHandler = new Handler();
        //获取主线程Id
        Thread mianThread = Thread.currentThread();
        mMianThreadId = mianThread.getId();
//        ThreadManager
        super.onCreate();
    }


    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static long getMianThreadId() {
        return mMianThreadId;
    }

}