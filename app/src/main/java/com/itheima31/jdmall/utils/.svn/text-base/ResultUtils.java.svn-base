package com.itheima31.jdmall.utils;

import android.content.Intent;
import android.widget.Toast;

import com.itheima31.jdmall.activity.LoginActivity;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.AddressBean;
import com.itheima31.jdmall.bean.BalanceInfoBean;
import com.itheima31.jdmall.bean.InvoiceInfoBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Tony on 2016/10/23.
 */

public class ResultUtils {

    public static LoadingPager.LoadedResultEnum checkResData(Object resObj) {
        if (resObj == null) {
            return LoadingPager.LoadedResultEnum.EMPTY;
        }

        if (resObj instanceof List) {
            if (((List) resObj).size() == 0) {
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }
        if (resObj instanceof Map) {
            if (((Map) resObj).size() == 0) {
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    public static LoadingPager.LoadedResultEnum checkBalanceData(Object resObj) {
        if (resObj == null) {
            Toast.makeText(UIUtils.getContext(), "网络错误", Toast.LENGTH_SHORT).show();
            return LoadingPager.LoadedResultEnum.ERROR;
        }

        if (resObj instanceof AddressBean) {
            if (((AddressBean) resObj).response.equals("error")) {
                MyApplication.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        UIUtils.getContext().startActivity(intent);
                        LogUtils.e("执行了跳转");
                    }
                });
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }

        if (resObj instanceof InvoiceInfoBean) {
            if (((InvoiceInfoBean) resObj).response.equals("error")) {
                MyApplication.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        UIUtils.getContext().startActivity(intent);
                    }
                });
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }

        if (resObj instanceof BalanceInfoBean) {
            if (((BalanceInfoBean) resObj).response.equals("error")) {
                MyApplication.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(UIUtils.getContext(), "请先登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UIUtils.getContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        UIUtils.getContext().startActivity(intent);
                    }
                });
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }

        if (resObj instanceof List) {
            if (((List) resObj).size() == 0) {
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }

        if (resObj instanceof Map) {
            if (((Map) resObj).size() == 0) {
                return LoadingPager.LoadedResultEnum.EMPTY;
            }
        }
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }
}
