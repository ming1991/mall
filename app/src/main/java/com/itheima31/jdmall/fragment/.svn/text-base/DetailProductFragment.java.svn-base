package com.itheima31.jdmall.fragment;

import android.view.View;
import android.widget.FrameLayout;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.controller.DetailProductButnHolder;
import com.itheima31.jdmall.controller.DetailProductCommentHolder;
import com.itheima31.jdmall.controller.DetailProductDesHolder;
import com.itheima31.jdmall.controller.DetailProductPicHolder;
import com.itheima31.jdmall.controller.DetailProductSelectHolder;
import com.itheima31.jdmall.protocol.DetailProductProtocol;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.UIUtils;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 14:28
 * 描述：    TODO
 */
    public class DetailProductFragment extends BaseFragment {
    @InjectView(R.id.flDetailProductButn)
    FrameLayout mFlDetailProductButn;

    @InjectView(R.id.flDetailProductDes)
    FrameLayout mFlDetailProductDes;
    @InjectView(R.id.flDetailProductSelect)
    FrameLayout mFlDetailProductSelect;
    @InjectView(R.id.flDetailProductComment)
    FrameLayout mFlDetailProductComment;
    @InjectView(R.id.flDetailProductPic)
    FrameLayout mFlDetailProductPic;
    private DetailProductBean mDetailProductBean;

    @Override
    public LoadingPager.LoadedResultEnum initData() {

        try {

            //解决缓存的问题,getInterfaceKey . pId;
            int pId = SPUtil.getInt(UIUtils.getContext(), "pId", 0);

            DetailProductProtocol detailProductProtocol = new DetailProductProtocol();
            mDetailProductBean = detailProductProtocol.loadData(pId);

            //Log.d("111", mDetailProductBean.product.name);

            //检验数据
            LoadingPager.LoadedResultEnum resultEnum = checkResData(mDetailProductBean);

           return resultEnum;

        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }

    }

    @Override
    public View initSuccessView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_product, null);
        ButterKnife.inject(this, view);

        //向容器添加(View+data=holder)

        //1底部button
        DetailProductButnHolder detailProductButnHolder = new DetailProductButnHolder(getActivity());
        View detailProductButnHolderView = detailProductButnHolder.mBaseView;

        detailProductButnHolder.refresHolderView(mDetailProductBean);  //还没加载数据
        mFlDetailProductButn.addView(detailProductButnHolderView);


        // 2上部的轮播图
        DetailProductPicHolder detailProductPicHolder = new DetailProductPicHolder();
        View detailProductPicHolderView = detailProductPicHolder.mBaseView;

        detailProductPicHolder.refresHolderView(mDetailProductBean);

        mFlDetailProductPic.addView(detailProductPicHolderView);


        //3描述 商品介个名称
        DetailProductDesHolder detailProductDesHolder = new DetailProductDesHolder();
        View detailProductDesHolderView = detailProductDesHolder.mBaseView;
        detailProductDesHolder.refresHolderView(mDetailProductBean); //还没加载数据

        mFlDetailProductDes.addView(detailProductDesHolderView);

        // 4<!--白条+加已选-->

        DetailProductSelectHolder detailProductSelectHolder = new DetailProductSelectHolder(getActivity());

        View detailProductSelectHolderView = detailProductSelectHolder.mBaseView;
        detailProductSelectHolder.refresHolderView(mDetailProductBean);  //还没加载数据

        mFlDetailProductSelect.addView(detailProductSelectHolderView);


        //5 评论
        //mFlDetailProductComment
        DetailProductCommentHolder detailProductCommentHolder =   new DetailProductCommentHolder(getActivity());
        View detailProductCommentHolderView = detailProductCommentHolder.mBaseView;
        detailProductCommentHolder.refresHolderView(mDetailProductBean);
        mFlDetailProductComment.addView(detailProductCommentHolderView);
        return view;
    }





}
