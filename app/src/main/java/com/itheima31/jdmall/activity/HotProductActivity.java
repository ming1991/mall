package com.itheima31.jdmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima31.jdmall.R;

public class HotProductActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private ImageView mHb1;
    private ImageView mHb2;
    private ImageView mHb3;
    private ImageView mHb4;
    private ImageView mHb5;
    private ImageView mHb6;
    private WebView mWeb;
    private LinearLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_product);
        back = (ImageView) findViewById(R.id.actionbar_home_layout_scan);
        title = (LinearLayout) findViewById(R.id.titles);


        back.setOnClickListener(this) ;
        mHb1 = (ImageView) findViewById(R.id.haibao1);
        mHb2 = (ImageView) findViewById(R.id.haibao2);
        mHb3 = (ImageView) findViewById(R.id.haibao3);
        mHb4 = (ImageView) findViewById(R.id.haibao4);
        mHb5 = (ImageView) findViewById(R.id.haibao5);
        mHb6 = (ImageView) findViewById(R.id.haibao6);
        mWeb = (WebView) findViewById(R.id.web);
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setWebViewClient(new WebViewClient());
        mHb1.setOnClickListener(this);
        mHb2.setOnClickListener(this);
        mHb3.setOnClickListener(this);
        mHb4.setOnClickListener(this);
        mHb5.setOnClickListener(this);
        mHb6.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.actionbar_home_layout_scan:
               finish();
                break;
            case R.id.haibao1:
                back.setVisibility(View.GONE);
                mHb1.setVisibility(View.GONE);
                mHb2.setVisibility(View.GONE);
                mHb3.setVisibility(View.GONE);
                mHb4.setVisibility(View.GONE);
                mHb5.setVisibility(View.GONE);
                mHb6.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.getSettings().setJavaScriptEnabled(true);
                mWeb.loadUrl("http://sale.jd.com/act/GTugDfc2msaVIQ.html");
                title.setVisibility(View.GONE);
                break;
            case R.id.haibao2:
                back.setVisibility(View.GONE);
                mHb1.setVisibility(View.GONE);
                mHb2.setVisibility(View.GONE);
                mHb3.setVisibility(View.GONE);
                mHb4.setVisibility(View.GONE);
                mHb5.setVisibility(View.GONE);
                mHb6.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.getSettings().setJavaScriptEnabled(true);
                mWeb.loadUrl("http://sale.jd.com/act/ohXn4qQN3pBIAa.html");
                title.setVisibility(View.GONE);
                break;
            case R.id.haibao3:
                back.setVisibility(View.GONE);
                mHb1.setVisibility(View.GONE);
                mHb2.setVisibility(View.GONE);
                mHb3.setVisibility(View.GONE);
                mHb4.setVisibility(View.GONE);
                mHb5.setVisibility(View.GONE);
                mHb6.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.getSettings().setJavaScriptEnabled(true);
                mWeb.loadUrl("http://sale.jd.com/act/sGL3mYaHypNVlb.html");
                title.setVisibility(View.GONE);
                break;
            case R.id.haibao4:
                back.setVisibility(View.GONE);
                mHb1.setVisibility(View.GONE);
                mHb2.setVisibility(View.GONE);
                mHb3.setVisibility(View.GONE);
                mHb4.setVisibility(View.GONE);
                mHb5.setVisibility(View.GONE);
                mHb6.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.getSettings().setJavaScriptEnabled(true);
                mWeb.loadUrl("http://sale.jd.com/act/7dAlFsTIVYJ.html");
                title.setVisibility(View.GONE);
                break;
            case R.id.haibao5:
                back.setVisibility(View.GONE);
                mHb1.setVisibility(View.GONE);
                mHb2.setVisibility(View.GONE);
                mHb3.setVisibility(View.GONE);
                mHb4.setVisibility(View.GONE);
                mHb5.setVisibility(View.GONE);
                mHb6.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.getSettings().setJavaScriptEnabled(true);
                mWeb.loadUrl("http://sale.jd.com/act/VlxoKkSXdqjLaJRi.html");
                title.setVisibility(View.GONE);
                break;
            case R.id.haibao6:
                back.setVisibility(View.GONE);
                mHb1.setVisibility(View.GONE);
                mHb2.setVisibility(View.GONE);
                mHb3.setVisibility(View.GONE);
                mHb4.setVisibility(View.GONE);
                mHb5.setVisibility(View.GONE);
                mHb6.setVisibility(View.GONE);
                mWeb.setVisibility(View.VISIBLE);
                mWeb.getSettings().setJavaScriptEnabled(true);
                mWeb.loadUrl("http://sale.jd.com/act/nQAd3alKxi4kF.html");
                title.setVisibility(View.GONE);
                break;

        }
    }

}
