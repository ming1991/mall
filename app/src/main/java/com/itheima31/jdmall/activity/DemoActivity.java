package com.itheima31.jdmall.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.widgets.FilterDialog;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Tony on 2016/10/23.
 * <p>
 * 使用BaseActivity参考Demo
 * <p>
 * 下拉刷新用    jindonglistView
 * 设置监听器 setOnJingDongRefreshListener 完成对应的回调函数
 * <p>
 * 上拉刷新用  GooglePlay里面的 MyBaseAdapter
 * <p>
 * <p>
 * 具体使用参考本类
 */

public class DemoActivity extends BaseActivity {


    @InjectView(R.id.demo_btn)
    Button mDemoBtn;

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {

//        HttpManager.getInstance()
//                .doHttpDeal(new ClassifyApi(new HttpOnNextListener<ClassifyBean>() {
//                    @Override
//                    public void onNext(ClassifyBean entiry) {
////                        Toast.makeText(UIUtils.getContext(), "" + entiry.category.get(0).name, Toast.LENGTH_SHORT).show();
//                        LogUtils.e("onNext:entiry:" + entiry.category.get(0).name);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        super.onError(e);
////                        Toast.makeText(UIUtils.getContext(), "" + e, Toast.LENGTH_SHORT).show();
//                        LogUtils.e("onError:e:" + e.toString());
//                    }
//                }));

        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View onInitSuccessView() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.activityt_demo, null);
        ButterKnife.inject(this, rootView);

        return rootView;
    }


    @OnClick(R.id.demo_btn)
    public void onClick() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.filter_dialog, null);
        FilterDialog dialog = new FilterDialog(this);
        dialog.show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(inflate);
//
//        AlertDialog dialog = builder.show();
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
//        attributes.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        attributes.gravity = Gravity.RIGHT;
//        attributes.horizontalMargin = 0;
//        attributes.verticalMargin = 0;
//        window.setAttributes(attributes);
//        dialog.show();

    }
}
