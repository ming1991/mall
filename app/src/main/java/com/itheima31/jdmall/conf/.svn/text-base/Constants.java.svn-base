package com.itheima31.jdmall.conf;

import com.itheima31.jdmall.bean.OrderListBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/24 10:00
 * 描    述：网络请求的URL
 */


public class Constants {

    public static final class URLS {


//        public static final String BASEURL = "http://10.0.2.2:8080/market/";

        public static final String BASEURL = "http://10.0.3.2:8080/market/";
        //远程服务器的链接地址

//        public static final String BASEURL = "http://192.168.20.210:8080/market/";

    }

    //结算跳转源头标记
    public static final String SHOPCARTTOBALANCE = "shopcarttobalance";

    //商品详情跳转支付中心,action
    public static final String DETAILPRODUCT2BALANCE = "detailproduct2balance";
    // //商品详情跳转支付中心,key ,value = 序列化的ShopBean
    public static final String DETAILPRODUCT2BALANCE_KEY = "oncePay";

    //   快报的消息编号
    public static final int MSG_WHAT_UPDATE_NEWS_INFO = 22;

    //商品存在SP的SP文件名
    public static final String SHOPCART_FILENAME = "shopcart_filename";
    //商品的尺寸颜色'

    public static final int COLOR_RED = 1;
    public static final int COLOR_GREEN = 2;
    public static final int SIZE_M = 3;
    public static final int SIZE_XXL = 4;
    public static final int SIZE_XXXL = 5;
    private static final Map<Integer, String> map = new HashMap<>();

    public static Map getMap() {
        if (map.size() != 0) {
            return map;
        }
        map.put(1, "红色");
        map.put(2, "绿色");
        map.put(3, "M");
        map.put(4, "XXL");
        map.put(5, "XXXL");

        return map;

    }

    //订单中心
    public static List<OrderListBean> mOrderList = new ArrayList<>();

    //保存用户相关的文件夹,注销的时候会清空,仅限存储用户信息
    public static final String LOGED_ACCOUNT = "loged_account";

    public static final String ACCOUNT_INFO = "account_info";

    public static final String SP_FILE_NAME = "sp_file_name";

    //存储搜索记录
    public static final String SEARCH_HISTORY = "search_history";

}
