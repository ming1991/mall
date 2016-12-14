package com.itheima31.jdmall.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 * author:ZY
 */

public class TopicBean {

    /**
     * productList : [{"id":17,"marketPrice":300,"name":"春装新款","pic":"/images/product/detail/q15.jpg","price":200},{"id":18,"marketPrice":200,"name":"短裙","pic":"/images/product/detail/q16.jpg","price":160},{"id":19,"marketPrice":200,"name":"新款秋装","pic":"/images/product/detail/q17.jpg","price":160},{"id":20,"marketPrice":200,"name":"妈妈新款","pic":"/images/product/detail/q18.jpg","price":160},{"id":24,"marketPrice":200,"name":"春秋新款外套","pic":"/images/product/detail/q22.jpg","price":160},{"id":25,"marketPrice":150,"name":"新款秋装","pic":"/images/product/detail/q23.jpg","price":160},{"id":26,"marketPrice":200,"name":"粉色系暖心套装","pic":"/images/product/detail/q24.jpg","price":200},{"id":27,"marketPrice":150,"name":"韩版粉嫩外套","pic":"/images/product/detail/q25.jpg","price":160},{"id":28,"marketPrice":300,"name":"春装新款","pic":"/images/product/detail/q26.jpg","price":200},{"id":29,"marketPrice":180,"name":"日本奶粉","pic":"/images/product/detail/q26.jpg","price":160}]
     * response : topicProductList
     */

    private String response;
    /**
     * id : 17
     * marketPrice : 300
     * name : 春装新款
     * pic : /images/product/detail/q15.jpg
     * price : 200
     */

    private List<ProductListBean> productList;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        public int id;
        public int marketPrice;
        public String name;
        public String pic;
        public int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(int marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
