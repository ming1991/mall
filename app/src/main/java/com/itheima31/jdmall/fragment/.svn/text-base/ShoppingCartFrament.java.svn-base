package com.itheima31.jdmall.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.MainActivity;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.BalanceActivity;
import com.itheima31.jdmall.activity.DetailActivity;
import com.itheima31.jdmall.activity.NewProductActivity;
import com.itheima31.jdmall.activity.TopPicActivity;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.ShopBean;
import com.itheima31.jdmall.bean.ShoppingCartBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventJumpShop;
import com.itheima31.jdmall.event.EventShoppingCartSizeChange;
import com.itheima31.jdmall.protocol.ShoppingProtocol;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.view.SpinnerView_color;
import com.itheima31.jdmall.view.SpinnerView_size;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tony on 2016/10/21.
 */

public class ShoppingCartFrament extends BaseFragment implements AdapterView.OnItemClickListener {


    int test;

    public static final int SHOPPINGCARTFRAGMENT = 150;


    //商品属性
    private ShoppingProtocol mShoppingProtocol;
    //当前购物车选中物品的总数
    public int sum=0;
    private List<ShoppingCartBean> mDatasList;
    private ShoppingCartBean tempBean;
    //private ViewHolder mViewHolder;
    private MyAdapter mMyAdapter;
    private CheckBox mShopping_sumcheckBox;
    private TextView mSum_tv;
    private Button mSum_btn;
    private int mSumPrice = 0;
    public int count = 0;
    private boolean mIsChange;
    //private static HashMap<Integer, Boolean> isSelected = new HashMap<>();
    private List<ShopBean> mShopBeanList = new ArrayList<>();
    public String curUser_id;
    public int curPosition;
    //控制EventBus只注册一次
    boolean isFirst=true;
    private RelativeLayout mCart_empty;



    public Map<String, Integer> loadDataFromSP(String user_id) {
        Map<String, Integer> allShopCart = SPUtils.getAllShopCart(MyApplication.getContext(), user_id);
        if(Integer.parseInt(user_id)!=-1){
            Map<String, Integer> allShopCart_1 = SPUtils.getAllShopCart(MyApplication.getContext(), -1+"");
            if(allShopCart_1==null){
                return allShopCart;
            }
            Set<String> keySet_1 = allShopCart_1.keySet();
            Iterator<String> iterator_1 = keySet_1.iterator();
            while (iterator_1.hasNext()){
                String key = iterator_1.next();
                if(allShopCart.containsKey(key)){
                    int num = allShopCart.get(key)+allShopCart_1.get(key);
                    //修改数据源
                    if(num<=10){
                        SPUtils.putToShopCart(MyApplication.getContext(),user_id,key,allShopCart_1.get(key));
                    }
                    SPUtils.deleteToShopCart(MyApplication.getContext(),-1+"",key);
                }else {
                    SPUtils.putToShopCart(MyApplication.getContext(),user_id,key,allShopCart_1.get(key));
                }
            }

        }
        allShopCart = SPUtils.getAllShopCart(MyApplication.getContext(), user_id);
        return allShopCart;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //((MainActivity)mActivity).onActivityResult(requestCode,resultCode,data);
        if(requestCode==SHOPPINGCARTFRAGMENT){
            mLoadingPager.triggerLoadData(true);
            initSuccessView();
        }

    }

    public LoadingPager.LoadedResultEnum initData() {


        //获取userId
        String[] strings = SPUtils.getString(Constants.LOGED_ACCOUNT);
        String response = strings[0];
        curUser_id = "-1";

        if ("login".equals(response)) {  //已登录

            curUser_id = strings[1];
        }

        //EventBus.getDefault().register();

        SystemClock.sleep(200);
        if (mDatasList == null) {//第一次打开购物车
            mDatasList = new ArrayList<>();
        } else {

        }

        //假数据 user_id product_id num color size
        /*String[] string = SPUtils.getString(Constants.LOGED_ACCOUNT);
        String curUserID =string[1];*/

       /* mShopBean = new ShopBean(-1 + "", 2, 2, 1, 3);
        curUser_id=-1+"";*/

        //SPUtils.putToShopCart(MyApplication.getContext(),mShopBean.user_id,mShopBean.toString(),mShopBean.num);


        mDatasList.clear();
        mShopBeanList.clear();
        mSumPrice = 0;
        //mShoppingProtocol = new ShoppingProtocol();
        Map<String, ?> dataFromSP = loadDataFromSP(curUser_id + "");
        if (dataFromSP == null || dataFromSP.size() == 0) {//购物车为空
            return LoadingPager.LoadedResultEnum.SUCCESS;
        }
        initmShopBeanList(dataFromSP);

        try {
            initmDatasList();
            return checkResData(mDatasList);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }

    }

