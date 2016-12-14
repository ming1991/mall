package com.itheima31.jdmall.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.HomeBean;
import com.itheima31.jdmall.controller.HomeTabController;
import com.itheima31.jdmall.controller.RecommendBaseController;
import com.itheima31.jdmall.controller.ToPicViewContorller;
import com.itheima31.jdmall.controller.TopViewController;
import com.itheima31.jdmall.protocol.HomeProtocol;
import com.itheima31.jdmall.utils.UIUtils;

import java.io.IOException;


/**
 * Created by Tony on 2016/10/21.
 */

public class HomeFrament extends BaseFragment {

    private HomeBean mHomeBean;

    public LoadingPager.LoadedResultEnum initData() {

        HomeProtocol homeProtocol = new HomeProtocol();
        try {
            mHomeBean = homeProtocol.loadData(0);
            return checkResData(mHomeBean);
        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;

        }

    }

    @Override
    public View initSuccessView() {
        ScrollView homeView = new ScrollView(UIUtils.getContext());

        LinearLayout container = new LinearLayout(UIUtils.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setLayoutParams(params);

        /***************Home Start********************/

        //轮播图
        TopViewController topView = new TopViewController();
        container.addView(topView.mBaseView);
        topView.refresHolderView(mHomeBean);

        //导航栏
        HomeTabController homeTabController = new HomeTabController();
        container.addView(homeTabController.mBaseView);

        //滚动快报
        ToPicViewContorller toPicViewContorller=new ToPicViewContorller(getContext());
        toPicViewContorller.setClickable(false);
        container.addView(toPicViewContorller);

        //大海报
        View poster = View.inflate(UIUtils.getContext(), R.layout.item_home_poster, null);

        container.addView(poster);

        //好货推荐
        //底部轮播图
        RecommendBaseController recommend=new RecommendBaseController();
        container.addView(recommend.mBaseView);
        recommend.refresHolderView(null);




        /***************Home End********************/

        homeView.addView(container);
        return homeView;
    }

}
