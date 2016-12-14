package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.activity.OrderDetailActivity;
import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.OrderBean;

import java.util.Map;

public class CancelOrderProtocol extends BaseProtocol<OrderBean> {
    public static final String TAG = "CancelOrderProtocol";

    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "ordercancel";
    }

    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("orderId", OrderDetailActivity.orderId);

    }
}
