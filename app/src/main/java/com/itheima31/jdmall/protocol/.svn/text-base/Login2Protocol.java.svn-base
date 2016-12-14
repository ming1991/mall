package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.MineBean;

import okhttp3.FormBody;

/**
 * Created by yangg on 2016/10/29.
 *
 * @desc
 */
public class Login2Protocol extends BaseProtocol<MineBean> {
    public static final String TAG = "Login2Protocol";
    private final String mAccount;
    private final String mPwd;

    public Login2Protocol(String account, String pwd) {
        mPwd = pwd;
        mAccount = account;
    }


    @Override
    protected boolean isGet() {
        return false;
    }

    @Override
    public String getInterfaceKey() {
        return "login";
    }

    @Override
    public void postAddBody(FormBody.Builder search) {
        search.add("username", mAccount);
        search.add("password", mPwd);
    }
}
