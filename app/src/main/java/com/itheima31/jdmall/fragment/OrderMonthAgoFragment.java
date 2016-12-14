package com.itheima31.jdmall.fragment;

import android.support.annotation.NonNull;
import android.view.View;

import com.google.gson.Gson;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.OrderActivity;
import com.itheima31.jdmall.activity.OrderDetailActivity;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.CancelOrderProtocol;
import com.itheima31.jdmall.protocol.OrderProtocol;
import com.itheima31.jdmall.utils.UIUtils;

import java.io.IOException;


public class OrderMonthAgoFragment extends OrderFragment implements OrderDetailActivity.OrderObserver {
    Gson gson = new Gson();
    private CancelOrderProtocol mCancelOrderProtocol;

    @NonNull
    @Override
    public OrderProtocol getOrderProtocol() {
        return new OrderProtocol("2");
    }

    //订单列表的请求方式
    @Override
    public int getInt() {
        return 1;
    }
    //加载视图
    @Override
    public View getInflate() {
        return View.inflate(UIUtils.getContext(), R.layout.order_month_ago, null);
    }

    //接收观察者提供的消息
    @Override
    public void onOederInfoChanged(final int order) {
        if (OrderActivity.order == 1) {
            mCancelOrderProtocol = new CancelOrderProtocol();

            ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        mCancelOrderProtocol.loadData(0);
                        mOneMonthAgo.remove(order);
                        UIUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
