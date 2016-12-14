package com.itheima31.jdmall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima31.jdmall.R;

import java.util.List;

/**
 * 创 建 者:  XQW
 * 创建时间:  2016/10/27 16:32
 * 描    述：
 */


public class GalleryAdapter extends  RecyclerView.Adapter<GalleryAdapter.ViewHolder>   {

public interface  OnItemClickListener{

    void OnItemClick(View view,int position);
}

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickLitener){

        this.mOnItemClickListener=mOnItemClickLitener;

    }

    private LayoutInflater mInflater;
    private List<String>  mDatas;

    public GalleryAdapter(Context context, List<String> datats)
    {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View arg0)
        {
            super(arg0);
        }

        TextView  mTxt;
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.activity_index_gallery_item,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.id_index_gallery_item_text);

        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i)
    {
        viewHolder.mTxt.setText(mDatas.get(i));


        if(mOnItemClickListener!=null){

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(viewHolder.itemView,i);
                }
            });

        }
    }



}
