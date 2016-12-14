package com.itheima31.jdmall.fragment;


import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.DcvGoodsActivity;
import com.itheima31.jdmall.activity.DcvliveActivity;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.BrandBean;
import com.itheima31.jdmall.bean.ValueBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.controller.DCVAnimatedController;
import com.itheima31.jdmall.controller.DCVHeadController;
import com.itheima31.jdmall.dcvheadline.DcvHeadline1Activity;
import com.itheima31.jdmall.utils.HttpUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Tony on 2016/10/21.
 */

public class DiscoverFrament extends BaseFragment {

    private List<Map<String, Object>> mHeadlist;
    private Map<String, Object>       map;
    String[] names = {"直播", "社区", "抽奖活动", "优选清单", "店铺头条", "选礼神器", "拍照购"};

    int[] pics = {R.drawable.dcv_live, R.drawable.dcv_community, R.drawable.dcv_something_good, R.drawable.dcv_list,
            R.drawable.dcv_headline, R.drawable.dcv_choose};

    private List<BrandBean> mBrandBean = new ArrayList<>();
    private Intent mIntent;
    boolean isLoad = false;

    public LoadingPager.LoadedResultEnum initData() {
        if (isLoad && mBrandBean != null) {
            mBrandBean.clear();
        }
        try {
            parseJson();
            return checkResData(mBrandBean);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    @Override
    public View initSuccessView() {
        getData();

        LinearLayout container = new LinearLayout(UIUtils.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.setPadding(0, 118, 0, 0);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(params);

        /***************DCV Start********************/
        DCVHeadController headController = new DCVHeadController(mHeadlist);
        container.addView(headController.mBaseView);
        headController.refresHolderView(null);

        DCVAnimatedController animatedController = new DCVAnimatedController();
        container.addView(animatedController.mBaseView);
        animatedController.refresHolderView(mBrandBean);

        /***************DCV End********************/

        /********************点击头部项目吐丝点击位置****************************/
        //       DCVheadListView dcVheadListView = new DCVheadListView()
        headController.setOnHeadItemListener(new DCVHeadController.OnHeadItemListener() {
            @Override
            public void onHeadItem(int position) {
                switch (position) {
                    case 0:
                        Intent intent=new Intent(UIUtils.getContext(), DcvliveActivity.class);
                        intent.putExtra("URL","https://m.douyu.com");
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(UIUtils.getContext(), "你点击的" + (names[position]) + "选项网络数据无法加载", Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        mIntent = new Intent(UIUtils.getContext(), DcvGoodsActivity.class);
                        startActivity(mIntent);
                        break;
                    case 3:
                        Toast.makeText(UIUtils.getContext(), "你点击的" + (names[position]) + "选项网络数据无法加载", Toast.LENGTH_SHORT).show();

                        break;
                    case 4:
                        mIntent = new Intent(UIUtils.getContext(), DcvHeadline1Activity.class);
                        startActivity(mIntent);
                        break;
                    case 5:
                        Toast.makeText(UIUtils.getContext(), "你点击的" + (names[position]) + "选项网络数据无法加载", Toast.LENGTH_SHORT).show();

                        break;

                }
                //  Toast.makeText(UIUtils.getContext(), "你点击的"+(names[position])+"选项网络数据无法加载", Toast.LENGTH_SHORT).show();

            }
        });
        //        /*************************************************/
        isLoad = true;
        return container;
    }

    private List<Map<String, Object>> getData() {
        //头部蘑菇数据
        mHeadlist = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < pics.length; i++) {
            map = new HashMap<String, Object>();
            map.put("index", names[i]);
            map.put("img", pics[i]);
            mHeadlist.add(map);
        }
        return mHeadlist;
    }

    private void parseJson() throws Exception {
        List<BrandBean> dvcBrand = new ArrayList<>();
        //1.创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //2.创建请求对象
        String url = Constants.URLS.BASEURL + getInterfaceKey();

        //定义一个map装参数
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("apikey", "9cf5aecf1fd1c219a835903acca0f53b");
        paramsMap.put("id", "0");
        paramsMap.put("page", "1");
        paramsMap.put("rows", "20");
        //paramsMap-->"index=0"
        String urlParamsByMap = HttpUtils.getUrlParamsByMap(paramsMap);

        url = url + "?" + urlParamsByMap;
        Request request;
        Request.Builder getRequest = new Request.Builder().get().url(url);
        request = getRequest.addHeader(getHeadName(), getHeadValue()).build();
        //3.发起请求
        Response response = okHttpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            String json = response.body().string();

            JSONObject obj = new JSONObject(json);
            JSONArray arr = obj.getJSONArray("brand");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject brandObj = arr.getJSONObject(i);

                String key = brandObj.getString("key");
                BrandBean mDvcBrandBean = new BrandBean();
                mDvcBrandBean.key = key;

                List<ValueBean> dvcValues = new ArrayList<>();
                JSONArray value = brandObj.getJSONArray("value");
                for (int j = 0; j < value.length(); j++) {
                    JSONObject valueObj = value.getJSONObject(j);

                    if (valueObj != null) {
                        ValueBean valueBean = new ValueBean();
                        valueBean.id = valueObj.getInt("id");
                        valueBean.name = valueObj.getString("name");
                        valueBean.pic = valueObj.getString("pic");
                        dvcValues.add(valueBean);
                    }
                }
                mDvcBrandBean.value = dvcValues;
                mBrandBean.add(mDvcBrandBean);
            }
        }
    }

    public String getInterfaceKey() {
        return "brand";
    }

    protected String getHeadValue() {
        return "20428";
    }

    protected String getHeadName() {
        return "userid";
    }
}
