package com.itheima31.jdmall.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.view.PayPopWindow;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PayActivity extends BaseActivity {

    @InjectView(R.id.act_pay_headback)
    ImageView      mHeadback;
    @InjectView(R.id.act_pay_order)
    TextView       mOrder;
    @InjectView(R.id.act_pay_jingdong_container)
    RelativeLayout mPayJingdong;
    @InjectView(R.id.act_pay_weixin_container)
    RelativeLayout mPayWeixin;
    @InjectView(R.id.act_pay_fast_container)
    RelativeLayout mPayFast;
    @InjectView(R.id.act_pay_apple_container)
    RelativeLayout mPayApple;
    private AlertDialog.Builder mBuilder;
    private PayPopWindow        mPop;
    private Activity            mActivity;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
//        SystemClock.sleep(300);
        mActivity = this;
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View onInitSuccessView() {
        View root = View.inflate(UIUtils.getContext(), R.layout.activity_pay, null);
        ButterKnife.inject(this, root);
        return root;
    }

    @OnClick({R.id.act_pay_headback, R.id.act_pay_order, R.id.act_pay_jingdong_container, R.id.act_pay_weixin_container, R.id.act_pay_alipay_container, R.id.act_pay_fast_container, R.id.act_pay_apple_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_pay_headback:
                showCanceldialog();
                break;
            case R.id.act_pay_order:
                startActivity(new Intent(UIUtils.getContext(), OrderActivity.class));
                finish();
                break;
            case R.id.act_pay_jingdong_container:
                go2Pay();
                break;
            case R.id.act_pay_weixin_container:
                go2Pay();
                break;
            case R.id.act_pay_alipay_container:
                go2Pay();
                break;
            case R.id.act_pay_fast_container:
                go2Pay();
                break;
            case R.id.act_pay_apple_container:
                go2Pay();
                break;
        }
    }

    private void go2Pay() {
        if (mPop == null) {
            mPop = new PayPopWindow(this, itemsOnclick);
        }
        mPop.backgroundAlpha(mActivity, 0.7f);
        mPop.showAtLocation(findViewById(R.id.activity_pay), Gravity.BOTTOM, 0, 0);
    }

    private View.OnClickListener itemsOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mPop.dismiss();
            switch (v.getId()) {
                case R.id.pop_pay_paynow:
                    startActivity(new Intent(UIUtils.getContext(), PaySuccessActivity.class));
                    finish();
                    Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

    private void showCanceldialog() {
        if (mBuilder == null) {
            mBuilder = new AlertDialog.Builder(this);
            mBuilder.setMessage("超过支付失效后订单将被取消,请尽快完成支付");

            mBuilder.setTitle("确认要离开收银台吗？");

            mBuilder.setPositiveButton("确认离开", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            });

            mBuilder.setNegativeButton("继续支付", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        mBuilder.create().show();
    }

    @Override
    public void onBackPressed() {
        showCanceldialog();
    }
}