    private void initmShopBeanList(Map<String, ?> dataFromSP) {
        Set<String> keySet = dataFromSP.keySet();
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // product_id   color  size
            String[] split = key.split(",");
            ShopBean bean = new ShopBean();
            bean.user_id = curUser_id + "";
            bean.Product_id = Integer.parseInt(split[0]);
            bean.color = Integer.parseInt(split[1]);
            bean.size = Integer.parseInt(split[2]);
            bean.num = Integer.parseInt(dataFromSP.get(key).toString());
            mShopBeanList.add(bean);
            sum+=bean.num;
        }
    }

    private void initmDatasList() throws IOException {
        for (int i = 0; i < mShopBeanList.size(); i++) {
            ShopBean bean = mShopBeanList.get(i);
            mShoppingProtocol = new ShoppingProtocol(bean.Product_id, bean.num, bean.color, bean.size);
            tempBean = mShoppingProtocol.loadData(0);
            if (checkResData(tempBean.cart) == LoadingPager.LoadedResultEnum.SUCCESS)
                mDatasList.add(tempBean);
        }
    }

    @Override
    public View initSuccessView() {

        if(isFirst){
            isFirst=false;
            EventBus.getDefault().register(this);
        }


        View view = View.inflate(MyApplication.getContext(), R.layout.shoppingcart_list, null);
        //JingDongListView jingDongListView = (JingDongListView) view.findViewById(R.id.shoppongcart_listview);
        ListView jingDongListView = (ListView) view.findViewById(R.id.shoppongcart_listview);
        jingDongListView.setOnItemClickListener(this);
        mShopping_sumcheckBox = (CheckBox) view.findViewById(R.id.shopping_sumcheckBox);
        mSum_btn = (Button) view.findViewById(R.id.shoppingcart_sum_btn);
        mSum_tv = (TextView) view.findViewById(R.id.shoppingcart_sum_tv);
        mCart_empty = (RelativeLayout) view.findViewById(R.id.cart_relative_empty);
        ImageView image_iv = (ImageView) view.findViewById(R.id.item_shoppingcart_image_iv);
        //mSum_tv.setText(mDatas.totalPrice + "");


        //
        //mMyAdapter = new MyAdapter(mDatasList, jingDongListView);
        mMyAdapter = new MyAdapter();
        jingDongListView.setAdapter(mMyAdapter);
        /*jingDongListView.setOnJingDongRefreshListener(new JingDongListView.OnJingDongRefreshListener() {
            @Override
            public void onRefresh() {
                SystemClock.sleep(1000);
            }

            @Override
            public void onRefreshCompleted() {
                Toast.makeText(MyApplication.getContext(), "完成刷新了", Toast.LENGTH_SHORT).show();
            }
        });*/


        //监听事件
        mShopping_sumcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    count=mDatasList.size();
                    sum=0;
                    for (int i = 0; i < mDatasList.size(); i++) {
                        sum+=mDatasList.get(i).totalCount;
                        mDatasList.get(i).cart.get(0).product.isChecked = true;
                        //mDatas.cart.get(i).product.isChecked = true;
                    }
                    EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                    mMyAdapter.notifyDataSetChanged();
                    ResetSumPrice();
                    mIsChange = false;
                } else {

                    if (mIsChange) {
                        mIsChange = false;
                        ResetSumPrice();
                        return;
                    }
                    count=0;
                    //Log.d("tag", " sumcheck count:" + count);
                    sum=0;
                    EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                    for (int i = 0; i < mDatasList.size(); i++) {
                        mDatasList.get(i).cart.get(0).product.isChecked = false;
                    }
                    //Log.d("tag", " sumcheck count:" + count);
                    mMyAdapter.notifyDataSetChanged();
                    ResetSumPrice();
                }

            }
        });
        mShopping_sumcheckBox.setChecked(true);
        count = mDatasList.size();
        for (int i = 0; i < mDatasList.size(); i++) {
            mSumPrice += mDatasList.get(i).totalPrice;
        }
        mSum_tv.setText(mSumPrice + "");
        mSum_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(curUser_id)==-1){
                    Toast.makeText(MyApplication.getContext(), "您还未登录，请先登录", Toast.LENGTH_SHORT).show();
                   /* Dialog dialog = new Dialog(mActivity);
                    dialog.setCanceledOnTouchOutside(true);
                    //dialog.set
                    //dialog
                    dialog.show();*/

                    AlertDialog.Builder builder=new AlertDialog.Builder(mActivity);  //先得到构造器
                    builder.setTitle("提示"); //设置标题
                    builder.setMessage("您还未登录，是否立即登录?"); //设置内容
                    builder.setIcon(R.mipmap.app_refresh_goods_0);//设置图标，图片id即可
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //new Intent(MyApplication.getContext(),mActivity.)
                             ((MainActivity)mActivity).changeFragment(R.id.act_mian_rb_mine);
                            dialog.dismiss(); //关闭dialog
                            Toast.makeText(mActivity, "确认" + which, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.create().show();
                    return;
                }
                List<ShopBean> mCheckedShopBeanList = new ArrayList<ShopBean>();

                for (int i = 0; i <mDatasList.size() ; i++) {
                    if(mDatasList.get(i).cart.get(0).product.isChecked){
                        mCheckedShopBeanList.add(mShopBeanList.get(i));
                        Log.d("tag","商品 num："+mShopBeanList.get(i).num);
                    }
                }
                Intent intent = new Intent(MyApplication.getContext(), BalanceActivity.class);
                intent.putExtra("checkedProduct", (Serializable) mCheckedShopBeanList);
                intent.setAction(Constants.SHOPCARTTOBALANCE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //随便逛逛
        Button empty_btn = (Button) view.findViewById(R.id.shopcart_empty_btn);
        empty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewProductActivity.class);

                startActivityForResult(intent,SHOPPINGCARTFRAGMENT);
            }
        });
        return view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("pId", mDatasList.get(position).cart.get(0).product.id);
        startActivityForResult(intent,SHOPPINGCARTFRAGMENT);
        //startActivity(intent);
    }




    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mDatasList.size();
        }

        @Override
        public Object getItem(int position) {
            return mDatasList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (mDatasList.size() == 0) {
                mCart_empty.setVisibility(View.VISIBLE);
            } else {
                mCart_empty.setVisibility(View.INVISIBLE);
            }

            ViewHolder vh;
            if (convertView == null) {
                convertView = View.inflate(MyApplication.getContext(), R.layout.item_shoppingcart_list, null);
                vh = new ViewHolder(convertView);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }

            ShoppingCartBean.CartBean cartBean = mDatasList.get(position).cart.get(0);
            vh.mItemShoppingcartNameTv.setText(cartBean.product.name);
            //vh.mItemShoppingcartColorTv.setText(cartBean.product.productProperty.get(0).v + "");
            vh.mItemShoppingcartColorTv.setText(cartBean.product.productProperty.get(0).v + "");
            vh.mItemShoppingcartNumTv.setText(cartBean.prodNum + "");
            vh.mItemShoppingcartPriceTv.setText(cartBean.product.price + "");
            Picasso.with(MyApplication.getContext()).load(Constants.URLS.BASEURL + cartBean.product.pic).into(vh.mItemShoppingcartImageIv);

            vh.mItemShoppingcartSizeTv.setText(cartBean.product.productProperty.get(1).v + "");
            vh.mItemShoppingcartHotIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), TopPicActivity.class);

                    startActivityForResult(intent,SHOPPINGCARTFRAGMENT);
                }
            });

            final ShoppingCartBean.CartBean bean = cartBean;


            vh.mShoppingCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (bean.product.isChecked == isChecked) {
                        return;
                    }

                    bean.product.isChecked = isChecked;


                    if (isChecked) {
                        sum+=bean.prodNum;
                        EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                        //buttonView.setTag(true);

                        Log.d("tag","check count: "+count);
                        count++;

                        Log.d("tag","check count: "+count);
                        if (count == mDatasList.size()) {
                            mShopping_sumcheckBox.setChecked(true);
                            ResetSumPrice();
                        } else {
                            ResetSumPrice();
                        }
                    } else {
                        sum-=bean.prodNum;
                        EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                        //成员变量置为false
                        //buttonView.setTag(false);

                        Log.d("tag","check count: "+count);
                        count--;
                        //从当前选中集合移除

                         Log.d("tag","check count: "+count);
                        mIsChange = true;
                        mShopping_sumcheckBox.setChecked(false);
                        ResetSumPrice();
                    }


                }

            });
            vh.mShoppingCheckBox1.setChecked(bean.product.isChecked);

            //增加物品数量
            vh.mItemShoppingcartAddIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ShoppingCartBean curBean = mDatasList.get(position);
                    ShopBean bean = mShopBeanList.get(mDatasList.indexOf(curBean));
                    if (curBean.totalCount >= 10) {
                        Toast.makeText(MyApplication.getContext(), "最多只能购买10件", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        sum++;
                        EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                        //由于一个curbean里面集合只有一个bean了.所以totalCount就是prodNum
                        curBean.totalCount++;
                        curBean.cart.get(0).prodNum++;
                        curBean.totalPrice += curBean.cart.get(0).product.price;
                        bean.num++;
                        //本地数据源的商品数量页跟着增加

                        SPUtils.putToShopCart(MyApplication.getContext(), curUser_id, bean.toString(), 1);

                        LinearLayout parent = (LinearLayout) v.getParent();
                        TextView tv = (TextView) parent.findViewById(R.id.item_shoppingcart_num_tv);
                        tv.setText(curBean.cart.get(0).prodNum + "");


                        if (curBean.cart.get(0).product.isChecked) {
                            ResetSumPrice();
                        }

                        //mMyAdapter.notifyDataSetChanged();

                    }
                }
            });

            //减少物品数量
            vh.mShoppingCartJianIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    ShoppingCartBean curBean = mDatasList.get(position);
                    ShopBean bean = mShopBeanList.get(position);
                    if (curBean.cart.get(0).prodNum <= 1) {
                        Toast.makeText(MyApplication.getContext(), "无法再减少", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        sum--;
                        EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                        curBean.cart.get(0).prodNum--;
                        curBean.totalCount--;
                        curBean.totalPrice -= curBean.cart.get(0).product.price;

                        bean.num--;
                        //本地数据源的商品数量页跟着减少
                        SPUtils.putToShopCart(MyApplication.getContext(), bean.user_id, bean.toString(), -1);
                        LinearLayout parent = (LinearLayout) v.getParent();
                        TextView tv = (TextView) parent.findViewById(R.id.item_shoppingcart_num_tv);
                        tv.setText(curBean.cart.get(0).prodNum + "");

                        if (curBean.cart.get(0).product.isChecked) {
                            ResetSumPrice();
                        }
                        //mMyAdapter.notifyDataSetChanged();
                    }
                }
            });

            //删除该物品
            vh.mItemShoppingcartDeleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("tag","count:"+count);
                    count--;
                    Log.d("tag","count:"+count);
                    ShoppingCartBean curBean = mDatasList.get(position);
                    //本地数据源的商品数量页跟着减少
                    ShopBean bean = mShopBeanList.get(position);
                    sum-=curBean.totalCount;
                    EventBus.getDefault().post(new EventShoppingCartSizeChange(sum));
                    //移除bean
                    mDatasList.remove(curBean);
                    mShopBeanList.remove(bean);
                    //SPUtils.putToShopCart(MyApplication.getContext(),curUser_id,bean.toString(),-curBean.totalCount);
                    SPUtils.deleteToShopCart(MyApplication.getContext(), bean.user_id, bean.toString());
                    if (mDatasList.size() == 0) {
                        mCart_empty.setVisibility(View.VISIBLE);
                    } else {
                        mCart_empty.setVisibility(View.INVISIBLE);
                    }
                    ResetSumPrice();
                    mMyAdapter.notifyDataSetChanged();
                }
            });
            convertView.setScaleX(0.6f);
            convertView.setScaleY(0.5f);
            ViewCompat.animate(convertView).scaleX(1).scaleY(1).setDuration(400)
                    .setInterpolator(new OvershootInterpolator(4))//
                    .start();//

            return convertView;
        }


        class ViewHolder {
            @InjectView(R.id.shopping_checkBox1)
            CheckBox mShoppingCheckBox1;
            @InjectView(R.id.item_shoppingcart_image_iv)
            ImageView mItemShoppingcartImageIv;
            @InjectView(R.id.item_shoppingcart_name_tv)
            TextView mItemShoppingcartNameTv;
            @InjectView(R.id.item_shoppingcart_color_tv)
            SpinnerView_color mItemShoppingcartColorTv;
            @InjectView(R.id.item_shoppingcart_price_tv)
            TextView mItemShoppingcartPriceTv;
            @InjectView(R.id.item_shoppingcart_size_tv)
            SpinnerView_size mItemShoppingcartSizeTv;
            @InjectView(R.id.item_shoppingcart_delete_iv)
            ImageView mItemShoppingcartDeleteIv;
            @InjectView(R.id.shopping_cart_jian_iv)
            ImageView mShoppingCartJianIv;
            @InjectView(R.id.item_shoppingcart_num_tv)
            TextView mItemShoppingcartNumTv;
            @InjectView(R.id.item_shoppingcart_add_iv)
            ImageView mItemShoppingcartAddIv;
            @InjectView(R.id.item_shopping_cart_hot)
            ImageView mItemShoppingcartHotIv;

            ViewHolder(View view) {
                ButterKnife.inject(this, view);
            }
        }


        private void ResetSumPrice() {
            int sum = 0;
            for (int i = 0; i < mDatasList.size(); i++) {
                if (mDatasList.get(i).cart.get(0).product.isChecked) {
                    sum += mDatasList.get(i).totalPrice;
                }
            }
            mSum_tv.setText(sum + "");
        }


    }


    private void ResetSumPrice() {
        int sum = 0;
        for (int i = 0; i < mDatasList.size(); i++) {
            if (mDatasList.get(i).cart.get(0).product.isChecked) {
                sum += mDatasList.get(i).totalPrice;
            }
        }
        mSum_tv.setText(sum + "");
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void jumpFragment(EventJumpShop event) {
        if(event.cs_id==Constants.COLOR_RED){
            mDatasList.get(curPosition).cart.get(0).product.productProperty.get(0).v=Constants.COLOR_RED+"";
            int num = mShopBeanList.get(curPosition).num;
            SPUtils.deleteToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString());
            mShopBeanList.get(curPosition).color=Constants.COLOR_RED;
            SPUtils.putToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString(),num);
        }else if(event.cs_id==Constants.COLOR_GREEN){
            mDatasList.get(curPosition).cart.get(0).product.productProperty.get(0).v=Constants.COLOR_GREEN+"";
            int num = mShopBeanList.get(curPosition).num;
            SPUtils.deleteToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString());
            mShopBeanList.get(curPosition).color=Constants.COLOR_GREEN;
            SPUtils.putToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString(),num);
        }else if(event.cs_id==Constants.SIZE_M){
            mDatasList.get(curPosition).cart.get(0).product.productProperty.get(1).v=Constants.SIZE_M+"";
            int num = mShopBeanList.get(curPosition).num;
            SPUtils.deleteToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString());
            mShopBeanList.get(curPosition).size=Constants.SIZE_M;
            SPUtils.putToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString(),num);
        }else if(event.cs_id==Constants.SIZE_XXL){
            mDatasList.get(curPosition).cart.get(0).product.productProperty.get(0).v=Constants.SIZE_XXL+"";
            int num = mShopBeanList.get(curPosition).num;
            SPUtils.deleteToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString());
            mShopBeanList.get(curPosition).size=Constants.SIZE_XXL;
            SPUtils.putToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString(),num);
        }else if(event.cs_id==Constants.SIZE_XXXL){
            mDatasList.get(curPosition).cart.get(0).product.productProperty.get(0).v=Constants.SIZE_XXXL+"";
            int num = mShopBeanList.get(curPosition).num;
            SPUtils.deleteToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString());
            mShopBeanList.get(curPosition).size=Constants.SIZE_XXXL;
            SPUtils.putToShopCart(mActivity,curUser_id,mShopBeanList.get(curPosition).toString(),num);
        }
    }
}
