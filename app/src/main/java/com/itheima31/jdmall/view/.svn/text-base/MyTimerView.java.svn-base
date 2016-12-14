package com.itheima31.jdmall.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;

/**
 * Created by Administrator on 2016/10/26.
 * author:ZY
 * 描述：计时器
 */

public class MyTimerView extends RelativeLayout {
    private TextView mHourText;
    private TextView mMinuteText;
    private TextView mSecondText;
    public int standradHour   = 60 * 60;               //标准小时
    public int StandradMinute = 60;                  //标准分钟
    public int time           = 2 * (standradHour);         //倒计时一小时
    public int hour           = 0;                          //当前小时
    public int minute         = 0;                        //当前分钟
    public int second         = 0;                        //当前秒
    private Handler             mHandler;
    private OnTimerZeroListener mOnTimerZeroListener;

    public MyTimerView(Context context) {
        this(context, null);
    }

    private void init() {
        View.inflate(getContext(), R.layout.item_timer, this);
        initView();
        initTask();
    }

    private void initView() {
        mHourText = (TextView) findViewById(R.id.tv_h);
        mMinuteText = (TextView) findViewById(R.id.tv_m);
        mSecondText = (TextView) findViewById(R.id.tv_s);
    }

    private void initTask() {
        mHandler = new Handler();
        new Thread(new TimerTask()).start();
    }

    public MyTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnTimerZeroListener(OnTimerZeroListener onTimerZeroListener) {
        mOnTimerZeroListener = onTimerZeroListener;
    }

    public int getTimes() {
        return time;
    }

    class TimerTask implements Runnable {

        @Override
        public void run() {
            initData();

            MyApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    setView();
                }
            });

            if (time >= 1) {

                mHandler.postDelayed(this, 1000);
                time--;
            } else {
                if (mOnTimerZeroListener != null) {
                    mOnTimerZeroListener.onTimeOut();
                }

            }

        }
    }

    private void setView() {

        if (second < 1) {
            mSecondText.setText("00");
        } else if (second < 10) {
            mSecondText.setText("0" + second);
        } else {
            mSecondText.setText("" + second);
        }

        if (hour < 1) {
            mHourText.setText("00");
        } else if (hour < 10) {
            mHourText.setText("0" + hour);
        } else {
            mHourText.setText("" + hour);
        }
        if (minute < 1) {
            mMinuteText.setText("00");
        } else if (minute < 10) {
            mMinuteText.setText("0" + "" + minute);
        } else {
            mMinuteText.setText("" + minute);
        }
    }

    private void initData() {
        hour = time / standradHour;
        if (hour > 0) {
            minute = (time - (hour * standradHour)) / StandradMinute;
        } else {
            minute = time / StandradMinute;
        }
        if (minute < 0 && hour < 0) {
            second = time;
        } else {
            second = (time - (hour * standradHour) - (minute * StandradMinute));
        }
    }

    public void setTimes(int times) {
        time = times;
    }

    public interface OnTimerZeroListener {
        void onTimeOut();
    }
}
