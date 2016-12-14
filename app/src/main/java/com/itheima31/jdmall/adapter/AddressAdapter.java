package com.itheima31.jdmall.adapter;

import android.support.annotation.NonNull;
import android.widget.ListView;

import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.base.MyBaseAdapter;
import com.itheima31.jdmall.bean.AddressListBean;
import com.itheima31.jdmall.controller.AddressController;

import java.util.List;

public class AddressAdapter extends MyBaseAdapter<AddressListBean> {


    public AddressAdapter(List<AddressListBean> datas, ListView listView) {
        super(datas, listView);
    }

    @NonNull
    @Override
    public BaseController getSpecialbaseHolder(int position) {
        return new AddressController();
    }

    public void notifyMyDataSetChange() {
        notifyDataSetChanged();
    }
}