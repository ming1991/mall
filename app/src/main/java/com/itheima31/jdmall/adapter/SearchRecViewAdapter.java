package com.itheima31.jdmall.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.ProductlistActivity;
import com.itheima31.jdmall.activity.SearchActivity;
import com.itheima31.jdmall.utils.StringUtils;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tony on 2016/10/29.
 */

public class SearchRecViewAdapter extends RecyclerView.Adapter<SearchHolder> {

    private final Context mContext;
    private final List<String> mDatas;


    public SearchRecViewAdapter(Context context, List<String> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_search_rcy, parent, false);
        SearchHolder holder = new SearchHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, final int position) {
        holder.mItemTvText.setText(mDatas.get(position));
        holder.mItemTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToList(mDatas.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void goToList(String text) {
        Intent intent = new Intent(UIUtils.getContext(), ProductlistActivity.class);
        if (!StringUtils.isEmpty(text)) {
            intent.putExtra(SearchActivity.KEY_TEXT, text);
        }
        mContext.startActivity(intent);
        ((Activity)mContext).finish();
    }

}

class SearchHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.item_search_rcy_tv)
    TextView mItemTvText;

    public SearchHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

}


