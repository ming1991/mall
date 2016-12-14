package com.itheima31.jdmall.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.MessagerCenterBean;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.Random;

import butterknife.InjectView;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/25 14:31
 * 描    述：
 */


public class MsgCenterController extends BaseController<MessagerCenterBean> {
    private int mI=0;

    @InjectView(R.id.item_msgcenter_list_iv)
    ImageView mItemMsgcenterListIv;
    @InjectView(R.id.item_msgcenter_list_maxtv)
    TextView  mItemMsgcenterListMaxtv;
    @InjectView(R.id.item_msgcenter_list_mintv)
    TextView  mItemMsgcenterListMintv;
    int[] icons = {
            R.drawable.a2n,
            R.drawable.a2p,
            R.drawable.a2q,
            R.drawable.a3m,
            R.drawable.abq,
            R.drawable.adp,
            R.drawable.as7,
            R.drawable.awx,
    };


    @Override
    public View getView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_msgcenter_list, null);
    }

    @Override
    public void refresHolderView(MessagerCenterBean data) {
    }

    @Override
    public void refresHolderViewWithPosition(MessagerCenterBean data, int position) {
        mItemMsgcenterListMaxtv.setText(data.maxTextview);
        mItemMsgcenterListMintv.setText(data.minTextview);
        Random random = new Random();

        mItemMsgcenterListIv.setImageResource(icons[position]);
    }
}
