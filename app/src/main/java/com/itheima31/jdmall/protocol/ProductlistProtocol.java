package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.LimitbuyBeans;

import java.util.Map;

/**
 * Created by HSAEE on 2016/10/26.
 */
public class ProductlistProtocol extends BaseProtocol<LimitbuyBeans>{
    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "limitbuy";
    }
    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("page",1+"");
        paramsMap.put("pageNum",100+"");
    }
}
