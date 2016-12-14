package com.itheima31.jdmall.factory;

import com.itheima31.jdmall.proxy.ThreadPoolProxy;

/**
 * 类    名:  ThreadPoolProxyFactory
 */
public class ThreadPoolProxyFactory {
    static ThreadPoolProxy mNormalProxy;
    static ThreadPoolProxy mDownLoadProxy;

    /**
     * 创建普通的线程池代理
     */
    public static ThreadPoolProxy createNormalPoolProxy() {
        if (mNormalProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mNormalProxy == null) {
                    mNormalProxy = new ThreadPoolProxy(5);
                }
            }
        }
        return mNormalProxy;
    }

    /**
     * 待用
     */
    public static ThreadPoolProxy createOtherPoolProxy() {
        if (mDownLoadProxy == null) {
            synchronized (ThreadPoolProxyFactory.class) {
                if (mDownLoadProxy == null) {
                    mDownLoadProxy = new ThreadPoolProxy(3);
                }
            }
        }
        return mDownLoadProxy;
    }
}
