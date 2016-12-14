package com.itheima31.jdmall.factory;

import android.content.Context;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.fragment.ClassifyFragment;
import com.itheima31.jdmall.fragment.DiscoverFrament;
import com.itheima31.jdmall.fragment.HomeFrament;
import com.itheima31.jdmall.fragment.MineFragment;
import com.itheima31.jdmall.fragment.ShoppingCartFrament;

import java.util.HashMap;

/**
 * Created by Tony on 2016/10/23.
 * 维护在今天的map
 */

public class FragmentFactory {

    private static HashMap<Integer, BaseFragment> mHm = new HashMap<>();


    public static BaseFragment getFragment(Context context, int checkedId) {

        BaseFragment baseFragment = mHm.get(checkedId);

        if (baseFragment != null) {
            return baseFragment;
        }
        switch (checkedId) {
            case R.id.act_mian_rb_home:
                baseFragment = new HomeFrament();
                break;
            case R.id.act_mian_rb_classify:
                baseFragment = new ClassifyFragment();
                break;
            case R.id.act_mian_rb_discover:
                baseFragment = new DiscoverFrament();
                break;
            case R.id.act_mian_rb_shopping_cart:
                baseFragment = new ShoppingCartFrament();
                break;
            case R.id.act_mian_rb_mine:
                baseFragment = new MineFragment();
                break;
        }

        mHm.put(checkedId, baseFragment);
        return baseFragment;
    }


}
