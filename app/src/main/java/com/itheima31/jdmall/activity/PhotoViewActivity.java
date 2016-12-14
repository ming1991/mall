package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotoViewActivity extends AppCompatActivity {


    private HorizontalInfiniteCycleViewPager mViewPager;

    private TextView mPagePosition;
    private ArrayList<String> mBigPicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);

        getSupportActionBar().hide();
        //获取跳转过来是数据
        init();

        initView();

        initData();

        initEvent();
    }

    private void initEvent() {
        PagerOnPageChangeListener pageChangeListener = new PagerOnPageChangeListener();
        mViewPager.setOnPageChangeListener(pageChangeListener);

       // mViewPager.setOnInfiniteCyclePageTransformListener();

    }

    class PagerOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            position = position%4;
            mPagePosition.setText(position+1+"/"+ mBigPicList.size());

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void init() {
        Intent intent = getIntent();

        mBigPicList = intent.getStringArrayListExtra("BigPicList");

    }

    private void initData() {
        mViewPager.setAdapter(mPagerAdapter);

        mPagePosition.setText(1+"/"+ mBigPicList.size());

    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mBigPicList == null ? 0 : mBigPicList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = new ImageView(PhotoViewActivity.this);

           view.setScaleType(ImageView.ScaleType.FIT_XY);
            String shortUrl = mBigPicList.get(position);
            String url = Constants.URLS.BASEURL + shortUrl;
            Picasso.with(UIUtils.getContext()).load(url).into(view);

            /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dip2px(200), UIUtils.dip2px(300));
            view.setLayoutParams(layoutParams);*/

            //设置点击事件
            PhotoViewOnClickListener viewOnClickListener = new PhotoViewOnClickListener();
            view.setOnClickListener(viewOnClickListener);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    class PhotoViewOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            finish();
        }
    }

    /*class PhotoViewOnClickListener implements PhotoViewAttacher.OnPhotoTapListener{

        @Override
        public void onPhotoTap(View view, float x, float y) {
            finish();

        }

        @Override
        public void onOutsidePhotoTap() {

        }
    }*/



    private void initView() {

        mViewPager = (HorizontalInfiniteCycleViewPager) findViewById(R.id.activity_photo_view_viewPager);
        mPagePosition = (TextView) findViewById(R.id.activity_photo_view_tv_pagePosition);

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setScrollDuration(2000);

        //infiniteCycleViewPager.setInterpolator(...); 指示器
        mViewPager.setMediumScaled(true);
        mViewPager.setMaxPageScale(0.8F);
        mViewPager.setMinPageScale(0.5F);
        mViewPager.setCenterPageScaleOffset(30.0F);
        mViewPager.setMinPageScaleOffset(5.0F);
        mViewPager.startAutoScroll(true);   //auto scroll
    }

}
