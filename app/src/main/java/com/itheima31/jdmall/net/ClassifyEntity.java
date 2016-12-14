package com.itheima31.jdmall.net;

import java.util.List;

/**
 * Created by Tony on 2016/10/28.
 */

public class ClassifyEntity {

    public String response;

    public List<CategoryBean> category;

    public static class CategoryBean {
        public int id;
        public boolean isLeafNode;
        public String name;
        public int parentId;
        public String pic;
        public String tag;
    }
}
