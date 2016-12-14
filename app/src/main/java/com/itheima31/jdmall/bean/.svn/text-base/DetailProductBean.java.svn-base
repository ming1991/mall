package com.itheima31.jdmall.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 15:09
 * 描述：    TODO
 */

public class DetailProductBean {

    /**
     * available : true
     * bigPic : ["/images/product/detail/bigcar1.jpg","/images/product/detail/bigcar2.jpg","/images/product/detail/bigcar3.jpg","/images/product/detail/bigcar4.jpg"]
     * buyLimit : 10
     * commentCount : 1
     * id : 1
     * inventoryArea : 全国
     * leftTime : 18000
     * limitPrice : 200
     * marketPrice : 500
     * name : 韩版时尚蕾丝裙
     * pics : ["/images/product/detail/c3.jpg","/images/product/detail/c4.jpg","/images/product/detail/c1.jpg","/images/product/detail/c2.jpg"]
     * price : 350
     * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}]
     * score : 5
     */

    public ProductBean product;
    /**
     * product : {"available":true,"bigPic":["/images/product/detail/bigcar1.jpg","/images/product/detail/bigcar2.jpg","/images/product/detail/bigcar3.jpg","/images/product/detail/bigcar4.jpg"],"buyLimit":10,"commentCount":1,"id":1,"inventoryArea":"全国","leftTime":18000,"limitPrice":200,"marketPrice":500,"name":"韩版时尚蕾丝裙","pics":["/images/product/detail/c3.jpg","/images/product/detail/c4.jpg","/images/product/detail/c1.jpg","/images/product/detail/c2.jpg"],"price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"},{"id":5,"k":"尺码","v":"XXXL"}],"score":5}
     * response : product
     */

    public String response;

    public  class ProductBean {
        public boolean available;  ////是否可售
        public int buyLimit;
        public int commentCount;
        public int id;  ////商品ID
        public String inventoryArea; //全国",  //配货说明
        public long leftTime; // //剩余时间，单位为秒
        public int limitPrice;
        public int marketPrice;
        public String name;
        public int price;
        public float score;
        public ArrayList<String> bigPic;
        public List<String> pics;
        /**
         * id : 1
         * k : 颜色
         * v : 红色
         */

        public List<ProductPropertyBean> productProperty;

        public  class ProductPropertyBean {
            public int id;
            public String k;
            public String v;
        }
    }
}
