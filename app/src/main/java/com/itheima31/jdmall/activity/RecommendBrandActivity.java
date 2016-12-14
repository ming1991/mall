package com.itheima31.jdmall.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import com.itheima31.jdmall.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RecommendBrandActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean issel = true;
    private boolean issel2 = true;
    private boolean issel3 = true;
    private boolean issel4 = true;
    private boolean issel5 = true;
    private boolean issel6 = true;
    private boolean issel7 = true;
    private boolean issel8 = true;
    private boolean issel9 = true;
    private boolean issel10 = true;
    private boolean issel11= true;
    private boolean issel12 = true;
    private boolean issel13 = true;
    private boolean issel14 = true;
    private boolean issel15 = true;
    private boolean issel16 = true;
    private boolean issel17 = true;
    @InjectView(R.id.dlg1)TextView mDg1;
    @InjectView(R.id.dlg2)TextView mDg2;
    @InjectView(R.id.dlg3)TextView mDg3;
    @InjectView(R.id.dlg4)TextView mDg4;
    @InjectView(R.id.dlg5)TextView mDg5;
    @InjectView(R.id.dlg6)TextView mDg6;
    @InjectView(R.id.dlg7)TextView mDg7;
    @InjectView(R.id.dlg8)TextView mDg8;
    @InjectView(R.id.dlg9)TextView mDg9;
    @InjectView(R.id.dlg10)TextView mDg10;
    @InjectView(R.id.dlg11)TextView mDg11;
    @InjectView(R.id.dlg12)TextView mDg12;
    @InjectView(R.id.dlg13)TextView mDg13;
    @InjectView(R.id.dlg14)TextView mDg14;
    @InjectView(R.id.dlg16)TextView mDg15;
    @InjectView(R.id.dlg17)TextView mDg16;
    @InjectView(R.id.dlg18)TextView mDg17;
    private AlertDialog mDialog;
    private ScrollView mScll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_brand);
        mScll = (ScrollView) findViewById(R.id.Brand_scll);
        findViewById(R.id.tt_text1).setOnClickListener(this);
        findViewById(R.id.actionbar_home_layout_back1).setOnClickListener(this);
        findViewById(R.id.tt_text2).setOnClickListener(this);
        findViewById(R.id.tt_text3).setOnClickListener(this);
        findViewById(R.id.tt_text4).setOnClickListener(this);

        init();
    }

    private void initData() {
        mDg1.setOnClickListener(this);
        mDg2.setOnClickListener(this);
        mDg3.setOnClickListener(this);
        mDg4.setOnClickListener(this);
        mDg5.setOnClickListener(this);
        mDg6.setOnClickListener(this);
        mDg7.setOnClickListener(this);
        mDg8.setOnClickListener(this);
        mDg9.setOnClickListener(this);
        mDg10.setOnClickListener(this);
        mDg11.setOnClickListener(this);
        mDg12.setOnClickListener(this);
        mDg13.setOnClickListener(this);
        mDg14.setOnClickListener(this);
        mDg15.setOnClickListener(this);
        mDg16.setOnClickListener(this);
        mDg17.setOnClickListener(this);
    }

    public void init() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog,
                (ViewGroup) findViewById(R.id.dialog));
        ButterKnife.inject(this,layout);
        initData();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(layout);
        mDialog = builder.create();
        mDialog.show();

        Window window = mDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1.0f;
        window.setAttributes(lp);
        mDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dlg1:mDg1.setSelected(issel);issel=!issel;break;
            case R.id.dlg2:mDg2.setSelected(issel2);issel2=!issel2;break;
            case R.id.dlg3:mDg3.setSelected(issel3);issel3=!issel3;break;
            case R.id.dlg4:mDg4.setSelected(issel4);issel4=!issel4;break;
            case R.id.dlg5:mDg5.setSelected(issel5);issel5=!issel5;break;
            case R.id.dlg6:mDg6.setSelected(issel6);issel6=!issel6;break;
            case R.id.dlg7:mDg7.setSelected(issel7);issel7=!issel7;break;
            case R.id.dlg8:mDg8.setSelected(issel8);issel8=!issel8;break;
            case R.id.dlg9:mDg9.setSelected(issel9);issel9=!issel9;break;
            case R.id.dlg10:mDg10.setSelected(issel10);issel10=!issel10;break;
            case R.id.dlg11:mDg11.setSelected(issel11);issel11=!issel11;break;
            case R.id.dlg12:mDg12.setSelected(issel12);issel12=!issel12;break;
            case R.id.dlg13:mDg13.setSelected(issel13);issel13=!issel13;break;
            case R.id.dlg14:mDg14.setSelected(issel14);issel14=!issel14;break;
            case R.id.dlg16:mDg15.setSelected(issel15);issel15=!issel15;break;
            case R.id.dlg17:
                mDialog.dismiss();
                break;
            case R.id.dlg18:
                this.finish();
                break;
            case R.id.tt_text1:
                mScll.smoothScrollTo(0,400);
                break;
            case R.id.tt_text2:
                mScll.smoothScrollTo(0,1750);
                break;
            case R.id.tt_text3:
                mScll.smoothScrollTo(0,3650);
                break;
            case R.id.tt_text4:
                mScll.smoothScrollTo(0,5530);
                break;
            case R.id.actionbar_home_layout_back1:
                finish();
                break;
        }
    }
}
