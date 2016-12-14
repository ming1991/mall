package com.itheima31.jdmall.event;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/27 0:27
 * 描述：    TODO
 */

public class EventProductInfo {





    public int productNum;
    public  String color;
    public  String size;
    public int id;  //商品id

    public EventProductInfo(int productNum, String color, String size, int id) {
        this.productNum = productNum;
        this.color = color;
        this.size = size;
        this.id = id;
    }
}
