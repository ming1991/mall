package com.itheima31.jdmall.controller;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.HotProductActivity;
import com.itheima31.jdmall.activity.LimitBuyActivity;
import com.itheima31.jdmall.activity.NewProductActivity;
import com.itheima31.jdmall.activity.RecommendBrandActivity;
import com.itheima31.jdmall.activity.TopPicActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/24 16:13
 * 描    述：
 */


public class HomeTabController extends BaseController {
    @InjectView(R.id.item_home_shopping_tab_iv1)
    ImageView mItemHomeShoppingTabIv1;
    @InjectView(R.id.item_home_shopping_tab_iv2)
    ImageView mItemHomeShoppingTabIv2;
    @InjectView(R.id.item_home_shopping_tab_iv3)
    ImageView mItemHomeShoppingTabIv3;
    @InjectView(R.id.item_home_shopping_tab_iv4)
    ImageView mItemHomeShoppingTabIv4;
    @InjectView(R.id.item_home_shopping_tab_iv5)
    ImageView mItemHomeShoppingTabIv5;


    @Override
    public View getView() {
        View shoppingTab = View.inflate(UIUtils.getContext(), R.layout.item_home_shopping_tab, null);
        return shoppingTab;
    }

    @Override
    public void refresHolderView(Object data) {

    }

    @OnClick({R.id.item_home_shopping_tab_iv1, R.id.item_home_shopping_tab_iv2, R.id.item_home_shopping_tab_iv3, R.id.item_home_shopping_tab_iv4, R.id.item_home_shopping_tab_iv5})
    public void onClick(View view) {
        Intent mIntent = new Intent();
        switch (view.getId()) {
            case R.id.item_home_shopping_tab_iv1:
                mIntent = new Intent(UIUtils.getContext(), LimitBuyActivity.class);
                break;
            case R.id.item_home_shopping_tab_iv2:
                mIntent = new Intent(UIUtils.getContext(), TopPicActivity.class);
                break;
            case R.id.item_home_shopping_tab_iv3:
                mIntent = new Intent(UIUtils.getContext(), NewProductActivity.class);
                break;
            case R.id.item_home_shopping_tab_iv4:
                mIntent = new Intent(UIUtils.getContext(), HotProductActivity.class);
                break;
            case R.id.item_home_shopping_tab_iv5:
                mIntent = new Intent(UIUtils.getContext(), RecommendBrandActivity.class);
                break;
        }
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(mIntent);
    }
}
