package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.LimitbuyBeans;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.ProductlistProtocol;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.view.MyTimerView;
import com.itheima31.jdmall.view.RecycleViewDivider;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class LimitBuyActivity extends BaseActivity {


    @InjectView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @InjectView(R.id.limitbuys_vp)
    ViewPager            mLimitbuysVp;

    private LimitbuyBeans                       mBean;
    private List<LimitbuyBeans.ProductListBean> mProductList;
    private List<String> times = new ArrayList<>();

    private ProductlistProtocol mProductlistProtocol;
    private RecyclerView        mRecyclerView;
    private LinearLayoutManager mLayoutManager;


    public LimitBuyActivity() {
        //获得当前的小时
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int mHour = mCalendar.get(Calendar.HOUR);
        int temp = mHour;
        for (int i = mHour; i <= 6 + mHour; i++) {
            if (temp >= 24) {
                temp = temp - 24;
            }
            if (temp < 10) {
                times.add("0" + temp + ":00");
            } else {
                times.add(temp + ":00");
            }
            temp = temp + 2;
        }
    }

    public void onItemClick(View view) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
        Intent intent = new Intent(LimitBuyActivity.this, DetailActivity.class);
        intent.putExtra("pId", mProductList.get(childAdapterPosition).getId());
        startActivity(intent);
    }

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {

        mProductlistProtocol = new ProductlistProtocol();
        try {

            mBean = mProductlistProtocol.loadData(1);
            mProductList = mBean.getProductList();
            return ResultUtils.checkResData(mProductList);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }

    }

    @Override
    public View onInitSuccessView() {
        View view = View.inflate(this, R.layout.activity_limitbuys, null);
        ButterKnife.inject(this, view);
        final LimitBuyAdapter adapter = new LimitBuyAdapter();
        mLimitbuysVp.setAdapter(adapter);

//        mLimitbuysVp.setOffscreenPageLimit(times.size());

        mTabs.setViewPager(mLimitbuysVp);
        //页面改变的设置监听
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mBean = mProductlistProtocol.loadData(position);
                            mProductList = mBean.getProductList();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        UIUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }


    class LimitBuyAdapter extends PagerAdapter {


        private MyRecyclerAdapter mAdapter;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            mRecyclerView = new RecyclerView(LimitBuyActivity.this);

            mLayoutManager = new LinearLayoutManager(LimitBuyActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new MyRecyclerAdapter();
            mAdapter.jishu=position*3600*2;
            //设置分隔线
            mRecyclerView.addItemDecoration(new RecycleViewDivider(LimitBuyActivity.this, LinearLayoutManager.VERTICAL));

            //设置动画
            AlphaInAnimationAdapter alphaAdapter = new AlphaInAnimationAdapter(mAdapter);
            mRecyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));
            container.addView(mRecyclerView);
            return mRecyclerView;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return times.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return times.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

        public int jishu=0;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = View.inflate(LimitBuyActivity.this, R.layout.item_buying_commodity, null);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mCostPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//-------------------------------------------------------------------------------------------------------
            LimitbuyBeans.ProductListBean productListBean = mProductList.get(position);
            String url = Constants.URLS.BASEURL + productListBean.pic;
            Picasso.with(UIUtils.getContext()).load(url).into(holder.mImageView);
            holder.mCommodityName.setText("韩版时尚酷炫潮流" + productListBean.name);
            holder.mCostPrice.setText("¥ " + productListBean.price);
            holder.mCostPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            holder.mBargainPrice.setText("¥ " + productListBean.limitPrice);
//            mViewHolder.mTimerView.setTimes(productListBean.leftTime - 7800 * 2 - 360-20);
            holder.mTimerView.setTimes(new Random().nextInt(1000)+jishu);
            holder.mTimerView.setOnTimerZeroListener(new MyTimerView.OnTimerZeroListener() {
                @Override
                public void onTimeOut() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            holder.mTimerView.setVisibility(View.INVISIBLE);
                            holder.mPanicBuying.setBackgroundResource(R.drawable.shape_yuanjiao);

//                            for (LimitbuyBeans.ProductListBean productListBean : mProductList) {
//                                productListBean.setLeftTime(0);
//                            }

                            holder.mPanicBuying.setText("马上抢");
                        }
                    });
                }
            });

            if (holder.mTimerView.getTimes() < 1) {
                holder.mTimerView.setVisibility(View.INVISIBLE);
                holder.mPanicBuying.setBackgroundResource(R.drawable.shape_yuanjiao);
                holder.mPanicBuying.setText("马上抢");
            } else {
                holder.mTimerView.setVisibility(View.VISIBLE);
                holder.mPanicBuying.setBackgroundResource(R.drawable.shape_yuanjiao2);
                holder.mPanicBuying.setText("未开始");
            }

        }


        @Override
        public int getItemCount() {
            return mProductList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView   mImageView;
            TextView    mCommodityName;
            TextView    mCostPrice;
            TextView    mBargainPrice;
            TextView    mPanicBuying;
            MyTimerView mTimerView;
//            View mLine;


            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.buying_commodity_image);
                mCommodityName = (TextView) itemView.findViewById(R.id.buying_commodity_name_tv);
                mCostPrice = (TextView) itemView.findViewById(R.id.cost_price_tv);
                mBargainPrice = (TextView) itemView.findViewById(R.id.bargain_price_tv);
                mPanicBuying = (TextView) itemView.findViewById(R.id.panic_buying_tv);
                mTimerView = (MyTimerView) itemView.findViewById(R.id.item_buying_timer);
            }

            @Override
            public void onClick(View v) {

            }
        }
    }

}
