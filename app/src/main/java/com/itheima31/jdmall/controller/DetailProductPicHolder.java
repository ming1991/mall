package com.itheima31.jdmall.controller;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.PhotoViewActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 15:30
 * 描述：    TODO
 */
public class DetailProductPicHolder extends BaseController<DetailProductBean> {

    @InjectView(R.id.item_detail_product_viewPager)
    ViewPager mItemDetailProductViewPager;
    @InjectView(R.id.item_detail_product_tv_pagePosition)
    TextView mItemDetailProductTvPagePosition;

    private List<String> mPicsList;
    private ArrayList<String> mBigPicList;

    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_product_pic, null);

        return view;
    }

    @Override
    public void refresHolderView(DetailProductBean data) {

        //商品图
        mPicsList = data.product.pics;

        //商品大图
        mBigPicList = data.product.bigPic;

        DetailProductPagerAdapter detailProductPagerAdapter = new DetailProductPagerAdapter();

        mItemDetailProductViewPager.setAdapter(detailProductPagerAdapter);


        mItemDetailProductTvPagePosition.setText(1 + "/" +mPicsList.size() );
        ViewpagerOnPageChangeListener onPageChangeListener = new ViewpagerOnPageChangeListener();
        mItemDetailProductViewPager.setOnPageChangeListener(onPageChangeListener);


    }

    class ViewpagerOnPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //获取集合的个数
            mItemDetailProductTvPagePosition.setText(position + 1 + "/" +mPicsList.size() );

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class ImageViewOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) { //跳转到浏览大图的界面

            Intent intent = new Intent(UIUtils.getContext(), PhotoViewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //mBigPicList
            intent.putStringArrayListExtra("BigPicList",mBigPicList);

            UIUtils.getContext().startActivity(intent);

        }
    }


    class DetailProductPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPicsList == null ? 0 : mPicsList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(UIUtils.getContext());

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            String shortUrl = mPicsList.get(position);
            String url = Constants.URLS.BASEURL + shortUrl;
            Picasso.with(UIUtils.getContext()).load(url).into(imageView);


            imageView.setOnClickListener(new ImageViewOnClickListener());

            container.addView(imageView);
            return imageView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
