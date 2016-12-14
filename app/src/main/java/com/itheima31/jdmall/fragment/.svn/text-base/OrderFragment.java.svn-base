package com.itheima31.jdmall.fragment;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.OrderActivity;
import com.itheima31.jdmall.activity.OrderDetailActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.base.MyBaseAdapter;
import com.itheima31.jdmall.bean.OrderBean;
import com.itheima31.jdmall.bean.OrderListBean;
import com.itheima31.jdmall.controller.OrderMonthAgoController;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.OrderProtocol;
import com.itheima31.jdmall.utils.UIUtils;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderFragment extends BaseFragment {
    public static final String              TAG    = "OrderFragment";
    public static       List<OrderListBean> myList = new ArrayList<>();
    public  List<OrderListBean> mOneMonthAgo;
    public  MyAdapter           mMyAdapter;
    private CircleRefreshLayout mRefreshLayout;
    public static String orderId;

    @Override
    public LoadingPager.LoadedResultEnum initData() {
        OrderProtocol orderProtocol = getOrderProtocol();
        try {
            OrderBean orderBean = orderProtocol.loadData(0);
            LoadingPager.LoadedResultEnum state = checkResData(orderBean);
            if (state != LoadingPager.LoadedResultEnum.SUCCESS) {
                return state;
            }
            state = checkResData(orderBean.orderList);
            if (state != LoadingPager.LoadedResultEnum.SUCCESS) {
                return state;
            }
            List<OrderListBean> OrderList = orderBean.orderList;
            mOneMonthAgo = OrderList;
            return state;
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    @Override
    public View initSuccessView() {
        View view = getInflate();
        mRefreshLayout = (CircleRefreshLayout) view.findViewById(R.id.refresh_layout);
        ListView listview = (ListView) view.findViewById(R.id.order_month_ago_list);
        mMyAdapter = new MyAdapter(mOneMonthAgo, listview);
        listview.setAdapter(mMyAdapter);

        mRefreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                            @Override
                            public void run() {
                                SystemClock.sleep(2000);
                                UIUtils.getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mLoadingPager.triggerLoadData(true);
                                        mLoadingPager.getChildAt(0).setVisibility(View.VISIBLE);
                                        Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
                                    }
                                });
                                mRefreshLayout.finishRefreshing();
                            }
                        });
                    }
                    @Override
                    public void completeRefresh() {
                        //TODO
                    }
                });
        return view;
    }


    class MyAdapter extends MyBaseAdapter<OrderListBean> {
        public MyAdapter(List<OrderListBean> datas, ListView listView) {
            super(datas, listView);

        }

        @NonNull
        @Override
        public BaseController getSpecialbaseHolder(int position) {
            return new OrderMonthAgoController();
        }

        //点击事件
        @Override
        public void onNormalItemClick(AdapterView<?> parent, View view, final int position, long id) {
            OrderActivity.order = getInt();
            OrderListBean orderListBean = mOneMonthAgo.get(position);
            Intent intent = new Intent(UIUtils.getContext(), OrderDetailActivity.class);
            intent.putExtra("isGone", true);
            intent.putExtra("position", position);
            intent.putExtra("orderId", orderListBean.orderId);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    //url中的type
    @NonNull
    public abstract OrderProtocol getOrderProtocol();

    //区分取消订单点击事件:一个月前订单与进一个月订单
    public abstract int getInt();

    //添加成功视图
    public abstract View getInflate();
}
