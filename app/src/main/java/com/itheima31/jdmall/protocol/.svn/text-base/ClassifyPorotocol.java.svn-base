package com.itheima31.jdmall.protocol;

import com.itheima31.jdmall.base.BaseProtocol;
import com.itheima31.jdmall.bean.ClassifyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 2016/10/25.
 */

public class ClassifyPorotocol extends BaseProtocol<List<ClassifyBean.CategoryBean>> {
    @Override
    protected boolean isGet() {
        return true;
    }

    @Override
    public String getInterfaceKey() {
        return "category";
    }

    @Override
    public List<ClassifyBean.CategoryBean> parseData(com.google.gson.Gson gson, String jsonString) {

        /**
         * 这是有多难解析
         */

        ClassifyBean bean = gson.fromJson(jsonString, ClassifyBean.class);

        List<ClassifyBean.CategoryBean> datas = new ArrayList<>();

        List<ClassifyBean.CategoryBean> category = bean.category;


        /*---------* 一级 *---------*/

        for (ClassifyBean.CategoryBean categoryBean : category) {
            if (categoryBean.parentId == 0) {
                datas.add(categoryBean);
            }
        }

        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).level2List = new ArrayList<>();
        }

        /*---------| 二级 |---------*/
        for (ClassifyBean.CategoryBean categoryBean : category) {
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).id == categoryBean.parentId) {
                    datas.get(i).level2List.add(categoryBean);
                }
            }
        }

        for (int i = 0; i < datas.size(); i++) {
            for (int j = 0; j < datas.get(i).level2List.size(); j++) {
                datas.get(i).level2List.get(j).level2List = new ArrayList<>();
            }
        }

        /*---------| 三级 |---------*/
        for (ClassifyBean.CategoryBean categoryBean : category) {
            for (int i = 0; i < datas.size(); i++) {
                for (int j = 0; j < datas.get(i).level2List.size(); j++) {
                    if (categoryBean.parentId == datas.get(i).level2List.get(j).id) {
                        datas.get(i).level2List.get(j).level2List.add(categoryBean);
                    }
                }
            }
        }

//        Log.d(TAG, "parseData: " + datas.get(0).level2List.get(0).level2List.size());

        return datas;
    }

}
