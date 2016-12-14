package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.MineBean;

/**
 * Created by yangg on 2016/10/25.
 *
 * @desc
 */
public class AccountCenterProtocol extends BaseProtocol<MineBean> {
    public static final String TAG = "AccountCenterProtocol";
    public String userid;

    public AccountCenterProtocol(String userid) {
        this.userid = userid;
    }

    protected String getHeadName() {
        return "userid";
    }

    @Override
    protected String getHeadValue() {
        return userid;
    }

    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "userinfo";
    }
}
