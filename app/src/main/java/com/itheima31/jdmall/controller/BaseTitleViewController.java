package com.itheima31.jdmall.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;

/**
 * Created by Tony on 2016/10/23.
 */

public class BaseTitleViewController extends BaseController<String> implements View.OnClickListener {

    @InjectView(R.id.base_actionbar_iv_back)
    ImageView mBaseActionbarIvBack;

    @Override
    public View getView() {
        UIUtils.getHandler();
        View rootView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.base_actionbar, null);
        return rootView;
    }

    @Override
    public void refresHolderView(String data) {
        mBaseActionbarIvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
