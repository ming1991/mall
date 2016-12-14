package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.Map;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/25 10:19
 * 描述：    TODO
 */
public class DetailProductProtocol extends BaseProtocol<DetailProductBean> {
    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "product";
    }

    //传入uil参数的键和值


    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        //SPUtil.putInt(UIUtils.getContext(),"pId",mPId);
        int pId = SPUtil.getInt(UIUtils.getContext(), "pId", 1);

        paramsMap.put("pId", pId + "");
    }


}
