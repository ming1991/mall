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

import com.itheima31.jdmall.MainActivity;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.TopicBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventJumpFragment;
import com.itheima31.jdmall.protocol.ToppicProtocol;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.TimeView;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TopPicActivity extends BaseActivity implements View.OnClickListener {
    private List<TopicBean.ProductListBean> mListBeen;
    private TopicBean mDatas;
    private MyAdapter mMyAdapter;

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
        ToppicProtocol toppicProtocol = new ToppicProtocol();
        try {
            mDatas = toppicProtocol.loadData(0);
            mListBeen = mDatas.getProductList();
            return ResultUtils.checkResData(mListBeen);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }

    }

    @Override
    public View onInitSuccessView() {
        View view = View.inflate(MyApplication.getContext(), R.layout.activity_top_pic, null);
        ImageView back = (ImageView) view.findViewById(R.id.actionbar_home_layout_back);
        ImageView car = (ImageView) view.findViewById(R.id.actionbar_message_car);
        ImageView go = (ImageView) view.findViewById(R.id.go);
        GridView gv = (GridView) view.findViewById(R.id.show_gvs);
        TimeView timeView = new TimeView(UIUtils.getContext());
        timeView.reStart(1000);
        back.setOnClickListener(this);
        car.setOnClickListener(this);
        go.setOnClickListener(this);
        mMyAdapter = new MyAdapter();
        gv.setAdapter(mMyAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TopPicActivity.this, DetailActivity.class);
                intent.putExtra("pId", mListBeen.get(position).id);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_home_layout_back:
                finish();
                break;
            case R.id.actionbar_message_car:

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
            case R.id.go:

                break;
        }
    }

    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (mListBeen != null) {
                return mListBeen.size();
            }
            return 0;
        }

        @Override
        public TopicBean.ProductListBean getItem(int position) {
            return mListBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TopPicActivity.MyAdapter.ViewHolder viewHolder;
            if (convertView == null) {

                convertView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_show_gvs, null);

                viewHolder = new TopPicActivity.MyAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (TopPicActivity.MyAdapter.ViewHolder) convertView.getTag();
            }

            TopicBean.ProductListBean productListBean = mListBeen.get(position);
            String url = Constants.URLS.BASEURL + productListBean.pic;
            Picasso.with(UIUtils.getContext()).load(url).into(viewHolder.mImageView);
            viewHolder.mName.setText(productListBean.name);
            viewHolder.mPrice.setText("¥ " + productListBean.marketPrice);
            viewHolder.mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            viewHolder.mLimitPrice.setText("¥ " + productListBean.price);

            return convertView;
        }

        private class ViewHolder {
            ImageView mImageView;
            TextView mName;
            TextView mPrice;
            TextView mLimitPrice;

            ViewHolder(View root) {
                mImageView = (ImageView) root.findViewById(R.id.show_iv);
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
