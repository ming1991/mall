package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.bean.AddressListBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.fragment.AddressFragment;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 200;
    private FragmentTransaction mTransaction;
    private AddressFragment     mFragment;
    String[] userid = SPUtils.getString(Constants.LOGED_ACCOUNT);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        if (userid[1] == null) {
            Toast.makeText(UIUtils.getContext(), "您还未登陆", Toast.LENGTH_SHORT).show();
            finish();
        }

        mTransaction = getSupportFragmentManager().beginTransaction();
        mFragment = new AddressFragment();
        mTransaction.replace(R.id.act_address_manager_container, mFragment);
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAddressEvent(AddressListBean event) {
        finish();
    }


    @OnClick({R.id.act_address_manager_top_title_tv, R.id.act_address_new_address_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_address_manager_top_title_tv:
                finish();
                break;
            case R.id.act_address_new_address_btn:
                Intent intent = new Intent(this, AddressAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}