package com.itheima31.jdmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */

public class BalanceInfoBean {

    /**
     * freight : 10
     * totalCount : 5
     * totalPoint : 30
     * totalPrice : 450
     */

    public CheckoutAddupBean      checkoutAddup;
    /**
     * checkoutAddup : {"freight":10,"totalCount":5,"totalPoint":30,"totalPrice":450}
     * checkoutProm : ["促销信息一","促销信息二"]
     * deliveryList : [{"des":"周一至周五送货","type":1},{"des":"双休日及公众假期送货","type":2},{"des":"时间不限，工作日双休日及公众假期均可送货","type":3}]
     * paymentList : [{"des":"到付-现金","type":1},{"des":"到付-POS机","type":2},{"des":"支付宝","type":1}]
     * productList : [{"prodNum":3,"product":{"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]}},{"prodNum":2,"product":{"id":2,"name":"粉色毛衣","pic":"/images/product/detail/q1.jpg","price":100,"productProperty":[{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"}]}}]
     * response : checkOut
     */

    public String                 response;
    public List<String>           checkoutProm;
    /**
     * des : 周一至周五送货
     * type : 1
     */

    public List<DeliveryListBean> deliveryList;
    /**
     * des : 到付-现金
     * type : 1
     */

    public List<PaymentListBean>  paymentList;
    /**
     * prodNum : 3
     * product : {"id":1,"name":"韩版时尚蕾丝裙","pic":"/images/product/detail/c3.jpg","price":350,"productProperty":[{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]}
     */

    public List<ProductListBean>  productList;
    /**
     * orderId : 465106
     * paymentType : 1
     * price : 450
     */

    public OrderInfoBean          orderInfo;

    public class OrderInfoBean {
        public String orderId;
        public int    paymentType;
        public int    price;

        @Override
        public String toString() {
            return "OrderInfoBean{" +
                    "orderId='" + orderId + '\'' +
                    ", paymentType=" + paymentType +
                    ", price=" + price +
                    '}';
        }
    }

    public class CheckoutAddupBean {
        public float   freight;
        public int   totalCount;
        public float totalPoint;
        public float totalPrice;

    }

    public class DeliveryListBean {
        public String des;
        public int    type;

    }

    public class PaymentListBean {
        public String des;
        public int    type;

    }

    public class ProductListBean {
        public int         prodNum;
        /**
         * id : 1
         * name : 韩版时尚蕾丝裙
         * pic : /images/product/detail/c3.jpg
         * price : 350
         * productProperty : [{"id":1,"k":"颜色","v":"红色"},{"id":2,"k":"颜色","v":"绿色"},{"id":3,"k":"尺码","v":"M"},{"id":4,"k":"尺码","v":"XXL"}]
         */

        public ProductBean product;

        public class ProductBean {
            public int                       id;
            public String                    name;
            public String                    pic;
            public float                     price;
            /**
             * id : 1
             * k : 颜色
             * v : 红色
             */

            public List<ProductPropertyBean> productProperty;

            public class ProductPropertyBean {
                public int    id;
                public String k;
                public String v;
            }
        }
    }

    @Override
    public String toString() {
        return "BalanceInfoBean{" +
                "checkoutAddup=" + checkoutAddup +
                ", response='" + response + '\'' +
                ", checkoutProm=" + checkoutProm +
                ", deliveryList=" + deliveryList +
                ", paymentList=" + paymentList +
                ", productList=" + productList +
                ", orderInfo=" + orderInfo +
                '}';
    }
}
