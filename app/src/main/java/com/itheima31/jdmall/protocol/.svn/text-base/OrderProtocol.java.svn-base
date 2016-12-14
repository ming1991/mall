package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.OrderBean;

import java.util.Map;

public class OrderProtocol extends BaseProtocol<OrderBean> {

    private String type;
    public OrderProtocol(String type) {
        this.type = type;
    }

    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "orderlist";
    }

    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("type",type);
        paramsMap.put("page","0");
        paramsMap.put("pageNum","10");
    }

}
