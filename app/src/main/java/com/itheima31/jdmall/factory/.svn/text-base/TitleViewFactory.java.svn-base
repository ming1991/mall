package com.itheima31.jdmall.factory;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.LogOutActivity;
import com.itheima31.jdmall.activity.MessageCenterActivity;
import com.itheima31.jdmall.activity.RecognitionActivity;
import com.itheima31.jdmall.activity.SearchActivity;
import com.itheima31.jdmall.net.api.UrlConstants;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.HashMap;

/**
 * Created by Tony on 2016/10/23.
 */

public class TitleViewFactory {

    private static HashMap<Integer, View> mhm = new HashMap<>();
    private static TiltleViewOnclickListener mTitleClickListener = new TiltleViewOnclickListener();

    public static View getTitleView(final Context context, int checkedId) {

        View titileView = mhm.get(checkedId);

        if (titileView == null) {
            switch (checkedId) {
                case R.id.act_mian_rb_home:
                    titileView = LayoutInflater.from(context).inflate(R.layout.actionbar_home_layout, null);
                    break;
                case R.id.act_mian_rb_classify:
                    titileView = LayoutInflater.from(context).inflate(R.layout.actionbar_find_layout, null);
                    break;
                case R.id.act_mian_rb_discover:
                    titileView = LayoutInflater.from(context).inflate(R.layout.actionbar_discover_layout, null);
                    break;
                case R.id.act_mian_rb_shopping_cart:
                    titileView = LayoutInflater.from(context).inflate(R.layout.actionbar_shopping_cart, null);
                    break;
                case R.id.act_mian_rb_mine:
                    titileView = LayoutInflater.from(context).inflate(R.layout.actionbar_mine, null);
                    View setting = titileView.findViewById(R.id.actionbar_discover_layout_attention);
                    setting.setOnClickListener(mTitleClickListener);
                    break;
            }
        }

        final EditText etText = (EditText) titileView.findViewById(R.id.actionbar_home_layout_edit_text);
        if (etText != null){
           etText.setOnTouchListener(new View.OnTouchListener() {
               int i = 0;
               @Override
               public boolean onTouch(View v, MotionEvent event) {

                   i++;
                   if (i == 2 ){

                       Drawable drawable = etText.getCompoundDrawables()[2];
                       //如果不是按下事件，不再处理
                       if (event.getX() > etText.getWidth()
                               - etText.getPaddingRight()
                               - drawable.getIntrinsicWidth()){

                           Intent intentLimint = new Intent(v.getContext(), RecognitionActivity.class);
                           v.getContext().startActivity(intentLimint);
//                           Toast.makeText(UIUtils.getContext(), "水豆腐", Toast.LENGTH_SHORT).show();
                           return true;
                       }


                       Intent intent = new Intent(v.getContext(), SearchActivity.class);
                       v.getContext().startActivity(intent);
                       i = 0;
                   }
                   return true;
               }
           });

        }
        

        View ivMessage = titileView.findViewById(R.id.actionbar_message_iv);
        ivMessage.setOnClickListener(mTitleClickListener);

        View ivScan = titileView.findViewById(R.id.actionbar_home_layout_scan);
        if (ivScan != null) {
            ivScan.setOnClickListener(mTitleClickListener);
        }


        mhm.put(checkedId, titileView);
        return titileView;
    }

    static int i = 1;

    static class TiltleViewOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.actionbar_message_iv:
                    Intent intent = new Intent(v.getContext(), MessageCenterActivity.class);
                    v.getContext().startActivity(intent);
                    break;
                case R.id.actionbar_home_layout_scan:
//                    Intent intentLimint = new Intent(v.getContext(), RecognitionActivity.class);
//                    v.getContext().startActivity(intentLimint);
                    Toast.makeText(UIUtils.getContext(), "当前Baseurl:" + UrlConstants.curBaseUrl, Toast.LENGTH_SHORT).show();
                    break;
                case R.id.actionbar_discover_layout_attention:
                    Intent intentSetting = new Intent(v.getContext(), LogOutActivity.class);
                    v.getContext().startActivity(intentSetting);
                    break;
            }
        }
    }
}
