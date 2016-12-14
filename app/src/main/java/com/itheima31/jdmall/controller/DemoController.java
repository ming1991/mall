package com.itheima31.jdmall.controller;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.UIUtils;

/**
 * Created by Tony on 2016/10/23.
 *
 * 当做原来的Hoder 这个是例子Demo
 */

public class DemoController extends BaseController<String> {

    private TextView mTextView;

    @Override
    public View getView() {
        UIUtils.getHandler();
        mTextView = new TextView(UIUtils.getContext());

        return mTextView;
    }

    @Override
    public void refresHolderView(String data) {
        mTextView.setGravity(Gravity.CENTER);
        mTextView.setTextColor(Color.BLACK);
        mTextView.setText(data);
    }
}
