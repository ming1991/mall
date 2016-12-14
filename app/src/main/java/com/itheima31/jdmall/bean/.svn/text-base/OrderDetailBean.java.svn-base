package com.itheima31.jdmall.bean;

import java.util.List;

public class OrderDetailBean {

    public AddressInfoBean       addressInfo;

    public CheckoutAddupBean     checkoutAddup;
    public DeliveryInfoBean      deliveryInfo;
    public InvoiceInfoBean       invoiceInfo;
    public OrderInfoBean         orderInfo;
    public PaymentInfoBean       paymentInfo;
    public String                response;
    public List<ProductListBean> productList;
    public List<String> prom;

    public static class AddressInfoBean {
        public String addressArea;
        public String addressDetail;
        public int    id;
        public String name;
    }

    public static class CheckoutAddupBean {
        public int freight;
        public int totalCount;
        public int totalPoint;
        public int totalPrice;
    }

    public static class DeliveryInfoBean {
        public String type;
    }

    public static class InvoiceInfoBean {
        public String invoiceContent;
        public String invoiceTitle;
    }

    public static class OrderInfoBean {
        public int    flag;
        public String orderId;
        public String status;
        public String time;
    }

    public static class PaymentInfoBean {
        public int type;
    }

    public static class ProductListBean {
        public int prodNum;
        public ProductBean product;

        public static class ProductBean {
            public int    id;
            public String name;
            public String pic;
            public int    price;
            public List<ProductPropertyBean> productProperty;

            public static class ProductPropertyBean {
                public int    id;
                public String k;
                public String v;
            }
        }
    }
}
