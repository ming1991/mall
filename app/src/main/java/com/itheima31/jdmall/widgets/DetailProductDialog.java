package com.itheima31.jdmall.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.bean.DialogBean;
import com.itheima31.jdmall.bean.ShopBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventProductInfo;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 20:24
 * 描述：
 */


public class DetailProductDialog extends Dialog {
    @InjectView(R.id.dialog_product_icon)
    ImageView mDialogProductIcon;
    @InjectView(R.id.dialog_product_price)
    TextView mDialogProductPrice;
    @InjectView(R.id.dialog_product_back)
    ImageView mDialogProductBack;
    @InjectView(R.id.dialog_product_color_red)
    TextView mDialogProductColorRed;
    @InjectView(R.id.dialog_product_color_green)
    TextView mDialogProductColorGreen;
    @InjectView(R.id.dialog_product_size_m)
    TextView mDialogProductSizeM;
    @InjectView(R.id.dialog_product_size_xxl)
    TextView mDialogProductSizeXxl;
    @InjectView(R.id.dialog_product_size_xxxl)
    TextView mDialogProductSizeXxxl;
    @InjectView(R.id.dialog_product_jian)
    TextView mDialogProductJian;
    @InjectView(R.id.dialog_product_num)
    TextView mDialogProductNum;
    @InjectView(R.id.dialog_product_jia)
    TextView mDialogProductJia;
    @InjectView(R.id.dialog_product_ok)
    TextView mDialogProductOk;

    private int count = 1;
    //红色为1,绿色为2
    private int color = 0;
    //m为3,xxl为4,xxxl为5
    private int size = 0;
    private DetailProductBean mDetailProductBean;

    public DetailProductDialog(Context context) {

        //进出场的动画主题theme
        this(context, R.style.DialoyTheme);
    }

    public DetailProductDialog(Context context, int themeResId) {
        super(context, themeResId);

        //设置内容
        setContentView(R.layout.view_detail_dialog);

        //手动绑定
        ButterKnife.inject(this);

        initProperty();


    }

    private void initProperty() {
        //设置显示在屏幕上的属性
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.dimAmount = 1.0f;
        attributes.alpha = 1;

        //显示在底部
        attributes.gravity = Gravity.BOTTOM;

        //点击dialog外部，让它消失
        setCanceledOnTouchOutside(true);

        getWindow().setAttributes(attributes);
    }

    @OnClick({R.id.dialog_product_back, R.id.dialog_product_color_red,
            R.id.dialog_product_color_green, R.id.dialog_product_size_m,
            R.id.dialog_product_size_xxl, R.id.dialog_product_size_xxxl, R.id.dialog_product_jian,
            R.id.dialog_product_jia, R.id.dialog_product_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_product_back: //结束本界面
                dismiss();

                //添加失败
                UIUtils.showFailToast();

                break;
            case R.id.dialog_product_color_red:
                mDialogProductColorRed.setSelected(true);
                mDialogProductColorGreen.setSelected(false);
                color = 1;

                break;
            case R.id.dialog_product_color_green:
                mDialogProductColorRed.setSelected(false);
                mDialogProductColorGreen.setSelected(true);
                color = 2;

                break;
            case R.id.dialog_product_size_m:
                mDialogProductSizeM.setSelected(true);
                mDialogProductSizeXxl.setSelected(false);
                mDialogProductSizeXxxl.setSelected(false);
                size = 3;

                break;
            case R.id.dialog_product_size_xxl:
                mDialogProductSizeM.setSelected(false);
                mDialogProductSizeXxl.setSelected(true);
                mDialogProductSizeXxxl.setSelected(false);
                size = 4;

                break;
            case R.id.dialog_product_size_xxxl:
                mDialogProductSizeM.setSelected(false);
                mDialogProductSizeXxl.setSelected(false);
                mDialogProductSizeXxxl.setSelected(true);
                size = 5;

                break;
            case R.id.dialog_product_jian:
                count--;
                if (count < 1) {
                    count = 1;
                }
                mDialogProductNum.setText(count + "");

                break;
            case R.id.dialog_product_jia:
                count++;
                mDialogProductNum.setText(count + "");

                break;
            case R.id.dialog_product_ok: //设置接口回调,返回数据
                //判断是否选择颜色 和尺寸

                if (color == 0) {
                    Toast.makeText(UIUtils.getContext(), "请选择颜色", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (size == 0) {
                    Toast.makeText(UIUtils.getContext(), "请选择尺码", Toast.LENGTH_SHORT).show();
                    return;
                }


                //商品id
                int id = SPUtil.getInt(UIUtils.getContext(), "pId", 1);

                //获取userId
                String[] strings = SPUtils.getString(Constants.LOGED_ACCOUNT);
                String response = strings[0];
                String userid = "-1";

                if ("login".equals(response)) {  //已登录

                    userid = strings[1];
                }

                ShopBean shopBean = new ShopBean(userid, id, count, color, size);

                //保存到全局putToShopCart中 ,添加到购物车的信息
                SPUtils.putToShopCart(UIUtils.getContext(), shopBean.user_id, shopBean.toString(), shopBean.num);


                DialogBean dialogBean = new DialogBean();
//                if (onDialogCommitClickListener != null) {

                     if (color==1){
                    dialogBean.color="红色";
                }else if(color == 2){
                    dialogBean.color="绿色";
                }
                if (size==3){
                    dialogBean.size="M";
                }else if(size==4){
                    dialogBean.size="XXL";
                }else if (size==5){
                    dialogBean.size="XXXL";
                }

                    dialogBean.count = count;
                    dialogBean.id = id;
                    dialogBean.price = mDetailProductBean.product.price;

//                    onDialogCommitClickListener.onDialogCommitClick(dialogBean);
//
//                }

                // Publisher：事件分发者，用于发出事件,通知购物车右上角更改数字
                EventBus.getDefault().post(new EventProductInfo(count, dialogBean.color,dialogBean.size,id));

                //添加成功,提示用户
                UIUtils.showSuccessToast();

                dismiss();

                break;
        }
    }

    //接口回调,还回用户选择的数据
    public interface OnDialogCommitClickListener {
        void onDialogCommitClick(DialogBean dialogBean);

    }

    private OnDialogCommitClickListener onDialogCommitClickListener;

    public void setOnDialogCommitClickListener(OnDialogCommitClickListener onDialogCommitClickListener) {
        this.onDialogCommitClickListener = onDialogCommitClickListener;
    }

    //设置方法把接受数据,设置内容
    public void setDialogData(DetailProductBean bean) {

        if (bean.product.limitPrice>=0){
            mDialogProductPrice.setText("¥" + bean.product.limitPrice);
        }else{
            mDialogProductPrice.setText("¥" + bean.product.price);
        }

        mDetailProductBean = bean;


    }
}
