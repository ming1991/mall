package com.itheima31.jdmall.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.fragment.OrderCancelFragment;
import com.itheima31.jdmall.fragment.OrderFountFragment;
import com.itheima31.jdmall.fragment.OrderMonthAfterFragment;
import com.itheima31.jdmall.fragment.OrderMonthAgoFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OrderActivity extends FragmentActivity implements View.OnClickListener {
    public static int order = 0;
    @InjectView(R.id.order_tv_order)
    TextView       mOrderTvOrder;
    @InjectView(R.id.order_relativeLayout)
    RelativeLayout mOrderRelativeLayout;
    @InjectView(R.id.order_month_ago_fl)
    FrameLayout    mOrderMonthAgoFl;
    @InjectView(R.id.order_iv_show)
    ImageView      mOrderIvShow;
    @InjectView(R.id.order_iv_back)
    ImageView      mOrderIvBack;
    @InjectView(R.id.order_iv_masage)
    ImageView      mOrderIvMasage;
    @InjectView(R.id.iv_search)
    ImageView      mIvSearch;
    private       PopupWindow             mPopupWindow;
    private       View                    mView;
    private       OrderMonthAgoFragment   mOrderMonthAgoFragment;
    private       OrderMonthAfterFragment mAfterFragment;
    private       OrderCancelFragment     mOrderCancelFragment;
    private       EditText                fount_et;
    public static String                  mTrim;
    private       OrderFountFragment      mOrderFountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.inject(this);

        mOrderCancelFragment = new OrderCancelFragment();
        mOrderMonthAgoFragment = new OrderMonthAgoFragment();
        OrderDetailActivity.addObserver(mOrderMonthAgoFragment);
        mAfterFragment = new OrderMonthAfterFragment();
        OrderDetailActivity.addObserver(mAfterFragment);

    }


    private View initView() {
        return View.inflate(this, R.layout.order_popuwindow, null);
    }

    private void initEvent() {
        mView.findViewById(R.id.order_one_month).setOnClickListener(this);
        mView.findViewById(R.id.order_now_month).setOnClickListener(this);
        mView.findViewById(R.id.order_cancle).setOnClickListener(this);

        fount_et = (EditText) mView.findViewById(R.id.order_fount_et);
        mView.findViewById(R.id.order_fount).setOnClickListener(this);
    }

    protected void showPopupWindow() {
        mView = initView();
        if (mPopupWindow == null) {
            int width = mOrderRelativeLayout.getWidth();
            int height = mOrderRelativeLayout.getHeight() * 2;
            mPopupWindow = new PopupWindow(width, height);
            initEvent();
            mPopupWindow.setContentView(mView);

            mPopupWindow.setOutsideTouchable(true);
            //设置背景之后，Popupwindow内部才会去处理touch事件
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            //让popupwindow可获取焦点
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    ObjectAnimator.ofFloat(mOrderIvShow, "rotation", 180, 0).start();
                }
            });
        }
        //弹出popupwindow
        mPopupWindow.showAsDropDown(mOrderRelativeLayout);
    }


    @OnClick({R.id.order_iv_back, R.id.order_tv_order, R.id.order_iv_masage, R.id.iv_search})
    public void onClick(View v) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.order_one_month:
                transaction.replace(R.id.order_month_ago_fl, mOrderMonthAgoFragment, "OrderMonthAgoFragment");
                break;
            case R.id.order_now_month:
                transaction.replace(R.id.order_month_ago_fl, mAfterFragment, "OrderMonthAfterFragment");
                break;
            case R.id.order_cancle:
                transaction.replace(R.id.order_month_ago_fl, mOrderCancelFragment, "OrderCancelFragment");
                break;
            case R.id.order_iv_back:
                finish();
                break;
            case R.id.order_tv_order:
                ObjectAnimator.ofFloat(mOrderIvShow, "rotation", 0, 180).start();
                showPopupWindow();
                break;
            case R.id.order_iv_masage:
                startActivity(new Intent(this, MessageCenterActivity.class));
                break;
            case R.id.order_fount:
                mTrim = fount_et.getText().toString().trim();
                if (mTrim.isEmpty()) {
                    Toast.makeText(this, "请输入订单号", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mOrderFountFragment = new OrderFountFragment();
                    transaction.replace(R.id.order_month_ago_fl, mOrderFountFragment, "OrderFountFragment");
                }
                break;
        }
        transaction.commit();
    }
}
