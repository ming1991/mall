package com.itheima31.jdmall.utils;

import android.view.MotionEvent;
import android.view.View;

import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.widgets.ChildViewPager;

/**
 * Created by Administrator on 2016/10/26.
 * author:ZY
 * 描述：用来自动轮播的类
 */

public class AutoScrollUtils implements Runnable{
    public ChildViewPager mViewPage;

    public AutoScrollUtils(ChildViewPager viewPager){
        mViewPage=viewPager;
        //按下去的时候停止轮播
        mViewPage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        start();
                        break;

                    case MotionEvent.ACTION_CANCEL:
//                        autoScrollTask.start();

                        break;


                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void start() {
        MyApplication.getHandler().postDelayed(this, 3000);
    }

    public void stop() {
        MyApplication.getHandler().removeCallbacks(this);
    }

    @Override
    public void run() {
        //得到当前的position
        int curPosition = mViewPage.getCurrentItem();
        curPosition++;

        mViewPage.setCurrentItem(curPosition);

        start();
    }
}
