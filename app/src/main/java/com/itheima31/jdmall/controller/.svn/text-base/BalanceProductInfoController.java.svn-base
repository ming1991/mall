package com.itheima31.jdmall.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.BalanceInfoBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

/**
 * 结算页面商品详情的视图
 */

public class BalanceProductInfoController extends BaseController<BalanceInfoBean.ProductListBean> {

    @InjectView(R.id.product_info_pic)
    ImageView mPic;
    @InjectView(R.id.product_info_des)
    TextView  mDes;
    @InjectView(R.id.product_info_color)
    TextView  mColor;
    @InjectView(R.id.product_info_size)
    TextView  mSize;
    @InjectView(R.id.product_info_price)
    TextView  mPrice;
    @InjectView(R.id.act_balance_product_num)
    TextView  mNum;

    @Override
    public View getView() {
        View root = View.inflate(UIUtils.getContext(), R.layout.product_info_layout, null);

        return root;
    }

    @Override
    public void refresHolderView(BalanceInfoBean.ProductListBean data) {
        Picasso.with(UIUtils.getContext()).load(Constants.URLS.BASEURL + data.product.pic).into(mPic);
        mDes.setText(data.product.name);
        mColor.setText(data.product.productProperty.get(0).k + ":" + data.product.productProperty.get(0).v);

        String price = UIUtils.getString(R.string.price);
        price = String.format(price, data.product.price + "");
        mPrice.setText(price);

        String productNum = UIUtils.getString(R.string.productNum);
        productNum = String.format(productNum, data.prodNum + "");
        mNum.setText(productNum);
    }

}
