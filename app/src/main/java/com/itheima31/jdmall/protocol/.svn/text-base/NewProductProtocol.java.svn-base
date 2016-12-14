package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.NewProductBean;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/29.
 * Todo:
 */
public class NewProductProtocol extends BaseProtocol<NewProductBean>{
    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "newproduct";
    }
    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("page",0+"");
        paramsMap.put("pageNum",10+"");
        //paramsMap.put("id",1+"");
        paramsMap.put("orderby","saleDown");
    }
}
