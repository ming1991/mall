package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.MineBean;

import okhttp3.FormBody;

/**
 * Created by yangg on 2016/10/24.
 *
 */
public class LoginProtocol extends BaseProtocol<MineBean> {

    private String userName;
    private String password;

    public LoginProtocol() {

    }

    public LoginProtocol(String login_acount, String login_pwd) {
        userName = login_acount;
        password = login_pwd;
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
        search.add("username", userName);
        search.add("password", password);
    }
}
