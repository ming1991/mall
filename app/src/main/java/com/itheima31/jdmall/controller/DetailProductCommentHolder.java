package com.itheima31.jdmall.controller;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.DetailActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.bean.DetailProductBean;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.InjectView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/25 17:05
 * 描述：    TODO
 */
public class DetailProductCommentHolder extends BaseController<DetailProductBean> {

    @InjectView(R.id.item_detail_product_comment_tv_num)
    TextView mItemDetailProductCommentTvNum;
    @InjectView(R.id.item_detail_product_comment_tv_des)
    TextView mItemDetailProductCommentTvDes;
    @InjectView(R.id.item_detail_product_comment_iv_go)
    ImageView mItemDetailProductCommentIvGo;
    private final DetailActivity mDetailActivity;

    public DetailProductCommentHolder(FragmentActivity activity) {
        mDetailActivity = (DetailActivity) activity;

    }

    @Override
    public View getView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_detail_product_comment, null);
        return view;
    }

    @Override
    public void refresHolderView(DetailProductBean data) {

        mItemDetailProductCommentTvNum.setText("评论("+data.product.commentCount+")");


        FragmentProduct2Comment fragmentProduct2Comment = new FragmentProduct2Comment();

        mDetailActivity.setOnFragmentProduct2CommentListener(fragmentProduct2Comment);

        mItemDetailProductCommentIvGo.setOnClickListener(new CommentOnClickListener());

    }

    class FragmentProduct2Comment implements DetailActivity.OnFragmentProduct2CommentListener {
        @Override
        public void onFragmentProduct2Comment(ViewPager viewPager) {

            viewPager.setCurrentItem(2,true);

        }
    }

    class CommentOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {  //跳转到评论的fragment界面

            //实现方法通过接口回调,传到当前fragment --activity -- viewPager -- setCurrent --fragment  //TODO 错误方法
            mDetailActivity.forSkip();

        }
    }

    /*private OnFragmentProduct2CommentListener onFragmentProduct2CommentListener;
    public interface OnFragmentProduct2CommentListener {

        void onFragmentProduct2Comment();
    }
    public void setOnFragmentProduct2CommentListener(OnFragmentProduct2CommentListener onFragmentProduct2CommentListener){
        this.onFragmentProduct2CommentListener = onFragmentProduct2CommentListener;
    }*/

}
