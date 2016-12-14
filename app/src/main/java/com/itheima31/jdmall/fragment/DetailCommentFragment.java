package com.itheima31.jdmall.fragment;

import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.utils.UIUtils;
import com.itheima31.jdmall.widgets.JingDongListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/24 14:30
 * 描述：    TODO
 */
public class DetailCommentFragment extends BaseFragment {
    @InjectView(R.id.item_detail_comment_lv)
    JingDongListView mItemDetailCommentLv;
    @InjectView(R.id.item_detail_comment_iv_backtop)
    ImageView mItemDetailCommentIvBacktop;
    private String[] mStrings = {"宝贝质量不错，很喜欢了", "不错，顶一个", "不错，顶一个",
            "忠心地感谢你，让我买到了梦寐以求的宝贝", "呵呵。商品这么快就到了，还不错哦"
            , "老朋友。我又来你店光顾了", "店家很讲信誉，而且很不错哦", "宝贝收到了，很喜欢"};
    private String[] names = {"张***乐", "李***晓", "李***hon", "赵***泽", "文***君", "李***明", "刘***晓", "张***天"};
    private int[] icons = {R.drawable.a8, R.drawable.a6, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a2, R.drawable.a7, R.drawable.a1};

    @Override
    public LoadingPager.LoadedResultEnum initData() {

        //模拟数据为真 TODO
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.fragment_detail_comment, null);

        ButterKnife.inject(this, view);
        DetailCommentBaseAdapter commentBaseAdapter = new DetailCommentBaseAdapter();

        mItemDetailCommentLv.setAdapter(commentBaseAdapter);

        //设置监听
        mItemDetailCommentLv.setOnJingDongRefreshListener(new JingDongListView.OnJingDongRefreshListener() {

            //子线程中触发加载数据
            @Override
            public void onRefresh() {
                SystemClock.sleep(1500);

            }

            //UI线程中,更新内容
            @Override
            public void onRefreshCompleted() {

            }
        });

        return view;
    }




    @OnClick(R.id.item_detail_comment_iv_backtop)
    public void onClick() {
        mItemDetailCommentLv.smoothScrollToPosition(0);

    }

    class DetailCommentBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return icons == null ? 0 : icons.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(UIUtils.getContext(), R.layout.item_detail_comment_listview, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.item_detail_comment_iv_icon);
                holder.name = (TextView) convertView.findViewById(R.id.item_detail_comment_tv_name);
                holder.des = (TextView) convertView.findViewById(R.id.item_detail_comment_tv_des);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.icon.setImageResource(icons[position]);
            holder.name.setText(names[position]);
            holder.des.setText(mStrings[position]);


            return convertView;
        }


    }

    class ViewHolder {
        ImageView icon;
        TextView name;
        TextView des;
    }

}
