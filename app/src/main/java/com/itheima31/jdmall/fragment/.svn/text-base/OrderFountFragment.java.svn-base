package com.itheima31.jdmall.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.OrderActivity;
import com.itheima31.jdmall.activity.OrderDetailActivity;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.OrderBean;
import com.itheima31.jdmall.bean.OrderListBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.protocol.OrderProtocol;
import com.itheima31.jdmall.utils.CheckResData;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class OrderFountFragment extends BaseFragment {

    @InjectView(R.id.order_detail_iv)
    ImageView mOrderDetailIv;
    @InjectView(R.id.order_detail_id)
    TextView  mOrderDetailId;
    @InjectView(R.id.order_detail_price)
    TextView  mOrderDetailPrice;
    @InjectView(R.id.order_detail_time)
    TextView  mOrderDetailTime;
    @InjectView(R.id.order_detail_state)
    TextView  mOrderDetailState;
    private OrderListBean mOrderListBean;
    private List<OrderListBean> mListBeen = new ArrayList<>();
    private String[]      arr            = {"/images/product/detail/c1.jpg", "/images/product/detail/q16.jpg", "/images/product/detail/c3.jpg", "/images/product/detail/q1.jpg", "/images/product/detail/a1.jpg"};
    private View mView;

    @Override
    public LoadingPager.LoadedResultEnum initData() {
        try {
            OrderProtocol orderProtocol1 = new OrderProtocol("1");
            OrderBean orderBean1 = orderProtocol1.loadData(0);
            mListBeen.addAll(orderBean1.orderList);

            OrderProtocol orderProtocol2 = new OrderProtocol("2");
            OrderBean orderBean2 = orderProtocol2.loadData(0);
            mListBeen.addAll(orderBean2.orderList);

            OrderProtocol orderProtocol3 = new OrderProtocol("3");
            OrderBean orderBean3 = orderProtocol3.loadData(0);
            mListBeen.addAll(orderBean3.orderList);


            return CheckResData.checkResData(mListBeen);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    @Override
    public View initSuccessView() {
        mView = View.inflate(UIUtils.getContext(), R.layout.order_fount_item, null);
        ButterKnife.inject(this, mView);


        for (int i = 0; i < mListBeen.size(); i++) {
            OrderListBean orderListBean = mListBeen.get(i);
            if (orderListBean.orderId.equals(OrderActivity.mTrim)) {
                mOrderListBean = orderListBean;
                break;
            }
        }
        if (mOrderListBean!=null) {
            Picasso.with(UIUtils.getContext()).load(Constants.URLS.BASEURL + arr[new Random().nextInt(4)]).into(mOrderDetailIv);
            mOrderDetailId.setText("订单编号:" + mOrderListBean.orderId);
            String flag = mOrderListBean.flag;
            int mFlag = 0;
            if (flag != null) {
                mFlag = Integer.parseInt(flag);
            }
            switch (mFlag) {
                case 1:
                    mOrderDetailState.setText("状态:可修改");
                    break;
                case 2:
                    mOrderDetailState.setText("状态:不可修改");
                    break;
                case 3:
                    mOrderDetailState.setText("状态:已完成");
                    break;
            }
            mOrderDetailPrice.setText("订单总额:" + mOrderListBean.price);
            BigInteger bigInteger = new BigInteger(mOrderListBean.time);
            long l = bigInteger.longValue();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date(l));
            mOrderDetailTime.setText("时间:" + format);
        } else {
            Toast.makeText(UIUtils.getContext(), "请输入正确的订单号", Toast.LENGTH_SHORT).show();
        }

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UIUtils.getContext(), OrderDetailActivity.class);
                intent.putExtra("isGone", false);
                intent.putExtra("orderId", mOrderListBean.orderId);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        return mView;
    }
}
