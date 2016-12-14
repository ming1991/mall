package com.itheima31.jdmall.controller;

import android.view.View;

import com.itheima31.jdmall.adapter.ExampleAdapter;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.BrandBean;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.DCVAnimatedExpandableListView;

import java.util.List;

/**
 * Created by wu on 2016/10/25.
 */
public class DCVAnimatedController extends BaseController<List<BrandBean>> {


    private DCVAnimatedExpandableListView mExpandableListView;
    private ExampleAdapter mAdapter;

    @Override
    public View getView() {
        mExpandableListView = new DCVAnimatedExpandableListView(UIUtils.getContext());
        mAdapter = new ExampleAdapter(UIUtils.getContext());
        mExpandableListView.setAdapter(mAdapter);
        return mExpandableListView;
    }

    @Override
    public void refresHolderView(List<BrandBean> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

}
