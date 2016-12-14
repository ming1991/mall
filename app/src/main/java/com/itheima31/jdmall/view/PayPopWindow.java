package com.itheima31.jdmall.view;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.itheima31.jdmall.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/29.
 */

public class PayPopWindow extends PopupWindow {
    @InjectView(R.id.pop_pay_shut)
    ImageView mShut;
    @InjectView(R.id.pop_pay_paynow)
    TextView  mPaynow;

    public PayPopWindow(final Activity context, View.OnClickListener itemsOnClick) {
        super(context);

        View root = View.inflate(context, R.layout.pop_pay_layout, null);
        ButterKnife.inject(this, root);
        mShut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mPaynow.setOnClickListener(itemsOnClick);

        setOutsideTouchable(true);
        setContentView(root);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置弹出窗体的背景
        setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        setAnimationStyle(R.style.pop_pay_anim);
        setBackgroundDrawable(new BitmapDrawable());

        //设置底下变成暗色
        backgroundAlpha(context, 0.7f);

        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(context, 1f);
            }
        });

        //设置虚拟按键不挡住popWindow
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
