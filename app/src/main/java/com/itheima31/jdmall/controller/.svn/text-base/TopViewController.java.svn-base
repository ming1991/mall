package com.itheima31.jdmall.controller;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.HomeBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.AutoScrollUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.ChildViewPager;
import com.squareup.picasso.Picasso;

import butterknife.InjectView;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/24 11:23
 * 描    述：
 */


public class TopViewController extends BaseController<HomeBean> {
    @InjectView(R.id.item_home_picture_pager)
    ChildViewPager mItemHomePicturePager;
    @InjectView(R.id.item_home_picture_container_indicator)
    LinearLayout   mItemHomePictureContainerIndicator;
    private HomeBean mData;

    @Override
    public View getView() {
        return View.inflate(UIUtils.getContext(), R.layout.item_home_top_news, null);
    }


    @Override
    public void refresHolderView(HomeBean data) {

        mData = data;
        //绑定mItemHomePicturePager对应的数据
        mItemHomePicturePager.setAdapter(new MyPagerAdapter());

        //绑定mItemHomePictureContainerIndicator对应的数据

        for (int i = 0; i < mData.homeTopic.size(); i++) {
            ImageView ivIndicator = new ImageView(UIUtils.getContext());
            ivIndicator.setImageResource(R.drawable.indicator_normal);


            int width = UIUtils.dip2px(6);//6dp
            int height = UIUtils.dip2px(6);//6dp
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.bottomMargin = UIUtils.dip2px(6);//6dp
            params.leftMargin = UIUtils.dip2px(6);//6dp
            mItemHomePictureContainerIndicator.addView(ivIndicator, params);

            if (i == 0) {//默认第一个是选中的
                ivIndicator.setImageResource(R.drawable.indicator_selected);
            }
        }

        mItemHomePicturePager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //处理position
                position = position % mData.homeTopic.size();

                for (int i = 0; i < mData.homeTopic.size(); i++) {
                    //还原成默认的
                    ImageView ivIndicator = (ImageView) mItemHomePictureContainerIndicator.getChildAt(i);
                    ivIndicator.setImageResource(R.drawable.indicator_normal);
                    //选中应该选中的
                    if (i == position) {
                        ivIndicator.setImageResource(R.drawable.indicator_selected);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //左右无限轮播
        mItemHomePicturePager.setCurrentItem(5000);

        //自动轮播
        final AutoScrollUtils autoScrollTask = new AutoScrollUtils(mItemHomePicturePager);
        autoScrollTask.start();

    }



    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mData.homeTopic != null) {
                return 9999;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //处理position
            position = position % mData.homeTopic.size();

            //view-->ImageView
            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //data
            String url = mData.homeTopic.get(position).pic;

            //data+view
            Picasso.with(UIUtils.getContext()).load(Constants.URLS.BASEURL + url).into(iv);

            //加入容器
            container.addView(iv);

            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
