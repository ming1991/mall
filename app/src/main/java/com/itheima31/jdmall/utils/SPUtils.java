package com.itheima31.jdmall.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.conf.Constants;

import java.util.Map;

/**
 * Created by yangg on 2016/10/25.
 *
 * @desc 将数据以sharedPreference的形式保存
 */
public class SPUtils {

    public static void putString(String key, String value) {
        SharedPreferences sharedPref = UIUtils.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        if (!(StringUtils.isEmpty(key) && StringUtils.isEmpty(value))) {
            sharedPref.edit().putString(key, value).commit();
        }
    }

    /**
     * Des : 获取用户的userid之类的相关数据
     *
     * @param key : Constants.LOGED_ACCOUNT
     * @return split[0] : response -->login
     * split[1] : userid -->用户id
     * split[2] : login_account -->用户账号
     * split[3] : login_password -->用户密码
     */
    public static String[] getString(String key) {
        SharedPreferences sharedPref = UIUtils.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        if (!StringUtils.isEmpty(key)) {
            String value = sharedPref.getString(key, "");
            String[] split = value.split("#");
            return split;
        }
        return null;
    }

    /**
     * Des : 注销用户的同时,清空在"user_info"里面存储的所有的用户信息
     */
    public static void clearSharedPref() {
        SharedPreferences sharedPref = UIUtils.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
        sharedPref.edit().clear().commit();
    }

    //购物车SP


    // 获取指定用户购物车所有条目值
    public static Map<String, Integer> getAllShopCart(Context context, String uder_id) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.SHOPCART_FILENAME + uder_id, Context.MODE_PRIVATE);
        Map<String, Integer> all = (Map<String, Integer>) sp.getAll();

        return all;

    }

    // 保存购物车条目的值
    public static void putToShopCart(Context context, String user_id, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.SHOPCART_FILENAME + user_id, Context.MODE_PRIVATE);
        int num = sp.getInt(key, 0);
        if (num == 0) {//商品不存在
            if(value>10 || value<0){
                Toast.makeText(MyApplication.getContext(), "一次只能购买10个以内", Toast.LENGTH_SHORT).show();
            }else {
                sp.edit().putInt(key, value + num).commit();
            }


        } else {//商品存在
            if (num + value <= 0) {//商品数减为0,要删除
                sp.edit().remove(key).commit();
            } else if(num + value >10){
                Toast.makeText(MyApplication.getContext(), "操作失败，只能再购买"+(10-num)+"个", Toast.LENGTH_SHORT).show();
            }else {
                sp.edit().putInt(key, value + num).commit();
            }

        }

    }

    public static void deleteToShopCart(Context context, String user_id, String key) {
        SharedPreferences sp = context.getSharedPreferences(
                Constants.SHOPCART_FILENAME + user_id, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }
}
