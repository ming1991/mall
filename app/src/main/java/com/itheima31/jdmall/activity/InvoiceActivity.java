package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.InvoiceInfoBean;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.InvoiceProtocol;
import com.itheima31.jdmall.utils.LogUtils;
import com.itheima31.jdmall.utils.ResultUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.james.biuedittext.BiuEditText;

public class InvoiceActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String CHOOSEN_INVOICE_TYPE    = "choosen_invoice_type";
    public static final String CHOOSEN_INVOICE_TITLE   = "choosen_invoice_title";
    public static final String CHOOSEN_INVOICE_CONTENT = "choosen_invoice_content";
    @InjectView(R.id.act_invoice_headback)
    ImageView   mHeadback;
    @InjectView(R.id.invoice_personal)
    Button      mInvoicePersonal;
    @InjectView(R.id.invoice_company)
    Button      mInvoiceCompany;
    @InjectView(R.id.invoice_radio_btn0)
    RadioButton mRadioBtn0;
    @InjectView(R.id.invoice_radio_btn1)
    RadioButton mRadioBtn1;
    @InjectView(R.id.invoice_radio_btn2)
    RadioButton mRadioBtn2;
    @InjectView(R.id.invoice_radio_btn3)
    RadioButton mRadioBtn3;
    @InjectView(R.id.invoice_radio_btn4)
    RadioButton mRadioBtn4;
    @InjectView(R.id.act_invoice_confirm)
    Button      mConfirm;
    @InjectView(R.id.act_invoice_radio_group)
    RadioGroup  mRadioGroup;
    @InjectView(R.id.invoice_title)
    BiuEditText mInvoiceTitle;
    private InvoiceInfoBean mData;
    private List<Integer> mIdArray = new ArrayList<>();

    //记录选中的发票信息
    private int    mChoosenInvoiceType;
    private String mChoosenInvoiceTitle;
    private int    mChoosenInvoiceContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        ButterKnife.inject(this);
        initExtra();
        initData();
        initIdArray();

    }

    //activity获取传递过来的数据
    public void initExtra() {
        Intent intent = getIntent();
        mChoosenInvoiceType = intent.getIntExtra(BalanceActivity.CUR_INVOICE_TYPE, 1);
        mChoosenInvoiceTitle = intent.getStringExtra(BalanceActivity.CUR_INVOICE_TITLE);
        mChoosenInvoiceContent = intent.getIntExtra(BalanceActivity.CUR_INVOICE_CONTENT, 1);
    }

    private void initData() {
        ThreadPoolProxyFactory.createNormalPoolProxy().submit(new Runnable() {
            @Override
            public void run() {
                InvoiceProtocol protocol = new InvoiceProtocol();
                try {
                    mData = protocol.loadData(0);
                    LogUtils.e(mData.toString());
                    MyApplication.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            initEvent();
                            initInvoiceType();
                            initInvoiceTitle();
                            initInvoiceContent();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initIdArray() {
        mIdArray.add(R.id.invoice_personal);
        mIdArray.add(R.id.invoice_company);
    }

    private void initEvent() {
        mHeadback.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
        mInvoicePersonal.setOnClickListener(this);
        mInvoiceCompany.setOnClickListener(this);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                LoadingPager.LoadedResultEnum result = ResultUtils.checkBalanceData(mData);
                if (result == LoadingPager.LoadedResultEnum.SUCCESS) {
                    if (checkedId == mRadioBtn0.getId()) {
                        mChoosenInvoiceContent = mData.invoice.get(0).id;
                    } else if (checkedId == mRadioBtn1.getId()) {
                        mChoosenInvoiceContent = mData.invoice.get(1).id;
                    } else if (checkedId == mRadioBtn2.getId()) {
                        mChoosenInvoiceContent = mData.invoice.get(2).id;
                    } else if (checkedId == mRadioBtn3.getId()) {
                        mChoosenInvoiceContent = mData.invoice.get(3).id;
                    } else {
                        mChoosenInvoiceContent = mData.invoice.get(4).id;
                    }
                } else {
                    Toast.makeText(InvoiceActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initInvoiceType() {
        if (mChoosenInvoiceType == 1) {
            mInvoicePersonal.setSelected(true);
            mInvoiceCompany.setSelected(false);
        } else {
            mInvoicePersonal.setSelected(false);
            mInvoiceCompany.setSelected(true);
        }
    }

    private void initInvoiceTitle() {
        mInvoiceTitle.setText(mChoosenInvoiceTitle);
    }

    private void initInvoiceContent() {
        mRadioBtn0.setChecked(false);
        mRadioBtn1.setChecked(false);
        mRadioBtn2.setChecked(false);
        mRadioBtn3.setChecked(false);
        mRadioBtn4.setChecked(false);
        switch (mChoosenInvoiceContent) {
            case 1:
                mRadioBtn0.setChecked(true);
                break;
            case 2:
                mRadioBtn1.setChecked(true);
                break;
            case 3:
                mRadioBtn2.setChecked(true);
                break;
            case 4:
                mRadioBtn3.setChecked(true);
                break;
            case 5:
                mRadioBtn4.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (mIdArray.contains(v.getId())) {
            mInvoicePersonal.setSelected(false);
            mInvoiceCompany.setSelected(false);
        }

        switch (v.getId()) {
            case R.id.act_invoice_headback:
                notifyChoosen();
                break;
            case R.id.act_invoice_confirm:
                notifyChoosen();
                break;
            case R.id.invoice_personal:
                mChoosenInvoiceType = 1;
                mInvoicePersonal.setSelected(true);
                break;
            case R.id.invoice_company:
                mChoosenInvoiceType = 2;
                mInvoiceCompany.setSelected(true);
                break;
            default:
                break;
        }
    }

    private void notifyChoosen() {
        mChoosenInvoiceTitle = mInvoiceTitle.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra(CHOOSEN_INVOICE_TYPE, mChoosenInvoiceType);
        intent.putExtra(CHOOSEN_INVOICE_TITLE, mChoosenInvoiceTitle);
        intent.putExtra(CHOOSEN_INVOICE_CONTENT, mChoosenInvoiceContent);
        setResult(RESULT_OK, intent);
        finish();
    }
}
