package com.itheima31.jdmall.controller;

import android.view.View;
import android.widget.LinearLayout;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;

/**
 * 类    名:  LoadMoreHolder
 */
public class LoadMoreController extends BaseController<Integer> {



    public static final int LOADMORE_LOADING = 0;//正在加载更多
    public static final int LOADMORE_ERROR = 1;//加载更多失败,点击重试
    public static final int LOADMORE_NONE = 2;//没有加载更多

    @InjectView(R.id.item_loadmore_container_loading)
    LinearLayout mItemLoadmoreContainerLoading;
    @InjectView(R.id.item_loadmore_container_retry)
    LinearLayout mItemLoadmoreContainerRetry;

    @Override
    public View getView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_loadmore, null);
    }

    @Override
    public void refresHolderView(Integer state) {
        //隐藏所有的
        mItemLoadmoreContainerLoading.setVisibility(View.GONE);
        mItemLoadmoreContainerRetry.setVisibility(View.GONE);

        switch (state) {
            case LOADMORE_LOADING:
                mItemLoadmoreContainerLoading.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_ERROR:
                mItemLoadmoreContainerRetry.setVisibility(View.VISIBLE);
                break;
            case LOADMORE_NONE:
                break;
            default:
                break;
        }
    }
}
