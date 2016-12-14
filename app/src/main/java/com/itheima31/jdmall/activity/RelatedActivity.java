package com.itheima31.jdmall.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.utils.ToastUtils;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yangg on 2016/10/28.
 *
 * @desc
 */
public class RelatedActivity extends AppCompatActivity {
    public static final String TAG = "RelatedActivity";
    @InjectView(R.id.back_share)
    ImageView mBack;
    @InjectView(R.id.share)
    ImageView mShare;
    @InjectView(R.id.jd_copyright_anounce)
    TextView mJdCopyrightAnounce;
    @InjectView(R.id.tv_about)
    TextView mTvAbout;

    private View mContentView;
    private boolean flags = true;

    private int[] share_App = {R.mipmap.share_qq, R.mipmap.share_qq_blog, R.mipmap.share_qq_zone, R.mipmap.share_sina_blog, R.mipmap.share_wechat, R.mipmap.share_door};
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_related_jd);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.back_share, R.id.share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_share:
                ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "退出", 0);
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                finish();
                break;
            case R.id.share:
                if (flags) {
                    showPopupWindow();
                } else {
                    mPopupWindow.dismiss();
                }
                flags = !flags;
                break;
        }
    }

    private void showPopupWindow() {

        mContentView = LayoutInflater.from(RelatedActivity.this).inflate(R.layout.popupwindow_layout, null);
        initView();

        mPopupWindow = new PopupWindow(mContentView, LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(mContentView);

        mPopupWindow.setFocusable(true);
        ColorDrawable colorDrawable = new ColorDrawable(0x00000000);
        mPopupWindow.setBackgroundDrawable(colorDrawable);
//        mPopupWindow.setOutsideTouchable(true);

//        View rootView = LayoutInflater.from(RelatedActivity.this).inflate(R.layout.activity_related_jd, null);
        mPopupWindow.showAtLocation(mBack, Gravity.BOTTOM, 0, 0);
//        mPopupWindow.showAsDropDown(mTvAbout,0,0);



    }

    private void initView() {
        TextView share_Tv = (TextView) mContentView.findViewById(R.id.to_share_tv);
        RecyclerView share_RV = (RecyclerView) mContentView.findViewById(R.id.to_share_recyclerView);

        share_Tv.setText("----------<>    分享到    <>----------");
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        share_RV.setLayoutManager(layoutManager);
        share_RV.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView_RV = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.share_recyclerview_item, null);
            MyViewHolder myViewHolder = new MyViewHolder(itemView_RV);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.iv.setImageResource(share_App[position]);
        }

        @Override
        public int getItemCount() {

            return share_App.length;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.to_share_item_recyclerview);
        }
    }
}
