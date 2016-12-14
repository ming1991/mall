package com.itheima31.jdmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/24.
 * Todo:
 */
public class ShoppingCartBean {

    public String response;
    public int totalCount;
    public int totalPoint;
    public int totalPrice;


    public List<CartBean> cart;
    public List<String> prom;

    public  class CartBean {
        public int prodNum;


        public ProductBean product;

        public  class ProductBean {
            public int buyLimit;
            public int id;
            public String name;
            public String number;
            public String pic;
            public int price;
            public boolean isChecked=false;
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

    @Override
    public String toString() {
        return "ShoppingCartBean{" +
                "response='" + response + '\'' +
                ", totalCount=" + totalCount +
                ", totalPoint=" + totalPoint +
                ", totalPrice=" + totalPrice +
                ", cart=" + cart +
                ", prom=" + prom +
                '}';
    }
}
