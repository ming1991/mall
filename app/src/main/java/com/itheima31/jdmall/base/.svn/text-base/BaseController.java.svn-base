package com.itheima31.jdmall.base;

import android.view.View;

import butterknife.ButterKnife;

/**
 * 描    述： 所有的controller的基类
 *
 * 原来的Holder
 */
public abstract class BaseController<BEAN> {

    public View   mBaseView;//持有的视图
    public BEAN mData;

    public BaseController() {
        mBaseView = getView();
        ButterKnife.inject(this, mBaseView);

        mBaseView.setTag(this);
    }

    /**
     */
    public abstract View getView() ;

    /**
     */
    public void setDataAndRefreshView(BEAN data) {
        //对成员变量里面的数据赋值
        mData = data;

        //刷新HolderView
        refresHolderView(mData);
    }

    public void refresHolderViewWithPosition(BEAN data,int position){

    }
    /**
     * @param data
     */
    public abstract void refresHolderView(BEAN data);
}
