package com.itheima31.jdmall.controller;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.HotProductActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.TopicBean;
import com.itheima31.jdmall.utils.AutoScrollUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.ChildViewPager;

import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/25.
 */

public class RecommendBaseController extends BaseController<TopicBean> {
    @InjectView(R.id.ImageView03)
    ImageView mImageView03;
    @InjectView(R.id.tv_recom_subtitle)
    TextView  mTvRecomSubtitle;

    @InjectView(R.id.ImageView02)
    ImageView mImageView02;
    @InjectView(R.id.tv_recom_subtitle2)
    TextView  mTvRecomSubtitle2;

    @InjectView(R.id.ImageView04)
    ImageView mImageView04;
    @InjectView(R.id.tv_recom_subtitle3)
    TextView  mTvRecomSubtitle3;

    @InjectView(R.id.ImageView01)
    ImageView mImageView01;
    @InjectView(R.id.tv_recom_subtitle4)
    TextView  mTvRecomSubtitle4;

    @InjectView(R.id.imageView1)
    ImageView mImageView1;
    @InjectView(R.id.tv_recom_subtitle5)
    TextView  mTvRecomSubtitle5;

    @InjectView(R.id.goodThing_vp)
    ChildViewPager mGoodThingVp;

    public int[] images = new int[]{R.drawable.img_home_banner1, R.drawable.img_home_banner2, R.drawable.img_home_banner3, R.drawable.img_home_banner4, R.drawable.img_home_banner5, R.drawable.img_home_banner6, R.drawable.img_home_banner7, R.drawable.img_home_banner8, R.drawable.img_home_banner9};

    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_home_good_thing_recommend, null);
        view.findViewById(R.id.layout_recom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UIUtils.getContext(),HotProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UIUtils.getContext().startActivity(intent);
                            }
        });
        return view;
    }

    @Override
    public void refresHolderView(TopicBean data) {
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
        mGoodThingVp.setAdapter(myPagerAdapter);

        mGoodThingVp.setCurrentItem(5000);
        AutoScrollUtils utils=new AutoScrollUtils(mGoodThingVp);
        utils.start();

    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position = position % images.length;
            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setImageResource(images[position]);
            container.addView(iv);

            return iv;
        }

        @Override
        public int getCount() {
            return 9999;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }
    }
}
