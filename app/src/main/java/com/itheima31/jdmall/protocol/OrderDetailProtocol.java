package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.activity.OrderDetailActivity;
import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.OrderDetailBean;

import java.util.Map;

/**
 * Created by ${林仁迪} on 2016/10/24.
 * 描述:
 */
public class OrderDetailProtocol extends BaseProtocol<OrderDetailBean> {

    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "orderdetail";
    }

    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("orderId", OrderDetailActivity.orderId);
    }
}
