package com.itheima31.jdmall.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.base.MyBaseAdapter;
import com.itheima31.jdmall.bean.MessagerCenterBean;
import com.itheima31.jdmall.controller.MsgCenterController;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MessageCenterActivity extends AppCompatActivity {
    @InjectView(R.id.act_message_center_lv)
    ListView mActMessageCenterLv;
    private List<MessagerCenterBean> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        ButterKnife.inject(this);
        setCustomActionBar();
        initData();
        MsgCenterAdapter adapter = new MsgCenterAdapter(mDatas, mActMessageCenterLv);
        mActMessageCenterLv.setAdapter(adapter);

        mActMessageCenterLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessagerCenterBean bean = mDatas.get(position);
                if (bean.maxTextview.equals("京东咚咚")) {
                    Toast.makeText(UIUtils.getContext(), "客服也是需要休息的", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("京东客服")) {
                    Toast.makeText(UIUtils.getContext(), "晚点再来找我们客服吧，现在还没上班的", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("系统通知")) {
                    Toast.makeText(UIUtils.getContext(), "你的商品2341213435运输途中已经爆炸了", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("我的京东")) {
                    Toast.makeText(UIUtils.getContext(), "哪有什么优惠信息，想多了你", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("店铺签到")) {
                    Toast.makeText(UIUtils.getContext(), "对方向你发送了一张波音747的100万优惠券", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("京东支付")) {
                    Toast.makeText(UIUtils.getContext(), "你的余额又少了", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("京东物流")) {
                    Toast.makeText(UIUtils.getContext(), "商品已经签收", Toast.LENGTH_SHORT).show();
                }
                if (bean.maxTextview.equals("京东游戏")) {
                    Toast.makeText(UIUtils.getContext(), "你的时间非常宝贵", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void initData() {
        String[] maxTitle = getResources().getStringArray(R.array.max_string);
        String[] minTitle = getResources().getStringArray(R.array.min_string);

        for (int i = 0; i < maxTitle.length; i++) {
            MessagerCenterBean bean = new MessagerCenterBean();
            bean.maxTextview = maxTitle[i];
            bean.minTextview = minTitle[i];
            mDatas.add(i, bean);
        }

    }


    class MsgCenterAdapter extends MyBaseAdapter<MessagerCenterBean> {


        public MsgCenterAdapter(List<MessagerCenterBean> datas, ListView listView) {
            super(datas, listView);
        }

        @NonNull
        @Override
        public BaseController getSpecialbaseHolder(int position) {
            return new MsgCenterController();
        }


    }

    private void setCustomActionBar() {
        ActionBar actionBar = getSupportActionBar();
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View mActionBarView = LayoutInflater.from(this).inflate(R.layout.item_messager_center_actionbar, null);
        actionBar.setCustomView(mActionBarView, lp);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    /**
     *
     * 添加了回退键的点击事件，finish当前的activity;
     *
     * @param view
     */
    public void messageback(View view) {
        finish();

    }
}