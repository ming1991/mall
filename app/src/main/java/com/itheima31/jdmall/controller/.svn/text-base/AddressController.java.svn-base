package com.itheima31.jdmall.controller;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.AddressListBean;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;

/**
 * author：wizong
 * when：created by 2016/10/25:14:01
 * explain：地址管理协议
 */
public class AddressController extends BaseController<AddressListBean> {

    @InjectView(R.id.item_address_tv_name)
    TextView  mItemAddressTvName;
    @InjectView(R.id.item_address_iv_Default)
    ImageView mItemAddressIvDefault;
    @InjectView(R.id.item_address_tv_detail)
    TextView  mItemAddressTv;
    @InjectView(R.id.item_address_tv_phone_num)
    TextView  mItemAddressTvPhoneNum;
    @InjectView(R.id.item_address_tv_zipCode)
    TextView  mItemAddressTvZipCode;

    @Override
    public View getView() {

        return View.inflate(UIUtils.getContext(), R.layout.item_address, null);
    }

    @Override
    public void refresHolderView(AddressListBean data) {
        if (data.isDefault == 0) {
            mItemAddressIvDefault.setVisibility(View.INVISIBLE);
        } else if (data.isDefault == 1) {
            mItemAddressIvDefault.setVisibility(View.VISIBLE);
        }

        String address = data.province + "省";
        address += data.city + "市";
        address += data.addressArea + "区";
        address += data.addressDetail;

        mItemAddressTvName.setText(data.name);
        mItemAddressTvZipCode.setText(data.zipCode);
        mItemAddressTvPhoneNum.setText(data.phoneNumber);
        mItemAddressTv.setText(address);

    }
}
