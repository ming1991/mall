package com.itheima31.jdmall.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima31.jdmall.bean.OrderListBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 描述:订单中心缓存工具
 */
public class OrderCache {
    //把数据保存本地
    public static void write2Local(String jsonString) {
        BufferedWriter writer = null;
        try {
            File cacheFile = getFile();
            writer = new BufferedWriter(new FileWriter(cacheFile));
            //写入缓存内容
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
        }
    }

    //从本地获取数据
    public static List<OrderListBean> loadDataFromLocal() {
        //1.找到缓存文件
        BufferedReader reader = null;
        try {
            File cacheFile = getFile();
            if (cacheFile.exists()) {//有缓存
                reader = new BufferedReader(new FileReader(cacheFile));
                //读取缓存内容
                String cacheJsonString = reader.readLine();
                //解析返回
                return (ArrayList) new Gson().fromJson(cacheJsonString, new TypeToken<List<OrderListBean>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(reader);
        }
        return null;
    }

    public static File getFile() {
        String dir = FileUtils.getDir("order");
        String fileName = "list";//唯一命中
        return new File(dir, fileName);
    };
}
