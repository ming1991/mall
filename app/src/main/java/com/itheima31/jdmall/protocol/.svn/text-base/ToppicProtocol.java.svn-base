package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.TopicBean;

import java.util.Map;

/**
 * Created by HSAEE on 2016/10/26.
 */
public class ToppicProtocol extends BaseProtocol<TopicBean>{

    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "topic/plist";
    }
    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("page",0+"");
        paramsMap.put("pageNum",10+"");
        paramsMap.put("id",1+"");
        paramsMap.put("orderby","saleDown");
    }
}
