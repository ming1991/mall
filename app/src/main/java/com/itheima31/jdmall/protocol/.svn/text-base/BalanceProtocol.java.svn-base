package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.BalanceInfoBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;

/**
 * 结算中心页面协议
 */

public class BalanceProtocol extends BaseProtocol<BalanceInfoBean> {

    private HashMap<String, String> mBodyMap;
    private String                  mInterfaceKey;

    public BalanceProtocol(String interfaceKey, HashMap<String, String> bodyMap) {
        mInterfaceKey = interfaceKey;
        mBodyMap = bodyMap;
    }

    @Override
    protected boolean isGet() {
        return false;
    }

    @Override
    public String getInterfaceKey() {
        return mInterfaceKey;
    }

    @Override
    public void postAddBody(FormBody.Builder search) {
        //遍历集合,添加请求体
        for (Map.Entry<String, String> entry : mBodyMap.entrySet()) {
            search.add(entry.getKey(), entry.getValue());
        }
    }
}
