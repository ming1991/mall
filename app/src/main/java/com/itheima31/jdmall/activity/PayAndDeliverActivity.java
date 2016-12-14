package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.BalanceInfoBean;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PayAndDeliverActivity extends BaseActivity implements View.OnClickListener {

    public static final String CHOOSEN_PAY_TYPE     = "pay_type";
    public static final String CHOOSEN_DELIVER_TYPE = "deliver_type";
    @InjectView(R.id.pay_on_internet)
    Button    mPayOnInternet;
    @InjectView(R.id.pay_after_receive)
    Button    mPayAfterReceive;
    @InjectView(R.id.transfer_accounts)
    Button    mTransferAccounts;
    @InjectView(R.id.act_pay_and_deliver_headback)
    ImageView mHeadback;
    @InjectView(R.id.deliver_jingdong)
    Button    mDeliverJingdong;
    @InjectView(R.id.deliver_other)
    Button    mDeliverOther;
    @InjectView(R.id.deliver_self)
    Button    mDeliverSelf;
    @InjectView(R.id.act_pay_and_deliver_confirm)
    Button    mConfirm;
    private ArrayList<Integer> mPayTypeList     = new ArrayList<>();
    private ArrayList<Integer> mDeliverTypeList = new ArrayList<>();
    private String          mPayDes;
    private int             mDeliverType;
    private BalanceInfoBean mInfoBean;

    @Override
    public void init() {
        Intent intent = getIntent();
        mPayDes = intent.getStringExtra(BalanceActivity.CUR_PAY_TYPE);
        mDeliverType = intent.getIntExtra(BalanceActivity.CUR_DELIVER_TYPE, 1);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BalanceInfoBean infoBean) {
//        LogUtils.e(infoBean.toString());
        mInfoBean = infoBean;
    }

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
        SystemClock.sleep(400);
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View onInitSuccessView() {
        View root = View.inflate(UIUtils.getContext(), R.layout.activity_pay_and_deliver, null);
        ButterKnife.inject(this, root);
        initEvent();
        initData();
        return root;
    }

    private void initEvent() {
        mHeadback.setOnClickListener(this);
        mPayOnInternet.setOnClickListener(this);
        mPayAfterReceive.setOnClickListener(this);
        mTransferAccounts.setOnClickListener(this);
        mDeliverJingdong.setOnClickListener(this);
        mDeliverOther.setOnClickListener(this);
        mDeliverSelf.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
    }

    private void initData() {
        //将选择支付和选择送货类型添加到各自集合中
        addId2List();
        //绑定视图
        bindData();
    }

    private void addId2List() {
        mPayTypeList.add(R.id.pay_on_internet);
        mPayTypeList.add(R.id.pay_after_receive);
        mPayTypeList.add(R.id.transfer_accounts);
        mDeliverTypeList.add(R.id.deliver_jingdong);
        mDeliverTypeList.add(R.id.deliver_other);
        mDeliverTypeList.add(R.id.deliver_self);
    }

    private void bindData() {
        mPayOnInternet.setText(mInfoBean.paymentList.get(0).des);
        mPayAfterReceive.setText(mInfoBean.paymentList.get(1).des);
        mTransferAccounts.setText(mInfoBean.paymentList.get(2).des);

        mDeliverJingdong.setText(mInfoBean.deliveryList.get(0).des);
        mDeliverOther.setText(mInfoBean.deliveryList.get(1).des);
        mDeliverSelf.setText(mInfoBean.deliveryList.get(2).des);

        initChoosenType();

    }

    private void initChoosenType() {
        for (int i = 0; i < mInfoBean.paymentList.size(); i++) {
            if (mInfoBean.paymentList.get(i).des.equals(mPayDes)) {
                if (i == 0) {
                    mPayOnInternet.setSelected(true);
                } else if (i == 1) {
                    mPayAfterReceive.setSelected(true);
                } else {
                    mTransferAccounts.setSelected(true);
                }
            }
        }
        for (int i = 0; i < mInfoBean.deliveryList.size(); i++) {
            if (mInfoBean.deliveryList.get(i).type == mDeliverType) {
                if (i == 0) {
                    mDeliverJingdong.setSelected(true);
                } else if (i == 1) {
                    mDeliverOther.setSelected(true);
                } else {
                    mDeliverSelf.setSelected(true);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (mPayTypeList.contains(id)) {
            mPayOnInternet.setSelected(false);
            mPayAfterReceive.setSelected(false);
            mTransferAccounts.setSelected(false);
        } else if (mDeliverTypeList.contains(id)) {
            mDeliverJingdong.setSelected(false);
            mDeliverOther.setSelected(false);
            mDeliverSelf.setSelected(false);
        }

        switch (id) {
            case R.id.act_pay_and_deliver_headback:
                notifyChoosen();
                break;
            case R.id.act_pay_and_deliver_confirm:
                notifyChoosen();
                break;
            case R.id.pay_on_internet:
                mPayOnInternet.setSelected(true);
                mPayDes = mInfoBean.paymentList.get(0).des;
                break;
            case R.id.pay_after_receive:
                mPayAfterReceive.setSelected(true);
                mPayDes = mInfoBean.paymentList.get(1).des;
                break;
            case R.id.transfer_accounts:
                mTransferAccounts.setSelected(true);
                mPayDes = mInfoBean.paymentList.get(2).des;
                break;
            case R.id.deliver_jingdong:
                mDeliverJingdong.setSelected(true);
                mDeliverType = mInfoBean.deliveryList.get(0).type;
                break;
            case R.id.deliver_other:
                mDeliverOther.setSelected(true);
                mDeliverType = mInfoBean.deliveryList.get(1).type;
                break;
            case R.id.deliver_self:
                mDeliverSelf.setSelected(true);
                mDeliverType = mInfoBean.deliveryList.get(2).type;
                break;
            default:
                break;
        }
    }

    private void notifyChoosen() {
        Intent intent = new Intent();
        intent.putExtra(CHOOSEN_PAY_TYPE, mPayDes);
        intent.putExtra(CHOOSEN_DELIVER_TYPE, mDeliverType);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
