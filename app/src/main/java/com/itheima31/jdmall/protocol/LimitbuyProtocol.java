package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.activity.ProductlistActivity;
import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.LimitbuyBean;

import java.util.Map;

/**
 * Created by HSAEE on 2016/10/25.
 */

public class LimitbuyProtocol extends BaseProtocol<LimitbuyBean> {
    String type= ProductlistActivity.mType;
    String mKey=ProductlistActivity.mKey;
    boolean isnull;

    public LimitbuyProtocol(boolean isNull){
        this.isnull = isNull;
    }
    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        if (isnull){
            return "productlist";
        }else {
            return "search";
        }
    }

    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("page",1+"");
        paramsMap.put("pageNum",10+"");
        paramsMap.put("orderby",type);
        if (!isnull){
            paramsMap.put("keyword",mKey);
        }else{
            paramsMap.put("cId",125+"");

        }
    }
}
