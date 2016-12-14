package com.itheima31.jdmall.utils;

import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;

/**
 * Created by Tony on 2016/10/27.
 */

public class ThreadUtils {

    public static void execute(final ThreadChangeListener l) {

        l.onPre();
        ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
            @Override
            public void run() {
                if (l != null) {
                    l.onExecute();
                    UIUtils.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            l.onCompelete();
                        }
                    });
                }
            }
        });
    }

    public interface ThreadChangeListener {
        void onPre();

        void onExecute();

        void onCompelete();
    }
}
