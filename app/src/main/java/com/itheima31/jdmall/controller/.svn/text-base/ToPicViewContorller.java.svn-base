package com.itheima31.jdmall.controller;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;

import static com.itheima31.jdmall.R.id.home_topic_ts;

/**
 * Created by Administrator on 2016/10/25.
 * author:ZY
 */

public class ToPicViewContorller extends LinearLayout {
    @InjectView(home_topic_ts)
    TextSwitcher mHomeTopicTs;

    public String[] stringArray = new String[]{"高通晓龙830来了！收发机竟然是", "华为荣耀8手机中的战斗机，强势登顶智能手机排行榜", "走颜值路线，小米新机曝光.1499.."};
    public Timer mTimer;
    int index;




    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MSG_WHAT_UPDATE_NEWS_INFO:
                    updateNews();
//                    runTask();
                    break;
                default:
                    break;
            }
        }
    };
    private final View mView;

    public ToPicViewContorller(Context context) {
        super(context);
        mView = View.inflate(UIUtils.getContext(), R.layout.item_home_top_topic, null);
        mHomeTopicTs= (TextSwitcher) mView.findViewById(R.id.home_topic_ts);
        mHomeTopicTs.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mHomeTopicTs.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(UIUtils.getContext());
                tv.setTextSize(14);
                tv.setSingleLine(true);
                tv.setTextColor(Color.BLACK);
                return tv;
            }
        });

        mTimer =new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mHandler.obtainMessage(Constants.MSG_WHAT_UPDATE_NEWS_INFO).sendToTarget();
            }
        },1,4000);



//        runTask();
        addView(mView);
    }

    public void runTask(){
        mHandler.sendEmptyMessageDelayed(Constants.MSG_WHAT_UPDATE_NEWS_INFO,4000);
    }

    protected  void updateNews(){
        index++;
        if(index>=stringArray.length){
            index=0;
        }
        mHomeTopicTs.setText(stringArray[index]);

    }


}
