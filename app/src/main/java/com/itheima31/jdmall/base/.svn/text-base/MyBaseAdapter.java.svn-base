package com.itheima31.jdmall.base;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.controller.LoadMoreController;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;

import java.util.List;

/**
 * 创 建 者:  伍碧林
 * 创建时间:  2016/10/15 17:30
 * 描    述： 基于MyBaseAdapter做封装,针对的是getView方法
 */
public abstract class MyBaseAdapter<BEAN> extends BaseAdapter implements AdapterView.OnItemClickListener {

    public static final int VIEWTYPE_LOADMORE = 0;
    public static final int VIEWTYPE_NORMAL = 1;
    public  List<BEAN> mDatas;
    private ListView mListView;
    private LoadMoreController mLoadMoreController;
    private LoadMoreTask mLoadMoreTask;
    private int mCurState;


    public MyBaseAdapter(List<BEAN> datas, ListView listView) {
        mDatas = datas;
        mListView = listView;
        mListView.setOnItemClickListener(this);
    }

    @Override
    public Object getItem(int i) {
        if (mDatas != null) {
            return mDatas.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        int curItemViewType = getItemViewType(position);
            /*--------------- 决定根视图长什么样子 ---------------*/
        BaseController baseHolder;
        if (convertView == null) {
            if (curItemViewType == VIEWTYPE_LOADMORE) {//加载更多
                baseHolder = getLoadMoreController();
            } else {//普通条目类型
                baseHolder = getSpecialbaseHolder(position);
            }

        } else {
            baseHolder = (BaseController) convertView.getTag();
        }

            /*--------------- 得到ItemBean,然后进行数据和视图的绑定 ---------------*/
        if (curItemViewType == VIEWTYPE_LOADMORE) {//加载更多

            if (hasLoadMore()) {
                mLoadMoreController.setDataAndRefreshView(LoadMoreController.LOADMORE_LOADING);
                //TODO触发加载更多的数据
                triggerLoadMoreData();
            } else {
                mLoadMoreController.setDataAndRefreshView(LoadMoreController.LOADMORE_NONE);
            }


        } else {//普通条目类型
            //data
            BEAN data = mDatas.get(position);


            //传递数据给holder,让holder进行数据和视图的绑定
            baseHolder.setDataAndRefreshView(data);
            baseHolder.refresHolderViewWithPosition(data,position);

        }

        View holderView = baseHolder.mBaseView;

        return holderView;//就是希望得到一个经过了数据和视图绑定的view
    }


    /**
     * @des 交给子类, 子类是必须实现, 定义成为抽象方法
     */
    @NonNull
    public abstract BaseController getSpecialbaseHolder(int position);

    /*--------------- listView中显示几种ViewType begin ---------------*/

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;//1(普通条目)+1(加载更多)=2
    }


    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return VIEWTYPE_LOADMORE;//加载更多
        } else {
            return getNormalItemViewType(position);
        }
    }

    /**
     * @des 得到普通条目的ViewType类型
     * @des 子类可以覆写该方法, 返回更多普通条目的类型
     */
    public int getNormalItemViewType(int position) {
        return VIEWTYPE_NORMAL;//默认是1
    }

    @Override
    public int getCount() {

        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 1;
    }

    /**
     * 得到加载更多的holder
     *
     * @return
     */
    private LoadMoreController getLoadMoreController() {
        if (mLoadMoreController == null) {
            mLoadMoreController = new LoadMoreController();
        }
        return mLoadMoreController;
    }

    /**
     * @return
     * @des 是否有加载更多
     * @des 子类可以覆写该方法, 最终决定是否有加载更多
     */
    public boolean hasLoadMore() {
        return false;//默认没有加载更多
    }

    /**
     * 触发加载更多的数据
     */
    private void triggerLoadMoreData() {
        if (mLoadMoreTask == null) {
            mCurState = LoadMoreController.LOADMORE_LOADING;
            mLoadMoreController.setDataAndRefreshView(mCurState);

            //异步加载
            mLoadMoreTask = new LoadMoreTask();
            ThreadPoolProxyFactory.createNormalPoolProxy().execute(mLoadMoreTask);
        }
    }

    class LoadMoreTask implements Runnable {
        private static final int PAGERSIZE = 20;

        @Override
        public void run() {
            /*--------------- 定义数据 ---------------*/
            mCurState = 0;
            List<BEAN> loadMoreList = null;

            /*--------------- 真正的在子线中加载更多的数据,得到数据,处理数据 ---------------*/
            try {
                loadMoreList = onLoadMoreData();
                if (loadMoreList == null) {
                    mCurState = LoadMoreController.LOADMORE_NONE;//没有加载更多
                } else {
                    if (loadMoreList.size() < PAGERSIZE) {
                        mCurState = LoadMoreController.LOADMORE_NONE;//没有加载更多
                    } else {
                        mCurState = LoadMoreController.LOADMORE_LOADING;//有加载更多
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mCurState = LoadMoreController.LOADMORE_ERROR;//加载更多失败,点击重试
            }
            /*--------------- 创建2个临时变量 ---------------*/
            final List<BEAN> finalLoadMoreList = loadMoreList;
            final int finalCurState = mCurState;

            /*--------------- 刷新2个ui ---------------*/
            MyApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    //刷新UI-->listview-->更新数据集,然后使用notifyDataSetChanged()-->mDatas.addAll(loadMoreList)
                    if (finalLoadMoreList != null && finalLoadMoreList.size() > 0) {
                        //更新数据集
                        mDatas.addAll(finalLoadMoreList);
                        //刷新listView
                        notifyDataSetChanged();

                    }
                    //刷新UI-->mLoadMoreController-->得到最新的状态,调用setDataAndRefreshHolderView()-->mCurState
                    mLoadMoreController.setDataAndRefreshView(finalCurState);

                }
            });
            //置空任务
            mLoadMoreTask = null;
        }
    }

    /**
     * @return
     * @des 当复写hasloadMore时 需要实现
     */
    public List<BEAN> onLoadMoreData() throws Exception {
        return null;
    }
    /*--------------- listView中显示几种ViewType end ---------------*/

    /*--------------- 处理item的点击事件 ---------------*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - mListView.getHeaderViewsCount();

        int curItemViewType = getItemViewType(position);
        if (curItemViewType == VIEWTYPE_LOADMORE) {
            if (mCurState == LoadMoreController.LOADMORE_ERROR) {
                triggerLoadMoreData();
            }
        } else {
            onNormalItemClick(parent, view, position, id);
        }
    }

    /**
     * 子类覆写方法,处理普通条目的点击事件
     */
    public void onNormalItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
