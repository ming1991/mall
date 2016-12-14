package com.itheima31.jdmall.widgets;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.ProductlistActivity;
import com.itheima31.jdmall.bean.ClassifyBean;
import com.itheima31.jdmall.net.api.UrlConstants;
import com.itheima31.jdmall.utils.UIUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tony on 2016/10/25.
 */

public class ClassifyRecylistView extends RecyclerView {

    private final ClassifyBean.CategoryBean mBean;
    private final List<ClassifyBean.CategoryBean> mDatas;
    private final List<ClassifyBean.CategoryBean> mTotalDatas;

    private static final int NORMAL_ITEM = 0;
    private static final int TITLE_ITEM = 1;


    public ClassifyRecylistView(Context context, ClassifyBean.CategoryBean bean, boolean isLeve1) {
        super(context);
        mBean = bean;
        mDatas = mBean.level2List;

        mTotalDatas = new ArrayList<>();

        if (isLeve1) {
            for (int i = 0; i < mDatas.size(); i++) {
//                mTotalDatas.add(mDatas.get(i));
                for (int j = 0; j < mDatas.get(i).level2List.size(); j++) {
                    mTotalDatas.add(mDatas.get(i).level2List.get(j));
                    mTotalDatas.add(0, mDatas.get(i).level2List.get(j));
                }
            }
        } else {
            for (int i = 0; i < mDatas.size(); i++) {
                mTotalDatas.add(mDatas.get(i));
            }
        }


        init();
    }

    private void init() {
//        setLayoutManager(new LinearLayoutManager(getContext()));
//        setLayoutManager(new GridLayoutManager(getContext(), 3));
        setAdapter(new ItemRcyAdapter());
        setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        setItemAnimator(new DefaultItemAnimator());
    }

    class ItemRcyAdapter extends Adapter<ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ViewHolder holder = null;

            View item = LayoutInflater
                    .from(getContext()).inflate(R.layout.item_classify_pager_rcy, parent, false);
            holder = new ItemRcyAdapterHolder(item);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {


            ItemRcyAdapterHolder normalHolder = (ItemRcyAdapterHolder) holder;
            normalHolder.mItemTitle.setText(mTotalDatas.get(position).name);
            String url = UrlConstants.curBaseUrl + mTotalDatas.get(position).pic;

            initAnim(normalHolder);

            Picasso.with(UIUtils.getContext()).load(url).into(normalHolder.mItemImg);


            normalHolder.mItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), ProductlistActivity.class);
                    intent.putExtra("title", "productlist");
                    getContext().startActivity(intent);
                }
            });
            normalHolder.mItemView.setOnLongClickListener(new OnLongClickListener() {
                                                              @Override
                                                              public boolean onLongClick(View v) {
                                                                  showDeleteDialog();
                                                                  return true;
                                                              }

                                                              private void showDeleteDialog() {
                                                                  new AlertDialog.Builder(getContext()).setTitle("真要删除条目?")
                                                                          .setIcon(android.R.drawable.ic_dialog_info)
                                                                          .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                                              @Override
                                                                              public void onClick(DialogInterface dialog, int which) {

                                                                                  mTotalDatas.remove(position);
                                                                                  notifyItemRemoved(position);
                                                                                  notifyItemRangeChanged(position, mTotalDatas.size() - position);

                                                                              }
                                                                          })
                                                                          .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                                                                              @Override
                                                                              public void onClick(DialogInterface dialog, int which) {
                                                                              }
                                                                          }).show();
                                                              }
                                                          }

            );


        }

        private void initAnim(ItemRcyAdapterHolder normalHolder) {
            View imgView = normalHolder.mItemView;
            Random random = new Random();
            switch (random.nextInt(2)) {
                case 0:
                    imgView.setTranslationY(imgView.getHeight());
                    ViewCompat.animate(imgView).translationY(0).setDuration(random.nextInt(800) + 400)
                            .setInterpolator(new OvershootInterpolator(10))//
                            .start();//
                    break;
                case 1:
                    imgView.setTranslationY(-imgView.getHeight());
                    ViewCompat.animate(imgView).translationY(0).setDuration(random.nextInt(400) + 400)
                            .setInterpolator(new OvershootInterpolator(10))//
                            .start();//
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return mTotalDatas.size();
        }

        @Override
        public int getItemViewType(int position) {
            return NORMAL_ITEM;
        }
    }

    class ItemRcyAdapterHolder extends ViewHolder {

        private final View mItemView;

        @InjectView(R.id.item_img)
        ImageView mItemImg;
        @InjectView(R.id.item_title)
        TextView mItemTitle;


        public ItemRcyAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            mItemView = itemView;
        }


    }

}
