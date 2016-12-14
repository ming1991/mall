package com.itheima31.jdmall.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.utils.ToastUtils;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.Random;

/**
 * Created by yangg on 2016/10/28.
 *
 * @desc
 */
public class BonusActivity extends AppCompatActivity {
    private RecyclerView mBonusRecyclerView;

    private static int[] mBonus = {R.mipmap.jd_bonus_1, R.mipmap.jd_bonus_2, R.mipmap.jd_bonus_3, R.mipmap.jd_bonus_4, R.mipmap.jd_bonus_5, R.mipmap.jd_bonus_6, R.mipmap.jd_bonus_7};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus);

        mBonusRecyclerView = (RecyclerView) findViewById(R.id.activity_bonus);
        mBonusRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BonusAdapter bonusAdapter = new BonusAdapter();
        mBonusRecyclerView.setAdapter(bonusAdapter);
        bonusAdapter.setOnItemClickListener(new BonusAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Integer data) {
                ToastUtils.showToastInCenter(UIUtils.getContext(), 2, "您已成功领取该优惠券!", 0);
            }
        });
    }

    static class BonusAdapter extends RecyclerView.Adapter<BonusAdapter.MyViewHolder> implements View.OnClickListener {

        private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

        @Override
        public BonusAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_jd_bonus, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);
            itemView.setOnClickListener(this);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(BonusAdapter.MyViewHolder holder, int position) {
            holder.iv.setImageResource(mBonus[position]);
            holder.itemView.setTag(mBonus[position]);
            Random random = new Random();
            int days = random.nextInt(30) + 1;
            holder.tv.setText(days + "天后过期");
        }

        @Override
        public int getItemCount() {
            return mBonus.length;
        }

        @Override
        public void onClick(View v) {
            if (mOnRecyclerViewItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnRecyclerViewItemClickListener.onItemClick(v, (Integer) v.getTag());
            }
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.jd_bonus_iv);
                tv = (TextView) itemView.findViewById(R.id.jd_bonus_tv);
            }
        }

        public static interface OnRecyclerViewItemClickListener {
            void onItemClick(View view, Integer data);
        }

        public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
            mOnRecyclerViewItemClickListener = listener;
        }

    }
}
