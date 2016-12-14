package com.itheima31.jdmall.controller;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.bean.DialogBean;
import com.itheima31.jdmall.event.EventProductInfo;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.DetailProductDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.InjectView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 17:00
 * 描述：    TODO
 */
public class DetailProductSelectHolder extends BaseController<DetailProductBean> {
    private static final String TAG = "DetailProdu";
    private final FragmentActivity mActivity;

    @InjectView(R.id.item_detail_product_select_tv_newPrice)
    TextView mItemDetailProductSelectTvNewPrice;
    @InjectView(R.id.item_detail_product_des_tv_oldPrice)
    TextView mItemDetailProductDesTvOldPrice;
    @InjectView(R.id.item_detail_product_select_iv_pay)
    ImageView mItemDetailProductSelectIvPay;
    @InjectView(R.id.item_detail_product_select_tv_selected)
    TextView mItemDetailProductSelectTvSelected;
    @InjectView(R.id.item_detail_product_select_tv_showSelected)
    TextView mItemDetailProductSelectTvShowSelected;
    @InjectView(R.id.item_detail_product_select_iv_select)
    ImageView mItemDetailProductSelectIvSelect;
    private DetailProductBean mData;

    public DetailProductSelectHolder(FragmentActivity activity) {
        mActivity = activity;

        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(EventProductInfo event) {

        mItemDetailProductSelectTvShowSelected.setText("颜色:"+event.color
                +"  尺寸:"+event.size +"  数量:"+event.productNum+"件");

    }

    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_product_select, null);




        return view;
    }

    @Override
    public void refresHolderView(DetailProductBean data) {

        mData = data;

        //设置点击事件
        IvSelectOnClickListener selectOnClickListener = new IvSelectOnClickListener();
        mItemDetailProductSelectIvSelect.setOnClickListener(selectOnClickListener);

        mItemDetailProductSelectIvPay.setOnClickListener(selectOnClickListener);


    }

    class IvSelectOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_detail_product_select_iv_select: //跳出选择商品颜色和尺寸的对话框

                    //不能使用getApplicationContext()获得的Context,而必须使用Activity,
                    DetailProductDialog detailProductDialog = new DetailProductDialog(mActivity);
                    detailProductDialog.setDialogData(mData);
                    detailProductDialog.show();

                   /* CommitClickListener dialogCommitClickListener = new CommitClickListener();
                    detailProductDialog.setOnDialogCommitClickListener(dialogCommitClickListener);*/

                    break;
                case R.id.item_detail_product_select_iv_pay:
                    Toast.makeText(UIUtils.getContext(), "暂未开通", Toast.LENGTH_SHORT).show();
                break;

                default:
                    break;
            }
        }
    }

    class CommitClickListener implements DetailProductDialog.OnDialogCommitClickListener {
        @Override
        public void onDialogCommitClick(DialogBean dialogBean) {

            mItemDetailProductSelectTvShowSelected.setText("颜色:"+dialogBean.color
                    +"  尺寸:"+dialogBean.size +"  数量:"+dialogBean.count+"件");

        }
    }
}
