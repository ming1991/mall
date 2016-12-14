package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.AddressBean;
import com.itheima31.jdmall.bean.AddressListBean;
import com.itheima31.jdmall.bean.BalanceInfoBean;
import com.itheima31.jdmall.bean.ShopBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.controller.BalanceProductInfoController;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.AddressProtocol;
import com.itheima31.jdmall.protocol.BalanceProtocol;
import com.itheima31.jdmall.utils.LogUtils;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {

    private static final int    REQ_PAY_DELIVER_TYPE = 100;
    private static final String TAG                  = "BalanceActivity";
    public static final  String CUR_INVOICE_TYPE     = "curInvoiceType";
    public static final  String CUR_INVOICE_TITLE    = "curInvoiceTitle";
    public static final  String CUR_INVOICE_CONTENT  = "curInvoiceContent";
    public static final  String CUR_PAY_TYPE         = "curPayType";
    public static final  String CUR_DELIVER_TYPE     = "curDeliverType";
    private static final int    REQ_INVOICE_INFO     = 200;
    private static final String BALANCE_TO_PAY       = "balance_to_pay";
    @InjectView(R.id.act_balance_headback)
    ImageView      mHeadback;
    @InjectView(R.id.act_balance_name)
    TextView       mName;
    @InjectView(R.id.act_balance_num)
    TextView       mNum;
    @InjectView(R.id.act_balance_address)
    TextView       mAddress;
    @InjectView(R.id.act_balance_address_container)
    RelativeLayout mAddressContainer;
    @InjectView(R.id.act_balance_pay)
    TextView       mPayType;
    @InjectView(R.id.act_balance_pay_container)
    RelativeLayout mPayContainer;
    @InjectView(R.id.act_balance_deliver_type)
    TextView       mDeliverType;
    @InjectView(R.id.act_balance_invoice_type)
    TextView       mBillType;
    @InjectView(R.id.act_balance_bill_container)
    RelativeLayout mBillContainer;
    @InjectView(R.id.act_balance_invoice_type)
    TextView       mInvoiceType;
    @InjectView(R.id.act_balance_invoice_content)
    TextView       mInvoiceContent;
    @InjectView(R.id.act_balance_invoice_title)
    TextView       mInvoiceTitle;
    @InjectView(R.id.act_balance_apply)
    TextView       mApply;
    @InjectView(R.id.act_balance_product_container)
    LinearLayout   mProductViewContainer;
    @InjectView(R.id.act_balance_actual_payment)
    TextView       mActualPayment;
    @InjectView(R.id.act_balance_price)
    TextView       mPrice;
    @InjectView(R.id.act_balance_freight)
    TextView       mFreight;
    @InjectView(R.id.act_balance_total_price)
    TextView       mTotalPrice;

    private BalanceInfoBean mData;
    private String          mCurPayDes;
    private int             mCurPayType;
    private int             mCurDeliverType;
    private int    mCurInvoiceType    = 1;
    private String mCurInvoiceTitle   = "";
    private int    mCurInvoiceContent = 1;
    private List<ShopBean> mProductList;
    private int    mCurAddressId   = 139;
    private String mCurPhoneNumber = "";
    private String mCurName        = "";
    private String mCurAddress     = "";
    private String          mProductParmas;
    private BalanceProtocol mProtocol;
    private float           mTotal;

    //加载视图前完成的操作,如获取activity传值
    @Override
    public void init() {
        EventBus.getDefault().register(this);

        Intent intent = getIntent();
        String action = intent.getAction();
        switch (action) {
            case Constants.SHOPCARTTOBALANCE:
                initShoppingCarParmas(intent);
                break;
            case Constants.DETAILPRODUCT2BALANCE:
                initBuyNowParmas(intent);
            default:
                break;
        }
    }

    private void initBuyNowParmas(Intent intent) {
        ShopBean buyNowBean = (ShopBean) intent.getSerializableExtra(Constants.DETAILPRODUCT2BALANCE_KEY);
        mProductParmas = buyNowBean.Product_id + ":" + buyNowBean.num + ":" + buyNowBean.color;
    }

    private void initShoppingCarParmas(Intent intent) {
        mProductList = (List<ShopBean>) intent.getSerializableExtra("checkedProduct");
        LogUtils.e(mProductList.toString());
        if (mProductList.size() == 0) {
            Toast.makeText(this, "您还没有选中商品,请重新勾选", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < mProductList.size(); i++) {
            ShopBean shopBean = mProductList.get(i);
            result.append(shopBean.Product_id).append(":").append(shopBean.num).append(":").append(shopBean.color).append("|");
        }

        mProductParmas = result.deleteCharAt(result.length() - 1).toString();
        LogUtils.e("mProductParmas=" + mProductParmas);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddressListBean addressInfoBean) {
        mCurAddressId = addressInfoBean.id;
        mCurAddress = "收货地址:" + addressInfoBean.province + "省" + addressInfoBean.city +
                addressInfoBean.addressArea + addressInfoBean.addressDetail;
        mName.setText("收货人:" + addressInfoBean.name);
        mNum.setText("电话:" + addressInfoBean.phoneNumber);
        mAddress.setText(mCurAddress);
//        LogUtils.e(addressInfoBean.toString());
    }

    //子线程中初始化数据,根据返回值LoadingPager返回视图
    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
        if (isInitAddressFail()) {
            return LoadingPager.LoadedResultEnum.ERROR;
        }
        HashMap<String, String> bodyMap = new HashMap<>();
        bodyMap.put("sku", mProductParmas);
        mProtocol = new BalanceProtocol("checkout", bodyMap);
        try {
            mData = mProtocol.loadData(0);
//            if (mData != null) {
//                Log.d(TAG, mData.toString());
//            }
            return ResultUtils.checkBalanceData(mData);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }
    }

    private boolean isInitAddressFail() {
        AddressProtocol addressProtocol = new AddressProtocol();
        try {
            AddressBean addressBean = addressProtocol.loadData(0);
            LoadingPager.LoadedResultEnum resultEnum = ResultUtils.checkBalanceData(addressBean);
            if (resultEnum != LoadingPager.LoadedResultEnum.SUCCESS) {
                return true;
            }
            for (AddressListBean addresslistBean : addressBean.addressList) {
                if (addresslistBean.isDefault == 1) {
                    mCurAddressId = addresslistBean.id;
                    mCurPhoneNumber = addresslistBean.phoneNumber;
                    mCurName = addresslistBean.name;
                    mCurAddress = addresslistBean.province + "省" + addresslistBean.city +
                            addresslistBean.addressArea + addresslistBean.addressDetail;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    //返回成功视图
    @Override
    public View onInitSuccessView() {
        View root = View.inflate(UIUtils.getContext(), R.layout.activity_balance, null);
        ButterKnife.inject(this, root);
        initEvent();
        initProductView();
        initPayInfo();
        initAddressView();
        return root;
    }


    private void initProductView() {
        float freight = mData.checkoutAddup.freight;
        float productPrice = 0;
        for (int i = 0; i < mData.productList.size(); i++) {
            BalanceProductInfoController productViewHolder = new BalanceProductInfoController();
            BalanceInfoBean.ProductListBean productListBean = mData.productList.get(i);
            productPrice += productListBean.prodNum * productListBean.product.price;
            productViewHolder.setDataAndRefreshView(productListBean);
            //添加商品详情视图
            mProductViewContainer.addView(productViewHolder.mBaseView);
        }

        String freightString = UIUtils.getString(R.string.addPrice);
        freightString = String.format(freightString, freight + "");
        mFreight.setText(freightString);

        String price = UIUtils.getString(R.string.price);
        price = String.format(price, productPrice + "");
        mPrice.setText(price);

        mTotal = productPrice + freight;
        mTotalPrice.setText("¥" + mTotal);

        String actualPayment = UIUtils.getString(R.string.actualPayment);
        actualPayment = String.format(actualPayment, mTotal + "");
        mActualPayment.setText(actualPayment);
    }

    private void initPayInfo() {
        mPayType.setText(mData.paymentList.get(0).des);
        mCurPayDes = mData.paymentList.get(0).des;
        mCurPayType = mData.paymentList.get(0).type;
        mDeliverType.setText(mData.deliveryList.get(0).des);
        mCurDeliverType = mData.deliveryList.get(0).type;
    }

    private void initAddressView() {
        mName.setText("收货人:" + mCurName);
        mNum.setText("电话:" + mCurPhoneNumber);
        mAddress.setText("收货地址:" + mCurAddress);
    }

    //加载视图后初始化事件
    public void initEvent() {
        mHeadback.setOnClickListener(this);
        mAddressContainer.setOnClickListener(this);
        mPayContainer.setOnClickListener(this);
        mBillContainer.setOnClickListener(this);
        mApply.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.act_balance_headback:
                finish();
                break;
            case R.id.act_balance_address_container:
                startActivity(new Intent(UIUtils.getContext(), AddressActivity.class));
                break;
            case R.id.act_balance_pay_container:
                intent = new Intent(UIUtils.getContext(), PayAndDeliverActivity.class);
                intent.putExtra(CUR_PAY_TYPE, mCurPayDes);
                intent.putExtra(CUR_DELIVER_TYPE, mCurDeliverType);
                startActivityForResult(intent, REQ_PAY_DELIVER_TYPE);

                ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(310);
                        EventBus.getDefault().post(mData);
                    }
                });
                break;
            case R.id.act_balance_bill_container:
                intent = new Intent(this, InvoiceActivity.class);
                intent.putExtra(CUR_INVOICE_TYPE, mCurInvoiceType);
                intent.putExtra(CUR_INVOICE_TITLE, mCurInvoiceTitle);
                intent.putExtra(CUR_INVOICE_CONTENT, mCurInvoiceContent);
                startActivityForResult(intent, REQ_INVOICE_INFO);
                break;
            case R.id.act_balance_apply:
                applyOrder();
                break;
            default:
                break;
        }
    }

    private void applyOrder() {
        HashMap<String, String> applyBodyMap = new HashMap<>();
        applyBodyMap.put("sku", mProductParmas);
        applyBodyMap.put("addressId", mCurAddressId + "");
        applyBodyMap.put("paymentType", mCurPayType + "");
        applyBodyMap.put("deliveryType", mCurDeliverType + "");
        applyBodyMap.put("invoiceType", mCurInvoiceType + "");
        applyBodyMap.put("invoiceTitle", mCurInvoiceTitle);
        applyBodyMap.put("invoiceContent", mCurInvoiceContent + "");

        final BalanceProtocol applyProtocol = new BalanceProtocol("ordersumbit", applyBodyMap);

        ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
            @Override
            public void run() {
                try {
                    BalanceInfoBean orderInfo = applyProtocol.loadData(0);
                    LogUtils.e(orderInfo.toString() + "提交结果");
                    MyApplication.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UIUtils.getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UIUtils.getContext(), PayActivity.class);
                            intent.putExtra(BALANCE_TO_PAY, mTotal);
                            startActivity(intent);
                            finish();
                        }
                    });
                } catch (IOException e) {
                    LogUtils.e("提交异常" + e.toString());
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_PAY_DELIVER_TYPE && resultCode == RESULT_OK) {
            updatePayAndDeliverInfo(data);
        } else if (requestCode == REQ_INVOICE_INFO && resultCode == RESULT_OK) {
            updateInvoiceInfo(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updatePayAndDeliverInfo(Intent data) {
        mCurPayDes = data.getStringExtra(PayAndDeliverActivity.CHOOSEN_PAY_TYPE);
        mCurDeliverType = data.getIntExtra(PayAndDeliverActivity.CHOOSEN_DELIVER_TYPE, mData.deliveryList.get(0).type);
        for (BalanceInfoBean.PaymentListBean paymentListBean : mData.paymentList) {
            if (paymentListBean.des.equals(mCurPayDes)) {
                mCurPayType = paymentListBean.type;
                mPayType.setText(paymentListBean.des);
            }
        }

        for (BalanceInfoBean.DeliveryListBean deliveryListBean : mData.deliveryList) {
            if (deliveryListBean.type == mCurDeliverType) {
                mDeliverType.setText(deliveryListBean.des);
            }
        }
    }

    private void updateInvoiceInfo(Intent data) {
        mCurInvoiceType = data.getIntExtra(InvoiceActivity.CHOOSEN_INVOICE_TYPE, 1);
        mCurInvoiceTitle = data.getStringExtra(InvoiceActivity.CHOOSEN_INVOICE_TITLE);
        mCurInvoiceContent = data.getIntExtra(InvoiceActivity.CHOOSEN_INVOICE_CONTENT, 1);
        setInvoiceTypeValue();
        setInvoiceTitleValue();
        setInvoiceContentValue();
    }

    private void setInvoiceTypeValue() {
        String invoiceType = UIUtils.getString(R.string.invoiceType);
        if (mCurInvoiceType == 1) {
            invoiceType = String.format(invoiceType, "个人");
        } else {
            invoiceType = String.format(invoiceType, "单位");
        }
        mInvoiceType.setText(invoiceType);

    }

    private void setInvoiceTitleValue() {
        String invoiceTitle = UIUtils.getString(R.string.invoiceTitle);
        invoiceTitle = String.format(invoiceTitle, mCurInvoiceTitle);
        mInvoiceTitle.setText(invoiceTitle);
    }

    private void setInvoiceContentValue() {
        String invoiceContent = UIUtils.getString(R.string.invoiceContent);
        if (mCurInvoiceContent == 1) {
            invoiceContent = String.format(invoiceContent, "图书");
        } else if (mCurInvoiceContent == 2) {
            invoiceContent = String.format(invoiceContent, "服装");
        } else if (mCurInvoiceContent == 3) {
            invoiceContent = String.format(invoiceContent, "耗材");
        } else if (mCurInvoiceContent == 4) {
            invoiceContent = String.format(invoiceContent, "软件");
        } else if (mCurInvoiceContent == 5) {
            invoiceContent = String.format(invoiceContent, "资料");
        }
        mInvoiceContent.setText(invoiceContent);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
