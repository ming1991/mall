package com.itheima31.jdmall.factory;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.fragment.DetailCommentFragment;
import com.itheima31.jdmall.fragment.DetailDetailFragment;
import com.itheima31.jdmall.fragment.DetailProductFragment;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 14:22
 * 描述：    TODO
 */
public class DetailFragmentFactory {

    //建立视图缓存类
    public static SparseArray<BaseFragment> mSparseArray = new SparseArray<>();

    public static final int FRAGMENT_PRODUCT      = 0;//首页
    public static final int FRAGMENT_DETAIL       = 1;//应用
    public static final int FRAGMENT_COMMENT      = 2;//游戏


    public static Fragment creatFragment(int position) {

        if (mSparseArray.get(position)!=null&&mSparseArray.size()!=0){
            return  mSparseArray.get(position);
        }

        BaseFragment fragment = null;
        switch (position) {
            case FRAGMENT_PRODUCT:
                fragment = new DetailProductFragment();
                break;
            case FRAGMENT_DETAIL:
                fragment = new DetailDetailFragment();
                break;
            case FRAGMENT_COMMENT:
                fragment = new DetailCommentFragment();
                break;

            default:
                break;
        }

        //保存到缓存数组中
        mSparseArray.put(position,fragment);

        return  fragment;
    }
}
