package com.itheima31.jdmall.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.OrderDetailBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.protocol.OrderDetailProtocol;
import com.itheima31.jdmall.utils.CheckResData;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity {

    public static String orderId;
    @InjectView(R.id.order_detail_return)
    ImageView    mOrderDetailReturn;
    @InjectView(R.id.order_id)
    TextView     mOrderId;
    @InjectView(R.id.order_name)
    TextView     mOrderName;
    @InjectView(R.id.order_phone)
    TextView     mOrderPhone;
    @InjectView(R.id.order_adress)
    TextView     mOrderAdress;
    @InjectView(R.id.order_state)
    TextView     mOrderState;
    @InjectView(R.id.order_deliveryType)
    TextView     mOrderDeliveryType;
    @InjectView(R.id.order_paymentInfo)
    TextView     mOrderPaymentInfo;
    @InjectView(R.id.order_generateTime)
    TextView     mOrderGenerateTime;
    @InjectView(R.id.order_invoiceInfo)
    TextView     mOrderInvoiceInfo;
    @InjectView(R.id.order_deliver)
    TextView     mOrderDeliver;
    @InjectView(R.id.order_deliveryHead)
    TextView     mOrderDeliveryHead;
    @InjectView(R.id.order_deliveryContent)
    TextView     mOrderDeliveryContent;
    @InjectView(R.id.order_deliveryRequest)
    TextView     mOrderDeliveryRequest;
    @InjectView(R.id.order_product_color)
    TextView     mOrderProductColor;
    @InjectView(R.id.order_product_size)
    TextView     mOrderProductSize;
    @InjectView(R.id.order_product_num)
    TextView     mOrderProductNum;
    @InjectView(R.id.order_product_price)
    TextView     mOrderProductPrice;
    @InjectView(R.id.order_product_total_num)
    TextView     mOrderProductTotalNum;
    @InjectView(R.id.order_product_total_price)
    TextView     mOrderProductTotalPrice;
    @InjectView(R.id.order_product_feight)
    TextView     mOrderProductFeight;
    @InjectView(R.id.order_product_privilege)
    TextView     mOrderProductPrivilege;
    @InjectView(R.id.order_product_meet)
    TextView     mOrderProductMeet;
    @InjectView(R.id.order_detail_cancel)
    TextView     mOrderDetailCancel;
    @InjectView(R.id.order_deliver_inquire)
    TextView     mOrderDeliverInquire;
    @InjectView(R.id.order_detail_iv)
    ImageView    mOrderDetailIv;
    @InjectView(R.id.order_deliver_llt)
    LinearLayout mOrderDeliverLlt;

    private OrderDetailBean mOrderDetailBean;
    private int             mPosition;
    private boolean         mIsGone;
    private PopupWindow     mPopupWindow;

    @Override
    public void init() {
        orderId = getIntent().getStringExtra("orderId");
        mPosition = getIntent().getIntExtra("position", 0);
        mIsGone = getIntent().getBooleanExtra("isGone", true);
    }

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
        try {
            OrderDetailProtocol orderDetailProtocol = new OrderDetailProtocol();
            mOrderDetailBean = orderDetailProtocol.loadData(0);
            return CheckResData.checkResData(mOrderDetailBean);
        } catch (Exception e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    @Override
    public View onInitSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.order_detail, null);
        ButterKnife.inject(this, view);
        if (mIsGone) {
            mOrderDetailCancel.setVisibility(View.VISIBLE);
        } else {
            mOrderDetailCancel.setVisibility(View.GONE);
        }
        refreshUIByState();
        return view;
    }

    public void refreshUIByState() {
        //地址信息 addressInfo
        OrderDetailBean.AddressInfoBean addressInfo = mOrderDetailBean.addressInfo;
        //运输
        OrderDetailBean.CheckoutAddupBean checkoutAddup = mOrderDetailBean.checkoutAddup;
        //快递
        OrderDetailBean.DeliveryInfoBean deliveryInfo = mOrderDetailBean.deliveryInfo;
        //发票
        OrderDetailBean.InvoiceInfoBean invoiceInfo = mOrderDetailBean.invoiceInfo;
        //订单信息
        OrderDetailBean.OrderInfoBean orderInfo = mOrderDetailBean.orderInfo;
        //付款
        OrderDetailBean.PaymentInfoBean paymentInfo = mOrderDetailBean.paymentInfo;
        //商品详情
        List<OrderDetailBean.ProductListBean> productList = mOrderDetailBean.productList;
        try {
            mOrderId.setText(orderInfo.orderId + "");//订单号
            mOrderName.setText(addressInfo.name);//收货人
            mOrderPhone.setText("12345");//电话
            mOrderAdress.setText(addressInfo.addressArea+addressInfo.addressDetail);//收货地址
            mOrderState.setText("送达状态");//订单状态
            mOrderDeliveryType.setText("快递");//送货状态
            switch (paymentInfo.type) {//支付方式
                case 1:
                    mOrderPaymentInfo.setText("货到付款");
                    break;
                case 2:
                    mOrderPaymentInfo.setText("货到POS机");
                    break;
                case 3:
                    mOrderPaymentInfo.setText("支付宝");
                    break;
            }
            //订单生成时间
            mOrderGenerateTime.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(new BigInteger(orderInfo.time).longValue())));
            mOrderInvoiceInfo.setText("是");//是否开发票
            //发货时间
            mOrderDeliver.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(new BigInteger(orderInfo.time).longValue())));
            mOrderDeliveryHead.setText(invoiceInfo.invoiceTitle);//发票抬头
            mOrderDeliveryContent.setText(invoiceInfo.invoiceContent);//发票内容
            switch (Integer.parseInt(deliveryInfo.type)) {//送货要求
                case 1:
                    mOrderDeliveryRequest.setText("周一至周五送货");
                    break;
                case 2:
                    mOrderDeliveryRequest.setText("双休日及公众假期送货");
                    break;
                case 3:
                    mOrderDeliveryRequest.setText("时间不限，工作日双休日及公众假期均可送货");
                    break;
            }
            //商品颜色
            //mOrderProductColor.setText("颜色:"+productList[new Random().nextInt(2)].product.productProperty[0].v);
            mOrderProductColor.setText("颜色:" + productList.get(0).product.productProperty.get(0).v);
            //商品尺寸?????????????
            mOrderProductSize.setText("尺寸:M");
            //数量
            int prodNum = productList.get(0).prodNum;
            mOrderProductNum.setText("数量:" + prodNum);
            //价格
            int price = productList.get(0).product.price;
            mOrderProductPrice.setText("价格:" + price);
            //总数
            mOrderProductTotalNum.setText(productList.get(0).prodNum + "件");
            //总金额
            int total = prodNum * price;
            mOrderProductTotalPrice.setText(total + "元");
            //运费
            int freight = checkoutAddup.freight;
            mOrderProductFeight.setText(freight + "元");
            //优惠金额
            int totalPoint = checkoutAddup.totalPoint;
            mOrderProductPrivilege.setText(totalPoint + "元");
            //应付
            mOrderProductMeet.setText(total + freight + totalPoint + "元");
            //商品图片
            Log.d("lin","asdasdasd"+ Constants.URLS.BASEURL+productList.get(0).product.pic);
           Picasso.with(UIUtils.getContext()).load(Constants.URLS.BASEURL+productList.get(0).product.pic).into(mOrderDetailIv);
        } catch (Exception e) {
            finish();
            Toast.makeText(this, "没有该商品详情", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.order_detail_return, R.id.order_detail_cancel, R.id.order_deliver_inquire})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order_detail_return:
                finish();
                break;
            case R.id.order_detail_cancel:
                dialog();
                break;
            case R.id.order_deliver_inquire:
                showPopupWindow();
                break;
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认取消订单吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                notifyObservers(mPosition);
                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*=============== 自己实现观察者设计模式,通知最新的下载进度给外界 ===============*/
    //1.定义接口以及接口方法
    public interface OrderObserver {
        void onOederInfoChanged(int order);
    }

    //2.定义集合保存接口对象
    public static List<OrderObserver> observers = new ArrayList<>();

    //3. 常见方法-->添加观察者到观察者集合中
    public static synchronized void addObserver(OrderObserver o) {
        if (o == null)
            throw new NullPointerException();
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    //3. 常见方法-->从观察者集合中移除观察者
    public static synchronized void deleteObserver(OrderObserver o) {
        observers.remove(o);
    }

    //3. 常见方法-->通知所有的观察者消息已经发生改变
    public static void notifyObservers(int order) {
        for (OrderObserver o : observers) {
            o.onOederInfoChanged(order);
        }
    }


    protected void showPopupWindow() {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;

        //popupwindow就创建一次
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(width, height);
            mPopupWindow.setContentView(View.inflate(UIUtils.getContext(), R.layout.order_delivery, null));

            mPopupWindow.setOutsideTouchable(true);
            //设置背景之后，Popupwindow内部才会去处理touch事件
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            //让popupwindow可获取焦点
            mPopupWindow.setFocusable(true);

        }
        //弹出popupwindow
        mPopupWindow.showAsDropDown(mOrderDeliverInquire);
    }

}
