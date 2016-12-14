package com.itheima31.jdmall.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yangg on 2016/10/24.
 */
public class MineController extends BaseController<String> {
    public static final String TAG = "MineController";
    @InjectView(R.id.item_mine_tv)
    TextView mTestTv;
    @InjectView(R.id.go_details_iv)
    ImageView mGoDetailsIv;
    @InjectView(R.id.wifi_select_iv)
    ImageView mWifiSelectIv;
    @InjectView(R.id.non_wifi_select_iv)
    ImageView mNonWifiSelectIv;
    @InjectView(R.id.wifi_select_layout)
    RelativeLayout mWifiSelectLayout;
    @InjectView(R.id.pop_text_show)
    TextView mTextShow;

    public String mFlags;
    public int favoritesCount;
    public int orderCount;

    @Override
    public View getView() {
        View inflateView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_mine_fragment, null);
        ButterKnife.inject(this, inflateView);
        mTextShow.setVisibility(View.GONE);
        return inflateView;
    }

    @Override
    public void refresHolderView(String data) {
        mTestTv.setText(data);
        mFlags = data;
        if (data.equals("非WLAN环境手动下载图片") || data.equals("WLAN环境下自动更新")) {
            mGoDetailsIv.setVisibility(View.GONE);
            mWifiSelectLayout.setVisibility(View.VISIBLE);
            mTextShow.setVisibility(View.GONE);
            boolean isWifi = SPUtil.getBoolean(UIUtils.getContext(), "wifi_state" + data, true);
            setWifiState(isWifi);
        } else if (data.equals("我的订单") || data.equals("宝贝收藏")) {
            //显示数量泡泡
            mGoDetailsIv.setVisibility(View.GONE);
            mWifiSelectLayout.setVisibility(View.GONE);
            mTextShow.setVisibility(View.GONE);

        } else {
            mGoDetailsIv.setVisibility(View.VISIBLE);
            mWifiSelectLayout.setVisibility(View.GONE);
            mTextShow.setVisibility(View.GONE);
        }
    }
//    private void pupUpNumInCircle() {
//
//    }


    @OnClick(R.id.wifi_select_layout)
    public void onClick() {
        boolean isWifi = SPUtil.getBoolean(UIUtils.getContext(), "wifi_state" + mFlags, true);
        if (isWifi) {
            mWifiSelectIv.setVisibility(View.GONE);
            mNonWifiSelectIv.setVisibility(View.VISIBLE);
        } else {
            mWifiSelectIv.setVisibility(View.VISIBLE);
            mNonWifiSelectIv.setVisibility(View.GONE);
        }
        isWifi = !isWifi;

        SPUtil.putBoolean(UIUtils.getContext(), "wifi_state" + mFlags, isWifi);
    }

    private void setWifiState(boolean isWifi) {
        if (isWifi) {
            mWifiSelectIv.setVisibility(View.VISIBLE);
            mNonWifiSelectIv.setVisibility(View.GONE);
        } else {
            mWifiSelectIv.setVisibility(View.GONE);
            mNonWifiSelectIv.setVisibility(View.VISIBLE);
        }
    }
}
