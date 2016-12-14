package com.itheima31.jdmall;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dd.CircularProgressButton;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.fragment.SplashFragment;
import com.itheima31.jdmall.utils.BarUtils;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.taurusxi.guidebackgroundcoloranimation.library.ColorAnimationView;

public class SplashActivity extends AppCompatActivity {
    @InjectView(R.id.viewPager)
    ViewPager mViewPager;

    @InjectView(R.id.circularButton)
    CircularProgressButton mCircularButton;

    @InjectView(R.id.ColorAnimationView)
    ColorAnimationView mColorAnimationView;

    @InjectView(R.id.act_splash_fl)
    FrameLayout mFrameLayout;

    private List<View> mListViews;
    private int        mProgress;

    private MyViewPagerAdapter mAdapter;

    private String MSPLASH_ACTIVITY = "splash_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        BarUtils.hideStatusBar(this);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);

        boolean isRead = SPUtil.getBoolean(SplashActivity.this, MSPLASH_ACTIVITY, false);
        if (isRead) {

            //闪屏页面
            mFrameLayout.removeAllViews();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.act_splash_fl,new SplashFragment(),"splash");
            transaction.commit();

            ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(3000);
                }
            });


//            startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            finish();
        }

        initData();

        initEvent();
    }

    private void initData() {

        mListViews = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        mListViews.add(inflater.inflate(R.layout.splash_layout1, null));
        mListViews.add(inflater.inflate(R.layout.splash_layout2, null));
        mListViews.add(inflater.inflate(R.layout.splash_layout3, null));

        mCircularButton.setIndeterminateProgressMode(true);
        mCircularButton.setBackgroundColor(0xffE71834);
        mCircularButton.setStrokeColor(0xffE3D515);

    }

    private void initEvent() {

        mAdapter = new MyViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);

        mColorAnimationView.setmViewPager(mViewPager, mListViews.size(), 0xffDD6622, 0xff5D26C5, 0xffE71834);
        mColorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == mListViews.size() - 1) {
                    mCircularButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position != mListViews.size() - 1) {
                    mCircularButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @OnClick(R.id.circularButton)
    public void onClick() {
        mProgress = 0;
        SPUtil.putBoolean(SplashActivity.this, MSPLASH_ACTIVITY, true);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress++;
                mCircularButton.setProgress(mProgress);
                UIUtils.getHandler().postDelayed(this, 20);
                //                if (mProgress > 20 && mProgress < 30) {
                //                    mProgress += 50;
                //                }
                if (mCircularButton.getProgress() == 50) {
                    mCircularButton.setProgress(100);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SystemClock.sleep(500);
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();

                        }
                    }).start();

                }

            }
        }, 20);
    }

    class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mListViews == null ? 0 : mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);

            return mListViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mListViews.get(position));
        }
    }


}
