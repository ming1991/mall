package com.itheima31.jdmall.controller;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.OrderListBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.InjectView;

/**
 * Created by ${林仁迪} on 2016/10/24.
 * 描述:
 */
public class OrderMonthAgoController extends BaseController<OrderListBean> {
    public static final String TAG = "OrderMonthAgoController";
    @InjectView(R.id.order_detail_id)
    TextView  mOrderDetailId;
    @InjectView(R.id.order_detail_price)
    TextView  mOrderDetailTotal;
    @InjectView(R.id.order_detail_state)
    TextView  mOrderDetailState;
    @InjectView(R.id.order_detail_time)
    TextView  mOrderDetailTime;
    @InjectView(R.id.order_detail_iv)
    ImageView mOrderDetailIv;
    private String[] arr = {"/images/product/detail/c1.jpg", "/images/product/detail/q16.jpg", "/images/product/detail/c3.jpg", "/images/product/detail/q1.jpg", "/images/product/detail/a1.jpg"};
    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.order_month_ago_list_item, null);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void refresHolderView(OrderListBean data) {
        Picasso.with(UIUtils.getContext()).load(Constants.URLS.BASEURL+arr[new Random().nextInt(4)]).into(mOrderDetailIv);
        mOrderDetailId.setText("订单编号:" + data.orderId);
        mOrderDetailState.setText("订单总额:" + data.price);

        BigInteger bigInteger = new BigInteger(data.time);
        long l = bigInteger.longValue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date(l));

        mOrderDetailTime.setText("时间:" + format);
        String fla = data.flag;
        int mFlag = Integer.parseInt(fla);
        switch (mFlag) {
            case 1:
                mOrderDetailTotal.setText("状态:可修改");
                break;
            case 2:
                mOrderDetailTotal.setText("状态:不可修改");
                break;
            case 3:
                mOrderDetailTotal.setText("状态:已完成");
                break;
        }


    }

}
