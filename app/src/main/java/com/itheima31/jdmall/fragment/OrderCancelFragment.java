package com.itheima31.jdmall.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.OrderDetailActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.base.MyBaseAdapter;
import com.itheima31.jdmall.bean.OrderBean;
import com.itheima31.jdmall.bean.OrderListBean;
import com.itheima31.jdmall.controller.OrderCancelController;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.OrderProtocol;
import com.itheima31.jdmall.utils.UIUtils;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.itheima31.jdmall.conf.Constants.mOrderList;


public class OrderCancelFragment extends BaseFragment {

    private CircleRefreshLayout mRefreshLayout;
    private ListView listview;
    private MyAdapter myAdapter;
    private List<OrderListBean> myOrderListBean = new ArrayList<>();
    @Override
    public LoadingPager.LoadedResultEnum initData() {
        OrderProtocol orderProtocol = new OrderProtocol("3");
        try {
            OrderBean orderBean = orderProtocol.loadData(0);
            LoadingPager.LoadedResultEnum state = checkResData(orderBean);

            List<OrderListBean> orderList = orderBean.orderList;
            mOrderList = orderList;
            return state;
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    @Override
    public View initSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.order_month_ago, null);
        mRefreshLayout = (CircleRefreshLayout) view.findViewById(R.id.refresh_layout);

        listview = (ListView) view.findViewById(R.id.order_month_ago_list);

        myAdapter = new MyAdapter(mOrderList, listview);
        listview.setAdapter(myAdapter);

        mRefreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                            @Override
                            public void run() {
                                SystemClock.sleep(2500);
                                UIUtils.getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mLoadingPager.triggerLoadData(true);
                                        mLoadingPager.getChildAt(0).setVisibility(View.GONE);
                                        Toast.makeText(UIUtils.getContext(), "刷新成功", Toast.LENGTH_LONG).show();
                                    }
                                });
                                mRefreshLayout.finishRefreshing();
                            }
                        });
                    }
                    @Override
                    public void completeRefresh() {

                    }
                });
            listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    dialog(position);
                    return true;
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
            return new OrderCancelController();
        }

        //点击事件
        @Override
        public void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {
            OrderListBean orderListBean = mOrderList.get(position);
            Intent intent = new Intent(UIUtils.getContext(), OrderDetailActivity.class);
            intent.putExtra("isGone", false);
            intent.putExtra("position", position);
            intent.putExtra("orderId", orderListBean.orderId);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setMessage("确认删除订单吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                myOrderListBean.add(mOrderList.get(position));
                mOrderList.remove(position);
                myAdapter.notifyDataSetChanged();
                Toast.makeText(UIUtils.getContext(),"删除成功",Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
