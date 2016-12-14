package com.itheima31.jdmall.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventJumpShop;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class SpinnerView_color extends RelativeLayout {



    private ArrayList<String> datas = new ArrayList<String>();
    private PopupWindow mpopupWindow;
    private TextView mTv;

    public SpinnerView_color(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_spinner, this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        datas.add("红色");
        datas.add("绿色");
    }

    private void initEvent() {
        mTv.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                showPopWindow();
            }

            
        });
        
    }
    public void setText(String color){
        if(mTv!=null){
            mTv.setText(color);
        }

    }
    private void showPopWindow() {
        if(mpopupWindow==null){
            int width = mTv.getWidth();
            int height = 300;
            mpopupWindow = new PopupWindow(width,height);
            ListView lv = new ListView(getContext());
            //lv.setBackgroundResource(R.drawable.listview_background);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mTv.setText(datas.get(position));
                    if(datas.get(position).equals("绿色")){
                        EventBus.getDefault().post(new EventJumpShop().setId(Constants.COLOR_GREEN));
                    }else if(datas.get(position).equals("红色")){
                        EventBus.getDefault().post(new EventJumpShop().setId(Constants.COLOR_RED));
                    }

                    mpopupWindow.dismiss();
                }
            });
            mpopupWindow.setContentView(lv);
            mpopupWindow.setOutsideTouchable(true);
            mpopupWindow.setBackgroundDrawable(new ColorDrawable());
            mpopupWindow.setFocusable(true);
        }
        mpopupWindow.showAsDropDown(mTv);
    }


    private void initView() {
        mTv = (TextView) findViewById(R.id.spinner_tv);
    }

    public SpinnerView_color(Context context) {
        this(context, null);
    }

    private BaseAdapter adapter = new BaseAdapter() {
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView = View.inflate(getContext(), R.layout.item_spinnerview_color, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            final String color = datas.get(position);
            holder.color_tv.setText(color);
            if(color.equals("红色")){
                holder.color_tv.setTextColor(Color.RED);
            }else if(color.equals("绿色")){
                holder.color_tv.setTextColor(Color.GREEN);
            }

            return convertView;
        }
        
        @Override
        public long getItemId(int position) {
            return position;
        }
        
        @Override
        public String getItem(int position) {
            return datas.get(position);
        }
        
        @Override
        public int getCount() {
            return datas==null? 0 : datas.size();
        }
        class ViewHolder{
            TextView color_tv;
            public ViewHolder(View root){

                color_tv=(TextView) root.findViewById(R.id.color_tv);
            }
        }
    };

}
