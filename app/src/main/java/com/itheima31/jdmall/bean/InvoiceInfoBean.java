package com.itheima31.jdmall.bean;

import java.util.List;

/**
 * 发票信息的bean类
 */

public class InvoiceInfoBean {

    public String                response;
    public List<InvoiceBean> invoice;

    public class InvoiceBean {
        public String content;
        public int    id;
    }

    @Override
    public String toString() {
        return "InvoiceInfoBean{" +
                "response='" + response + '\'' +
                ", invoice=" + invoice +
                '}';
    }
}
