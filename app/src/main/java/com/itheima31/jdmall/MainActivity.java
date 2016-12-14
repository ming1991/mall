package com.itheima31.jdmall;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.event.EventJumpFragment;
import com.itheima31.jdmall.event.EventShoppingCartSizeChange;
import com.itheima31.jdmall.factory.FragmentFactory;
import com.itheima31.jdmall.factory.TitleViewFactory;
import com.jauker.widget.BadgeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @InjectView(R.id.act_mian_fl_title)
    FrameLayout mActMianFlTitle;
    @InjectView(R.id.act_mian_fl_content)
    FrameLayout mActMianFlContent;
    @InjectView(R.id.act_mian_rb_home)
    RadioButton mActMianRbHome;
    @InjectView(R.id.act_mian_rb_classify)
    RadioButton mActMianRbClassify;
    @InjectView(R.id.act_mian_rb_discover)
    RadioButton mActMianRbFind;
    @InjectView(R.id.act_mian_rb_shopping_cart)
    RadioButton mActMianRbShoppingCart;
    @InjectView(R.id.act_mian_rb_mine)
    RadioButton mActMianRbMine;
    @InjectView(R.id.act_mian_radio_group)
    RadioGroup mActMianRadioGroup;
    @InjectView(R.id.activity_main)
    LinearLayout mActivityMain;

    @InjectView(R.id.btn_msg)
    Button mBtnMsg;

    private int mJump2FragmentId = 0;

    private BadgeView mBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.inject(this);

        EventBus.getDefault().register(this);
        initEvent();

        int data = getIntent().getIntExtra("xxxx", 0);

        Button button = new Button(this);
        //LayoutInflater.from(this).inflate()
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });



    }

    private void initEvent() {
        mActMianRadioGroup.setOnCheckedChangeListener(this);
        mActMianRadioGroup.check(R.id.act_mian_rb_home);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        //加载Fragment;
        BaseFragment fragment = FragmentFactory.getFragment(this, checkedId);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.act_mian_fl_content, fragment);
        //回退栈  加入的话 点back会回到上个Fragment
//        transaction.addToBackStack(null);
        transaction.commit();
        //加载头部视图
        mActMianFlTitle.removeAllViews();

        View view = TitleViewFactory.getTitleView(this, checkedId);
        ViewGroup parent = (ViewGroup) (view.getParent());

        //解决重新启动bug
        if (parent != null) {
            parent.removeView(view);
        }

        mActMianFlTitle.addView(view);

        //  小圆点
        mBadge = new BadgeView(this);
        mBadge.setTargetView(mBtnMsg);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void jumpFragment(EventJumpFragment event) {
        mJump2FragmentId = event.fragmentId;
    }

    public void changeFragment(int checkId){
        mActMianRadioGroup.check(checkId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShoppingCartSizeChange(EventShoppingCartSizeChange event) {
        if (mBtnMsg != null) {
            ViewGroup parent = (ViewGroup)mBadge.getParent();

            if (event.size == 0) {
                parent.setVisibility(View.GONE);
            } else {
                parent.setVisibility(View.VISIBLE);
                mBadge.setBadgeCount(event.size);
            }
        }

    }


    //处理跳转fragment事件
    @Override
    protected void onResume() {
        super.onResume();
        if (mJump2FragmentId != 0) {
            mActMianRadioGroup.check(R.id.act_mian_rb_shopping_cart);
            mJump2FragmentId = 0;
//            FragmentFactory.getFragment(this,R.id.act_mian_rb_shopping_cart).mLoadingPager.triggerLoadData();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
