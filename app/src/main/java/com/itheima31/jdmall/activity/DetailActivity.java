package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.factory.DetailFragmentFactory;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    @InjectView(R.id.activity_detail_tabs)
    PagerSlidingTabStrip mActivityDetailTabs;
    @InjectView(R.id.activity_detail_viewPager)
    ViewPager mActivityDetailViewPager;
    @InjectView(R.id.activity_detail_iv_back)
    ImageView mActivityDetailIvBack;
    @InjectView(R.id.activity_detail_iv_more)
    ImageView mActivityDetailIvMore;

    private String[] mTitles = {"商品", "详情", "评论"};
    private int mPId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().hide();

        ButterKnife.inject(this);

        // TODO 获取跳转的商品ID
        init();

        initData();

        initEvent();


    }

    private void initEvent() {

        //TODO  触发加载 在BaseFragment 的onCreate里面 被调用

        //设置指示器的监听
        //mActivityDetailTabs.setOnPageChangeListener();

        //获取当前显示的fragment对象,当视图树布局好之后,再调用该方法  /

        //TODO  出现 空指针  .setOnFragmentProduct2CommentListener(product2CommentListener);

        /*mActivityDetailViewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //int position = mActivityDetailViewPager.getCurrentItem();
                BaseFragment fragment = DetailFragmentFactory.mSparseArray.get(0);
                if (fragment!=null){

                    FragmentProduct2CommentListener product2CommentListener = new FragmentProduct2CommentListener();
                    ((DetailProductFragment)fragment)
                            .mDetailProductCommentHolder
                            .setOnFragmentProduct2CommentListener(product2CommentListener);
                }

                mActivityDetailViewPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });*/

    }

 /*   class FragmentProduct2CommentListener implements DetailProductCommentHolder.OnFragmentProduct2CommentListener {

        @Override
        public void onFragmentProduct2Comment() {
            mActivityDetailViewPager.setCurrentItem(2,true);
        }
    }

*/

    private void initData() {

        DetailFragmentAdapter detailFragmentAdapter = new DetailFragmentAdapter(getSupportFragmentManager());

        mActivityDetailViewPager.setAdapter(detailFragmentAdapter);

        mActivityDetailTabs.setViewPager(mActivityDetailViewPager);

    }

    /**
     * 获取跳转过来的商品ID
     * */
    private void init() {

        Intent intent = getIntent();

        mPId = intent.getIntExtra("pId",1);

        //商品idpId 保存到本地
        SPUtil.putInt(UIUtils.getContext(),"pId",mPId);



    }

    @OnClick({R.id.activity_detail_iv_back, R.id.activity_detail_iv_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_detail_iv_back: //点击返回
                finish();

                break;
            case R.id.activity_detail_iv_more://
                Toast.makeText(UIUtils.getContext(), "没有更多", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    class DetailFragmentAdapter extends FragmentPagerAdapter {

        public DetailFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment =  DetailFragmentFactory.creatFragment(position);

            return fragment;
        }

        @Override
        public int getCount() {

            return mTitles == null ? 0 : mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mTitles[position];
        }
    }


    /**
     * 两个fragment之间的跳转接口
     * */
    private OnFragmentProduct2CommentListener onFragmentProduct2CommentListener;
    public interface OnFragmentProduct2CommentListener {

        void onFragmentProduct2Comment(ViewPager viewPager);
    }
    public void setOnFragmentProduct2CommentListener(OnFragmentProduct2CommentListener onFragmentProduct2CommentListener){
        this.onFragmentProduct2CommentListener = onFragmentProduct2CommentListener;
    }

    public void forSkip(){
        if(onFragmentProduct2CommentListener!=null){
            onFragmentProduct2CommentListener.onFragmentProduct2Comment(mActivityDetailViewPager);
        }
    }

}
