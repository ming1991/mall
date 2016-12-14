package com.itheima31.jdmall.base;

import android.util.Log;

import com.google.gson.Gson;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.FileUtils;
import com.itheima31.jdmall.utils.HttpUtils;
import com.itheima31.jdmall.utils.IOUtils;
import com.itheima31.jdmall.utils.LogUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/24 9:54
 * 描    述：
 */

public abstract class BaseProtocol<RESTYPE> {

    public static final int PROTOCOLTIMEOUT = 5 * 60 * 1000;//5分钟
    private int mIndex;

    //强制从网络请求数据
    public RESTYPE loadData(int index) throws IOException {

        RESTYPE result = null;
        result = loadDataFromNet(index);
        return result;

    }

    private RESTYPE loadDataFromNet(int index) throws IOException {

        mIndex = index;

        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建请求对象
        //?index=0
        String url = Constants.URLS.BASEURL + getInterfaceKey();

        //定义一个map装参数
        Map<String, Object> paramsMap = new HashMap<>();
        getParamsMap(paramsMap);
        //paramsMap-->"index=0"
        String urlParamsByMap = HttpUtils.getUrlParamsByMap(paramsMap);


        Log.d("lin","asds "+url);
        Request request;
        if (isGet()) {
            url = url + "?" + urlParamsByMap;
            Request.Builder getRequest = new Request.Builder().get().url(url);
            request = getRequest.addHeader(getHeadName(), getHeadValue()).build();
        } else {
            String headName = getHeadName();
            String headValue = getHeadValue();
            FormBody.Builder search = new FormBody.Builder();
            postAddBody(search);
            FormBody body = search.build();

            request = new Request.Builder().post(body).url(url).addHeader(headName, headValue).build();

        }
        //3.发起请求
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {//服务器有响应
            String jsonString = response.body().string();
            /*--------------- 存本地 ---------------*/
            write2Local(index, jsonString);

            LogUtils.d("jsonString:" + jsonString);
            //解析jsonString
            Gson gson = new Gson();
            RESTYPE result = parseData(gson, jsonString);
            return result;

        }
        return null;
    }

    protected abstract boolean isGet();

    public void postAddBody(FormBody.Builder search) {

    }

    protected String getHeadValue() {
        return "20428";
    }

    protected String getHeadName() {
        return "userid";
    }

    private void write2Local(int index, String jsonString) {

        BufferedWriter writer = null;
        try {
            File cacheFile = getCacheFile(index);
            writer = new BufferedWriter(new FileWriter(cacheFile));
            //写入缓存的生成时间
            writer.write(System.currentTimeMillis() + "");
            //换行
            writer.newLine();
            //写入缓存内容
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
        }
    }

    private File getCacheFile(int index) {
        String dir = FileUtils.getDir("json");//sdcard/Android/data/包目录/json
        String fileName = generateKey(index);//唯一命中
        return new File(dir, fileName);
    }

    /**
     * @return
     * @des 得到协议对应的关键字
     * @des 在BaseProtocl中不知道具体的协议关键字是啥, 交给子类
     * @des 子类是必须实现, 定义成为抽象方法即可
     */
    public abstract String getInterfaceKey();

    /**
     * @param paramsMap 如果请求的URL没有key-value值  就只需覆写这个方法，不做任何操作
     * @des 对参数多对应的ParamsMap赋值
     * @des 子类可以覆写该方法, 有key-value值返回具体的参数
     * 例如：?pId=1&page=1&pageNum=10
     * paramsMap.put("pId", 1 + "");
     * paramsMap.put("page", 1 + "");
     * paramsMap.put("pageNum", 10 + "");
     */
    public void getParamsMap(Map<String, Object> paramsMap) {
        paramsMap.put("index", mIndex + "");//默认的
    }

    public RESTYPE parseData(Gson gson, String jsonString) {
        //反射获取子类的泛型类型
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(jsonString, type);
    }

    /**
     * @param index:
     * @return
     */
    public String generateKey(int index) {
        return getInterfaceKey() + "." + index;//默认的规则
    }


}