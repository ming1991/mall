package com.itheima31.jdmall.fragment;


import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.BasePagerAdapter;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.ClassifyBean;
import com.itheima31.jdmall.net.HttpManager;
import com.itheima31.jdmall.net.HttpOnNextListener;
import com.itheima31.jdmall.net.ParseClassifyData;
import com.itheima31.jdmall.net.api.ClassifyApi;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.widgets.ClassifyRecylistView;
import com.itheima31.jdmall.widgets.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tony on 2016/10/21.
 */

public class ClassifyFragment extends BaseFragment {

    @InjectView(R.id.frg_classify_recyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.frg_classify_viewPager)
    ViewPager mViewPager;
    @InjectView(R.id.frg_classify_recyclerView_level2)
    RecyclerView mLevel2RecyclerView;
    @InjectView(R.id.frg_classify_level2_viewPager)
    ViewPager mLevel2ViewPager;
    @InjectView(R.id.frg_classify_frameLayout)
    FrameLayout mFrameLayout;

    private List<ClassifyBean.CategoryBean> mDatas;
    private List<ClassifyBean.CategoryBean> mlevel2Datas;
    private boolean isError = false;

    @Override
    public LoadingPager.LoadedResultEnum initData() {

//        ClassifyPorotocol porotocol = new ClassifyPorotocol();
//        try {
//            mDatas = porotocol.loadData(0);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        HttpManager.getInstance().doHttpDeal(new ClassifyApi(new HttpOnNextListener<ClassifyBean>() {
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                LogUtils.e("error:" + e.toString());
//            }
//
//            @Override
//            public void onNext(ClassifyBean bean) {
//                LogUtils.e("onNext:" + mDatas.get(0).name);
//                mDatas =  ParseClassifyData.parseData(bean);

//
//        }
//        }));

        HttpManager.getInstance()
                .doHttpDeal(new ClassifyApi(new HttpOnNextListener<ClassifyBean>() {
                    @Override
                    public void onNext(ClassifyBean bean) {
                        mDatas = ParseClassifyData.parseData(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        isError = true;

                    }
                }));

        if (isError){
            isError = false;
            return LoadingPager.LoadedResultEnum.ERROR;
        }

        //遍历出二级标题
        mlevel2Datas = new ArrayList<>();

        for (int i = 0; i < mDatas.size(); i++) {
            for (int j = 0; j < mDatas.get(i).level2List.size(); j++) {
                mlevel2Datas.add(mDatas.get(i).level2List.get(j));
            }
        }


        return ResultUtils.checkResData(mDatas);
    }

    @Override
    public View initSuccessView() {
        View rootView = LayoutInflater.from(mActivity).inflate(R.layout.fragment_classify, null);
        ButterKnife.inject(this, rootView);

        // 一级目录
        mRecyclerView.setAdapter(new ClassifyRcyViewAdapter(mDatas));
        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 1));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        //二级目录
        mLevel2RecyclerView.setAdapter(new ClassifyRcyViewAdapter(mlevel2Datas));
        mLevel2RecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 1));

        //一级pager
        mViewPager.setAdapter(new ClassifyPagerAdapter(mDatas));
//        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setWillNotCacheDrawing(true);
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        //二级pager
        mLevel2ViewPager.setAdapter(new ClassifyPagerAdapter2(mlevel2Datas));
        mLevel2ViewPager.setWillNotCacheDrawing(true);
        mLevel2ViewPager.setPageTransformer(true, new ZoomOutPageTransformer());


        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private View oldBtn;
    private boolean isFirst = true;

    class ClassifyRcyViewAdapter extends RecyclerView.Adapter<ClassifyRcyViewHolder> {


        private final List<ClassifyBean.CategoryBean> mDatas1;

        public ClassifyRcyViewAdapter(List<ClassifyBean.CategoryBean> datas) {
            mDatas1 = datas;
        }

        @Override
        public ClassifyRcyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = null;
            if (mDatas1.size() == mDatas.size()) {
                inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_classify_indicator, parent,false);
            } else {
                inflate = LayoutInflater.from(mActivity).inflate(R.layout.item_classify_indicator2, parent,false);
            }
            ClassifyRcyViewHolder holder = new ClassifyRcyViewHolder(inflate);

            return holder;
        }

        @Override
        public void onBindViewHolder(ClassifyRcyViewHolder holder, final int position) {

            if (isFirst){
                isFirst = false;
                oldBtn = holder.mTvIndicator;
                oldBtn.setBackgroundColor(getResources().getColor(R.color.mineColorBackground));
            }

            holder.mTvIndicator.setText(mDatas1.get(position).name);

            if (mDatas1.size() == mDatas.size()) {
                holder.mTvIndicator.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackgroundColor(getResources().getColor(R.color.mineColorBackground));
                        oldBtn.setBackgroundColor(Color.WHITE);
                        oldBtn = v;

                        mViewPager.setCurrentItem(position);
                        mViewPager.setVisibility(View.VISIBLE);
                        mLevel2ViewPager.setVisibility(View.INVISIBLE);
                        mLevel2RecyclerView.smoothScrollToPosition(1);
                    }
                });
            } else {
                holder.mTvIndicator.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setBackgroundColor(getResources().getColor(R.color.mineColorBackground));
                        oldBtn.setBackgroundColor(Color.WHITE);
                        oldBtn = v;


                        mLevel2ViewPager.setCurrentItem(position);
                        mLevel2ViewPager.setVisibility(View.VISIBLE);
                        mViewPager.setVisibility(View.INVISIBLE);
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return mDatas1.size();
        }
    }

    class ClassifyRcyViewHolder extends RecyclerView.ViewHolder {

        private final View mItemView;
        @InjectView(R.id.tv_indicator)
        TextView mTvIndicator;

        public ClassifyRcyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mItemView = itemView;
        }
    }

    class ClassifyPagerAdapter extends BasePagerAdapter {

        public ClassifyPagerAdapter(List datas) {
            super(datas);
        }

        @Override
        public View getView(int position) {
            ClassifyRecylistView rcyView = new ClassifyRecylistView(mActivity, mDatas.get(position), true);
            return rcyView;
        }

    }

    class ClassifyPagerAdapter2 extends BasePagerAdapter {

        public ClassifyPagerAdapter2(List datas) {
            super(datas);
        }

        @Override
        public View getView(int position) {
            if (mDatas == mlevel2Datas) {
            }
            ClassifyRecylistView rcyView = new ClassifyRecylistView(mActivity, mlevel2Datas.get(position), false);
            return rcyView;

        }
    }
}