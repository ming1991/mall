package com.itheima31.jdmall.fragment;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.CustomMarqueeTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 14:30
 * 描述：    TODO
 */
public class DetailDetailFragment extends BaseFragment {
    @InjectView(R.id.item_detail_product_detail_sv)
    ScrollView mItemDetailProductDetailSv;
    @InjectView(R.id.item_detail_product_detail_iv)
    ImageView mItemDetailProductDetailIv;
    @InjectView(R.id.fagment_product_detail_iv1)
    ImageView mFagmentProductDetailIv1;
    @InjectView(R.id.fagment_product_detail_customMarqueeTextView)
    CustomMarqueeTextView mFagmentProductDetailCustomMarqueeTextView;

    @Override
    public LoadingPager.LoadedResultEnum initData() {

        //模拟数据为真 TODO
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.fragment_product_detail_des, null);
        ButterKnife.inject(this, view);

       /* //加载gif图

        Glide
                .with(this)
                .load("file:///android_asset/detail1.gif")
                .into(mFagmentProductDetailIv1);*/

        //给字体设置变化的颜色动画
        playAnima();
        return view;
    }

    private void playAnima() {

        int colorA = Color.parseColor("#f23465");
        int colorB = Color.parseColor("#ff4081");
        int colorC = Color.parseColor("#ff0000");


        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(mFagmentProductDetailCustomMarqueeTextView
                ,"textColor",colorA,colorB,colorC);
        objectAnimator.setDuration(500);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.start();

    }


    @OnClick(R.id.item_detail_product_detail_iv)
    public void onClick() {
        mItemDetailProductDetailSv.fullScroll(ScrollView.FOCUS_UP);
    }


}
