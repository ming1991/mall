package com.itheima31.jdmall.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Tony on 2016/10/24.
 *
 * pagerAdapter 基类
 */

public abstract class BasePagerAdapter<E> extends PagerAdapter{

    private final List<E> mDatas;

    public BasePagerAdapter(List<E> datas){
        mDatas = datas;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(position);
        container.addView(view);
        return view;
    }

    //只需returView即可
    public abstract View getView(int position) ;

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
