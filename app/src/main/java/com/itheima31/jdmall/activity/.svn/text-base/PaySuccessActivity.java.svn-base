package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.itheima31.jdmall.MainActivity;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PaySuccessActivity extends AppCompatActivity {

    @InjectView(R.id.shop_more)
    TextView mShopMore;
    @InjectView(R.id.look_order)
    TextView mLookOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.shop_more, R.id.look_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_more:
                startActivity(new Intent(UIUtils.getContext(), MainActivity.class));
                finish();
                break;
            case R.id.look_order:
                startActivity(new Intent(UIUtils.getContext(), OrderActivity.class));
                finish();
                break;
        }
    }
}
