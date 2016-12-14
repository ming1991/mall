package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.BrandBean;

import java.util.List;
import java.util.Map;

/**
 * Created by wu on 2016/10/25.
 */
public class DiscoverProtocol extends BaseProtocol<List<BrandBean>>{

    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "brand";
    }

    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        http://localhost:8080/market/brand?apikey=9cf5aecf1fd1c219a835903acca0f53b&id=0&page=1&rows=20
        paramsMap.put("apikey", "9cf5aecf1fd1c219a835903acca0f53b");
        paramsMap.put("id", "0");
        paramsMap.put("page", "1");
        paramsMap.put("rows", "20");
    }
}
