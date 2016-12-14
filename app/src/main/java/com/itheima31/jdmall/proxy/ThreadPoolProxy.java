package com.itheima31.jdmall.proxy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 类    名:  ThreadPoolProxy
 * 描    述： 线程池的代理类
 * 描    述： 替原对象(ThreadPool)完成一些操作(提交任务,执行任务,移除任务)
 */
public class ThreadPoolProxy {
    ThreadPoolExecutor mExecutor;//核心池大小

    private int mCorePoolSize;

    public ThreadPoolProxy(int corePoolSize) {
        mCorePoolSize = corePoolSize;
    }

    /**
     * 创建ThreadPoolExecutor对象
     */
    private void initThreadPoolExecutor() {
        //双重检查加锁,只有在第一次实例化的时候才启用同步机制,提高了性能
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()) {
                    int maximumPoolSize = mCorePoolSize;//最大线程数
                    long keepAliveTime = 0;//保持时间
                    TimeUnit unit = TimeUnit.MILLISECONDS;//单位
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();//任务队列
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();//线程工厂
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
                    mExecutor = new ThreadPoolExecutor(mCorePoolSize, maximumPoolSize,
                            keepAliveTime, unit,
                            workQueue, threadFactory, handler);
                }
            }
        }
    }

    /**
     * 提交任务
     *
     * @param task
     */
    public Future submit(Runnable task) {
        //初始化mExecutor对象
        initThreadPoolExecutor();
        Future<?> submitResult = mExecutor.submit(task);
        return  submitResult;
    }

    /**
     * 执行任务
     *
     * @param task
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }


    /**
     * 移除任务
     *
     * @param task
     */
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }
}
