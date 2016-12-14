package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itheima31.jdmall.MainActivity;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.NewProductBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventJumpFragment;
import com.itheima31.jdmall.protocol.NewProductProtocol;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.TimeView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class NewProductActivity extends BaseActivity implements View.OnClickListener {



    private NewProductBean mDatas;
    private List<NewProductBean.ProductListBean> mListBeen;


    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
        NewProductProtocol newProductProtocol = new NewProductProtocol();
        try {
            mDatas = newProductProtocol.loadData(0);
            mListBeen = mDatas.productList;
            return ResultUtils.checkResData(mListBeen);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    @Override
    public View onInitSuccessView() {
        View view = View.inflate(MyApplication.getContext(), R.layout.activity_new_product, null);
        ImageView back = (ImageView) view.findViewById(R.id.actionbar_home_layout_back1);
        ImageView car = (ImageView) view.findViewById(R.id.actionbar_message_car1);
        ImageView go = (ImageView) view.findViewById(R.id.go1);
        GridView gridView = (GridView) view.findViewById(R.id.new_gridview);
        TimeView timeView = new TimeView(UIUtils.getContext());
        timeView.reStart(1000);
        back.setOnClickListener(this);
        car.setOnClickListener(this);
        go.setOnClickListener(this);

        gridView.setAdapter(new MaAdapter() );
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewProductActivity.this, DetailActivity.class);
                intent.putExtra("pId", mListBeen.get(position).id);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_home_layout_back1:
                finish();
                break;
            case R.id.actionbar_message_car1:

                UIUtils.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventJumpFragment jump = new EventJumpFragment();
                        jump.fragmentId = R.id.act_mian_rb_shopping_cart;
                        EventBus.getDefault().post(jump);
                    }
                }, 0);

                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.go1:

                break;
        }
    }

    class MaAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if(mListBeen==null){
                return 0;
            }
            return mListBeen.size();
        }

        @Override
        public NewProductBean.ProductListBean getItem(int position) {
            return mListBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {

                convertView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_show_newproduct, null);

                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            NewProductBean.ProductListBean productListBean = mListBeen.get(position);
            String url = Constants.URLS.BASEURL + productListBean.pic;
            //Picasso.with(UIUtils.getContext()).load(url).into(viewHolder.mImageView);
            //Picasso.with(UIUtils.getContext()).load(url).into(viewHolder.mCircleImageView);

            Glide.with(UIUtils.getContext()).load(url)
                    .bitmapTransform(new BlurTransformation(UIUtils.getContext(), 25))
                    .into(viewHolder.mImageView);

            Glide.with(UIUtils.getContext()).load(url)
                    .bitmapTransform(new CropCircleTransformation(UIUtils.getContext()))
                    .into(viewHolder.mCircleImageView);

            //Glide.with(UIUtils.getContext()).load(url).into(viewHolder.mCircleImageView);
            viewHolder.mName.setText(productListBean.name);
            viewHolder.mPrice.setText("¥ " + productListBean.marketPrice);
            viewHolder.mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            viewHolder.mLimitPrice.setText("¥ " + productListBean.price);

            return convertView;
        }

        private class ViewHolder {
            ImageView mImageView;
            ImageView mCircleImageView;
            TextView mName;
            TextView mPrice;
            TextView mLimitPrice;

            ViewHolder(View root) {
                mImageView = (ImageView) root.findViewById(R.id.newproduct_iv);
                mCircleImageView = (ImageView) root.findViewById(R.id.newproduct_circleiv);
                mName = (TextView) root.findViewById(R.id.show_desc);
                mPrice = (TextView) root.findViewById(R.id.show_price);
                mLimitPrice = (TextView) root.findViewById(R.id.show_linitprice);
                TextView jian = (TextView) root.findViewById(R.id.jianshu);
                Random random = new Random();
                jian.setText(random.nextInt(80) + 20 + "");
            }
        }
    }

}
