package com.itheima31.jdmall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.itheima31.jdmall.R;

/**
 * Created by wu on 2016/10/30.
 */

public class DcvliveActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url=getIntent().getStringExtra("URL");
        WebView web = (WebView) findViewById(R.id.webview);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);
    }
}
