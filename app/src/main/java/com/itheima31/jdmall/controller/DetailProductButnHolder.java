package com.itheima31.jdmall.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.MainActivity;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.BalanceActivity;
import com.itheima31.jdmall.activity.LoginActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.bean.DialogBean;
import com.itheima31.jdmall.bean.ShopBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventJumpFragment;
import com.itheima31.jdmall.event.EventProductInfo;
import com.itheima31.jdmall.robot.RobotActivity;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.DetailProductDialog;
import com.jauker.widget.BadgeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 15:03
 * 描述：
 */
public class DetailProductButnHolder extends BaseController<DetailProductBean> {
    private  FragmentActivity mActivity;
    @InjectView(R.id.item_detail_product_bt_tv_service)
    TextView mItemDetailProductBtTvService;
    @InjectView(R.id.item_detail_product_bt_tv_collect)
    TextView mItemDetailProductBtTvCollect;
    @InjectView(R.id.item_detail_product_bt_tv_shop)
    TextView mItemDetailProductBtTvShop;
    @InjectView(R.id.item_detail_product_bt_tv_joinShop)
    TextView mItemDetailProductBtTvJoinShop;
    @InjectView(R.id.item_detail_product_bt_tv_paying)
    TextView mItemDetailProductBtTvPaying;
    public boolean isCollect = false;
    @InjectView(R.id.item_detail_tv_index_num)
    TextView mItemDetailTvIndexNum;
    private DetailProductBean mData;

    private int count = 0;
    private EventProductInfo mProductInfo;

    public DetailProductButnHolder(FragmentActivity activity) {

        mActivity = activity;

        //注册订阅者
        EventBus.getDefault().register(this);

    }

    //订阅者的回调方法
    @Subscribe
    public void onEvent(EventProductInfo e){

        //保存用户选择的信息
        mProductInfo = e;

        BadgeView badgeView = new BadgeView(UIUtils.getContext());

        //设置右上角的角标值
        badgeView.setTargetView(mItemDetailTvIndexNum);

        count = count + e.productNum;
        badgeView.setBadgeCount(count);

    }



    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_product_btn, null);


        return view;
    }

    @Override
    public void refresHolderView(DetailProductBean data) {
        mData = data;

       /* //设置右上角的通知的标记  布局不通过
       BadgeView badgeView = new BadgeView(UIUtils.getContext());

        badgeView.setTargetView(mItemDetailProductBtTvShop);
        badgeView.setBadgeCount(5);*/

    }

    @OnClick({R.id.item_detail_product_bt_tv_service, R.id.item_detail_product_bt_tv_collect, R.id.item_detail_product_bt_tv_shop, R.id.item_detail_product_bt_tv_joinShop, R.id.item_detail_product_bt_tv_paying})
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.item_detail_product_bt_tv_service: //客服

                intent = new Intent(mActivity, RobotActivity.class);
                mActivity.startActivity(intent);

                break;
            case R.id.item_detail_product_bt_tv_collect:
                isCollect = !isCollect;
                mItemDetailProductBtTvCollect.setSelected(isCollect);

                break;
            case R.id.item_detail_product_bt_tv_shop: //跳转到购物车

                EventBus.getDefault().post(new EventJumpFragment().setId(R.id.act_mian_rb_shopping_cart));
                mActivity.startActivity(new Intent(mActivity, MainActivity.class));

                break;
            case R.id.item_detail_product_bt_tv_joinShop: //加入购物车 ,跳出 dialog

                //不能使用getApplicationContext()获得的Context,而必须使用Activity,

                DetailProductDialog detailProductDialog = new DetailProductDialog(mActivity);
                detailProductDialog.setDialogData(mData);
                detailProductDialog.show();

              /*  ProductCommitClickListener dialogCommitClickListener = new ProductCommitClickListener();
                detailProductDialog.setOnDialogCommitClickListener(dialogCommitClickListener);*/


                break;
            case R.id.item_detail_product_bt_tv_paying:  //跳转到支付界面(结算中心) ,判断是否登录
                //获取userId
                String[] strings = SPUtils.getString(Constants.LOGED_ACCOUNT);
                String response = strings[0];
                String userid = "-1";



                if ("login".equals(response)) {  //已登录,跳到支付界面

                    if (mProductInfo==null||mProductInfo.productNum==0){
                        Toast.makeText(UIUtils.getContext(), "您还没有选择商品", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    userid = strings[1];

                    ShopBean shopBean = new ShopBean();
                    shopBean.num = mProductInfo.productNum;
                    shopBean.user_id = userid;
                    shopBean.Product_id =  mProductInfo.id;

                    //解析数据


                    if ("红色".equals(mProductInfo.color)){
                        shopBean.color = 1;
                    }else if ("绿色".equals(mProductInfo.color)){
                        shopBean.color = 2;
                    }


                    if ("M".equals(mProductInfo.size)){
                        shopBean.size = 3;
                    }else if ("XXL".equals(mProductInfo.size)){
                        shopBean.size = 4;
                    }else if ("XXXL".equals(mProductInfo.size)){
                        shopBean.size = 5;
                    }

                    intent = new Intent(mActivity, BalanceActivity.class);
                    intent.setAction(Constants.DETAILPRODUCT2BALANCE);
                    intent.putExtra(Constants.DETAILPRODUCT2BALANCE_KEY,shopBean);
                    mActivity.startActivity(intent);

                    mActivity.finish();

                } else { //进入登录界面
                    intent = new Intent(mActivity, LoginActivity.class);
                    mActivity.startActivity(intent);
                }

                break;
        }
    }

    //接口回调还回数据
    class ProductCommitClickListener implements DetailProductDialog.OnDialogCommitClickListener {

        @Override
        public void onDialogCommitClick(DialogBean dialogBean) {
            // Toast.makeText(UIUtils.getContext(), dialogBean.color, Toast.LENGTH_SHORT).show();

        }
    }


}
