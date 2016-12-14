package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.ShoppingCartBean;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/24.
 * Todo:
 */
public class ShoppingProtocol extends BaseProtocol<ShoppingCartBean> {
    public static final String TAG = "ShoppingProtocol";
    int id;         //加入购物车的商品id
    int num;        //加入购物车的商品数量
    int id_color;   ////加入购物车的商品颜色
    int id_size;    //加入购物车的商品尺寸

    int id2;         //加入购物车的商品id
    int num2;        //加入购物车的商品数量
    int id_color2;   ////加入购物车的商品颜色
    int id_size2;    //加入购物车的商品尺寸
    public ShoppingProtocol(){}
    //public ShoppingProtocol(int id,int num,int id_color,int id_size,int id2,int num2,int id_color2,int id_size2){
    public ShoppingProtocol(int id,int num,int id_color,int id_size){
        this.id=id;
        this.num=num;
        this.id_color=id_color;
        this.id_size=id_size;

        /*this.id2=id2;
        this.num2=num2;
        this.id_color2=id_color2;
        this.id_size2=id_size2;*/

    }


    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "cart";
    }

    @Override
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("sku",id+":"+num+":"+id_color+","+id_size);

    }
}
