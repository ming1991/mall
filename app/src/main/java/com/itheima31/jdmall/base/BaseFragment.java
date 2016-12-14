package com.itheima31.jdmall.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima31.jdmall.utils.UIUtils;

import java.util.List;
import java.util.Map;

/**
 * 类    名:  BaseFragment
 */
public abstract class BaseFragment extends Fragment {

    public LoadingPager mLoadingPager;

    public Activity mActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingPager = new LoadingPager(UIUtils.getContext()) {
            @Override
            public LoadedResultEnum initData() {
                return BaseFragment.this.initData();
            }
            @Override
            public View initSuccessView() {
                return BaseFragment.this.initSuccessView();
            }
        };
        //获取Activity
        mActivity = getActivity();
        mLoadingPager.triggerLoadData();
        return mLoadingPager;
    }
    /**
     * 加载数据  子线程执行 子类实现
     */
    public abstract LoadingPager.LoadedResultEnum initData();

    /**
     * 成功视图 主线程执行 子类实现
     */
    public abstract View initSuccessView();


    public LoadingPager.LoadedResultEnum checkResData(Object resObj) {
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
}
