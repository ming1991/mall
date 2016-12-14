package com.itheima31.jdmall.controller;

import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;
import cn.iwgang.countdownview.CountdownView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 16:54
 * 描述：    TODO
 */
public class DetailProductDesHolder extends BaseController<DetailProductBean> {
    @InjectView(R.id.item_detail_product_des_tv_newPrice)
    TextView mItemDetailProductDesTvNewPrice;
    @InjectView(R.id.item_detail_product_des_tv_oldPrice)
    TextView mItemDetailProductDesTvOldPrice;
    @InjectView(R.id.item_detail_product_des_ratingBar)
    RatingBar mItemDetailProductDesRatingBar;
    @InjectView(R.id.item_detail_product_des_tv_name)
    TextView mItemDetailProductDesTvName;
    @InjectView(R.id.item_detail_product_des_tv_inventoryArea)
    TextView mItemDetailProductDesTvInventoryArea;
    @InjectView(R.id.item_detail_product_des_rl_container_normal)
    RelativeLayout mItemDetailProductDesRlContainerNormal;
    @InjectView(R.id.item_detail_product_des_tv_limitPrice)
    TextView mItemDetailProductDesTvLimitPrice;
    @InjectView(R.id.item_detail_product_des_tv_lessPrice)
    TextView mItemDetailProductDesTvLessPrice;
    @InjectView(R.id.item_detail_product_des_tv_leftTime)
    TextView mItemDetailProductDesTvLeftTime;
    @InjectView(R.id.item_detail_product_des_countdownView_time)
    CountdownView mItemDetailProductDesCountdownViewTime;
    @InjectView(R.id.item_detail_product_des_ll_container_limit)
    LinearLayout mItemDetailProductDesLlContainerLimit;

    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_product_des, null);
        return view;
    }

    @Override
    public void refresHolderView(DetailProductBean data) {
        int price = data.product.price;
        int marketPrice = data.product.marketPrice;
        float score = data.product.score;
        String name = data.product.name;
        String inventoryArea = data.product.inventoryArea;
        int limitPrice = data.product.limitPrice;
        long leftTime = data.product.leftTime*1000;

        if (leftTime>0){ //显示抢购页面
            mItemDetailProductDesTvLimitPrice.setText("¥" +limitPrice);
            mItemDetailProductDesTvLessPrice.setText("[京东秒杀]便宜"+Math.abs(price-limitPrice)+"元");
            mItemDetailProductDesCountdownViewTime.start(leftTime);

            mItemDetailProductDesRlContainerNormal.setVisibility(View.GONE);
            mItemDetailProductDesLlContainerLimit.setVisibility(View.VISIBLE);


        }else{
            mItemDetailProductDesTvNewPrice.setText("¥" + price);
            //添加删除线
            mItemDetailProductDesTvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            mItemDetailProductDesTvOldPrice.setText("¥" + marketPrice);

            mItemDetailProductDesRatingBar.setRating(score);

            mItemDetailProductDesRlContainerNormal.setVisibility(View.VISIBLE);
            mItemDetailProductDesLlContainerLimit.setVisibility(View.GONE);

        }

        mItemDetailProductDesTvName.setText(name);
        mItemDetailProductDesTvInventoryArea.setText("[配货]:" + inventoryArea);

    }
}
