package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.LimitbuyBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.LimitbuyProtocol;
import com.itheima31.jdmall.utils.LogUtils;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.utils.StringUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.FilterDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ProductlistActivity extends BaseActivity implements View.OnClickListener {
    public static String mType = "shelvesDown";
    private LimitbuyBean mDatas;
    private List<LimitbuyBean.ProductListBean> mListBeen;
    private TextView mTotal;
    private TextView mHotSell;
    private TextView mPrice;
    private TextView mScreen;
    private ImageView mBack;
    private ImageView mSelect;
    private GridView mGvlist;
    private ListView mListView;
    private boolean isgv = true;
    private boolean xml = true;
    private MyAdapter mMyAdapter;
    private PopupWindow mPop;
    private Button mUp;
    private Button mDown;
    public static String mKey = null;
    public static boolean isnull = false;
    private EditText mEdit;

    @Override
    public void init() {

        mKey = getIntent().getStringExtra(SearchActivity.KEY_TEXT);
        if (mKey != null) {
            isnull = false;
        } else {
            isnull = true;
        }
        super.init();
    }

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {

        LimitbuyProtocol limitbuyProtocol = new LimitbuyProtocol(isnull);

        try {
            mDatas = limitbuyProtocol.loadData(0);
            mListBeen = mDatas.getProductList();

            if (mListBeen.size() == 0) {
                mDatas = new LimitbuyProtocol(true).loadData(0);
                mListBeen = mDatas.getProductList();
            }

            for (int i = 0; i < mListBeen.size(); i++) {
                LogUtils.d(mListBeen.get(i).getName());
            }

            return ResultUtils.checkResData(mListBeen);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }


    }

    @Override
    public View onInitSuccessView() {
        View view = View.inflate(MyApplication.getContext(), R.layout.activity_limit_buy, null);
        mEdit = (EditText) view.findViewById(R.id.actionbar_home_layout_edit_text);
        mBack = (ImageView) view.findViewById(R.id.actionbar_home_layout_scan);
        mSelect = (ImageView) view.findViewById(R.id.actionbar_message_iv);
        mTotal = (TextView) view.findViewById(R.id.total_tv);
        mHotSell = (TextView) view.findViewById(R.id.hotSell_tv);
        mPrice = (TextView) view.findViewById(R.id.price_tv);
        mScreen = (TextView) view.findViewById(R.id.screen_tv);
        mEdit.setOnClickListener(this);

        if (!StringUtils.isEmpty(mKey)) {
            mEdit.setText(mKey);
            mEdit.setTextColor(Color.parseColor("#9c9c9c"));
        }
        mBack.setOnClickListener(this);
        mSelect.setOnClickListener(this);
        mTotal.setOnClickListener(this);
        mHotSell.setOnClickListener(this);
        mPrice.setOnClickListener(this);
        mScreen.setOnClickListener(this);
        mGvlist = (GridView) view.findViewById(R.id.show_gv);
        mListView = (ListView) view.findViewById(R.id.show_lv);


        // 引入窗口配置文件
        View view2 = View.inflate(MyApplication.getContext(), R.layout.pop, null);
        mUp = (Button) view2.findViewById(R.id.up);
        mDown = (Button) view2.findViewById(R.id.down);
        mUp.setOnClickListener(this);
        mDown.setOnClickListener(this);
        // 创建PopupWindow对象
        mPop = new PopupWindow(view2, 300, 200, false);
        // 需要设置一下此参数，点击外边可消失
        mPop.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        mPop.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mPop.setFocusable(true);

        mMyAdapter = new MyAdapter();
        mGvlist.setAdapter(mMyAdapter);
        mGvlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductlistActivity.this, DetailActivity.class);
                intent.putExtra("pId", mListBeen.get(position).id);
                startActivity(intent);
            }
        });
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductlistActivity.this, DetailActivity.class);
                intent.putExtra("pId", mListBeen.get(position).id);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_home_layout_edit_text:
                startActivity(new Intent(ProductlistActivity.this, SearchActivity.class));
                finish();
                break;
            case R.id.actionbar_home_layout_scan:
                finish();
                break;
            case R.id.actionbar_message_iv:
                if (isgv) {
                    mSelect.setImageDrawable(getResources().getDrawable(R.drawable.icon_pic_grid_type));
                    mGvlist.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
                    isgv = !isgv;
                    xml = false;
                } else {
                    mSelect.setImageDrawable(getResources().getDrawable(R.drawable.bar_class_normal));
                    mGvlist.setVisibility(View.VISIBLE);
                    mListView.setVisibility(View.GONE);
                    isgv = !isgv;
                    xml = true;
                }
                break;
            case R.id.total_tv:
                mTotal.setTextColor(getResources().getColor(R.color.colorAccent));
                mHotSell.setTextColor(getResources().getColor(R.color.appColorLessImportantr));
                mPrice.setTextColor(getResources().getColor(R.color.appColorLessImportantr));
                mType = "shelvesDown";

                ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                    @Override
                    public void run() {
                        onInitData();
                        UIUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });


                break;
            case R.id.hotSell_tv:
                //mHotSell.setTextColor(Color.RED);
                mHotSell.setTextColor(getResources().getColor(R.color.colorAccent));
                mTotal.setTextColor(getResources().getColor(R.color.appColorLessImportantr));
                mPrice.setTextColor(getResources().getColor(R.color.appColorLessImportantr));
                mType = "saleDown";
                ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                    @Override
                    public void run() {
                        onInitData();
                        UIUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                break;
            case R.id.price_tv:
                mPrice.setTextColor(getResources().getColor(R.color.colorAccent));
                mTotal.setTextColor(getResources().getColor(R.color.appColorLessImportantr));
                mHotSell.setTextColor(getResources().getColor(R.color.appColorLessImportantr));
                mPop.showAsDropDown(v);
                break;
            case R.id.up:
                mType = "priceUp";
                ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                    @Override
                    public void run() {
                        onInitData();
                        UIUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                break;
            case R.id.down:
                mType = "priceDown";
                ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                    @Override
                    public void run() {
                        onInitData();
                        UIUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                mMyAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
                break;
            case R.id.screen_tv:
                new FilterDialog(this).show();
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
        public LimitbuyBean.ProductListBean getItem(int position) {
            return mListBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                if (xml) {
                    convertView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_show_gv, null);
                } else {
                    convertView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_show_lv, null);
                }
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            LimitbuyBean.ProductListBean productListBean = mListBeen.get(position);
            String url = Constants.URLS.BASEURL + productListBean.pic;
            Picasso.with(UIUtils.getContext()).load(url).into(viewHolder.mImageView);

            if (isnull) {
                viewHolder.mName.setText("韩版时尚酷炫潮流" + productListBean.name);
                viewHolder.mPrice.setText("原价 ¥ " + productListBean.marketPrice);
                viewHolder.mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.mLimitPrice.setText("火爆价 ¥ " + productListBean.price);
            } else {
                viewHolder.mName.setText("" + productListBean.name);
                viewHolder.mPrice.setText("" + productListBean.marketPrice);
                viewHolder.mPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                viewHolder.mLimitPrice.setText("" + productListBean.price);
            }
            convertView.setScaleX(0.6f);
            convertView.setScaleY(0.5f);

            ViewCompat.animate(convertView).scaleX(1).scaleY(1).setDuration(400)
                    .setInterpolator(new OvershootInterpolator(4))//
                    .start();//
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
                TextView pl = (TextView) root.findViewById(R.id.pltv);
                TextView hp = (TextView) root.findViewById(R.id.hptv);
                Random random = new Random();
                pl.setText(random.nextInt(1500) + 500 + "条评论");
                hp.setText("好评" + (random.nextInt(50) + 50) + "%");
            }
        }
    }
}
