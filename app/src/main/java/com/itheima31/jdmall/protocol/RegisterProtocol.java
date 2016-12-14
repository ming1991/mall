package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.MineBean;

import okhttp3.FormBody;

/**
 * Created by yangg on 2016/10/25.
 *
 * @desc
 */
public class RegisterProtocol extends BaseProtocol<MineBean> {
    private String userName;
    private String password;

    public RegisterProtocol() {

    }

    public RegisterProtocol(String register_acount, String register_pwd) {
        userName = register_acount;
        password = register_pwd;
    }

    @Override
    protected boolean isGet() {
        return false;
    }

    @Override
    public String getInterfaceKey() {
        return "register";
    }

    @Override
    public void postAddBody(FormBody.Builder search) {
        search.add("username",userName).add("password",password);

    }
}
