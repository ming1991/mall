package com.itheima31.jdmall.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 类    名:  BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity {

    private LoadingPager mLoadingPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initView();
        setContentView(mLoadingPager);
        //触发访问网络
        initData();
        initActionBar();
    }

    private void initView() {
        mLoadingPager = new LoadingPager(this) {
            @Override
            public LoadedResultEnum initData() {
                return onInitData();
            }

            @Override
            public View initSuccessView() {
                return onInitSuccessView();
            }
        };
    }

    /**
      * 触发加载数据  调用
     */
    private void initData() {
        mLoadingPager.triggerLoadData();

    }

    /**
     *  调用顺序
     *
     *  1.从Intent中获取数据  非强制实现
     *  例如 getIntent().getExtry();
     */
    public void init() {
    }



    /**
     *2.加载网络数据 子类必须实现
     *  子线程运行
     * 校验数据用ResultUtils
     */
    public abstract LoadingPager.LoadedResultEnum onInitData();

    /**
     * 3 加载成功视图 子类必须实现
     *  主线程运行
     *   绑定事件 都在这里完成
     * @return
     */
    public abstract View onInitSuccessView();

    /**
     * 4 初始化actionbar
     *  建议 可以用  getSupportActionBar().hide();  隐藏,然后自定义
     */
    public void initActionBar() {

    }

}
