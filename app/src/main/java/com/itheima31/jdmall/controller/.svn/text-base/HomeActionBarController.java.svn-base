package com.itheima31.jdmall.controller;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.DemoActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;

/**
 * Created by Tony on 2016/10/24.
 */

public class HomeActionBarController extends BaseController implements View.OnClickListener {
    @InjectView(R.id.actionbar_home_layout_scan)
    ImageView mActionbarHomeLayoutScan;
    @InjectView(R.id.actionbar_home_layout_edit_text)
    EditText mActionbarHomeLayoutEditText;
    @InjectView(R.id.actionbar_message_iv)
    ImageView mActionbarHomeLayoutMessage;

    @Override
    public View getView() {
        View rootView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.actionbar_home_layout, null);
        return rootView;
    }
    @Override
    public void refresHolderView(Object data) {
        mActionbarHomeLayoutMessage.setClickable(true);
        mActionbarHomeLayoutMessage.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(UIUtils.getContext(), DemoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(intent);
        Toast.makeText(UIUtils.getContext(), "点了", Toast.LENGTH_SHORT).show();
    }
}
