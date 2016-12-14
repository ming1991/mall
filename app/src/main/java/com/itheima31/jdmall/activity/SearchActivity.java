package com.itheima31.jdmall.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.adapter.SearchRecViewAdapter;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.SearchBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.net.HttpManager;
import com.itheima31.jdmall.net.HttpOnNextListener;
import com.itheima31.jdmall.net.api.SearchApi;
import com.itheima31.jdmall.utils.ResultUtils;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.StringUtils;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.act_search_hot_ll)
    LinearLayout mLlHot;
    @InjectView(R.id.act_search_ll_history)
    RecyclerView mRecView;
    @InjectView(R.id.act_search_search_bar)
    EditText mSearchBar;
    @InjectView(R.id.act_search_clean)
    TextView mClean;
    @InjectView(R.id.act_search_search_sure)
    TextView mSearchSure;
    @InjectView(R.id.act_search_back)
    ImageView mBack;


    private List<String> mDatas;
    private boolean isError = false;
    public static final String KEY_TEXT = "key";
    private int curPosition = 0;
    private ArrayList<String> mHistoryDatas;
    private SearchRecViewAdapter mAdapter;
    private AlertDialog mDialog;

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {

        HttpManager.getInstance().doHttpDeal(new SearchApi(new HttpOnNextListener<SearchBean>() {
            @Override
            public void onNext(SearchBean bean) {
                mDatas = bean.searchKeywords;
            }

            @Override
            public void onError(Throwable e) {
                isError = true;
            }
        }));

        SystemClock.sleep(500);
        if (isError) {
            isError = false;
            return LoadingPager.LoadedResultEnum.ERROR;
        }

        mHistoryDatas = new ArrayList<>();
        String keys = SPUtil.getString(this, Constants.SEARCH_HISTORY, "");
        if (!StringUtils.isEmpty(keys)) {
            String[] split = keys.split(",");
            for (String s : split) {
                mHistoryDatas.add(s);
            }
        }

        return ResultUtils.checkResData(mDatas);
    }

    @Override
    public View onInitSuccessView() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_search, null);
        ButterKnife.inject(this, rootView);

        initRootView();
        initEvent();

        return rootView;
    }

    private void initEvent() {

        mRecView.setLayoutManager(new LinearLayoutManager(this));
        mRecView.setAdapter(mAdapter = new SearchRecViewAdapter(this, mHistoryDatas));
//        mRecView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mSearchSure.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mClean.setOnClickListener(this);

    }

    private void initRootView() {

        for (int i = 0; i < mDatas.size(); i++) {
            TextView textView = new TextView(this);
            textView.setText(mDatas.get(i));
            textView.setBackgroundResource(R.drawable.actionbar_texst_bg_shape);
            textView.setTextSize(UIUtils.dip2px(7));
            int left = UIUtils.dip2px(10);
            int top = UIUtils.dip2px(2);
            textView.setPadding(left, top, left, top);
            textView.setOnClickListener(mHotListener);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = UIUtils.dip2px(10);
            mLlHot.addView(textView, params);
        }

    }

    @Override
    public void initActionBar() {
        getSupportActionBar().hide();
    }

    public void goToList(String text) {
        Intent intent = new Intent(UIUtils.getContext(), ProductlistActivity.class);
        if (!StringUtils.isEmpty(text)) {
            intent.putExtra(KEY_TEXT, text);
            String keys = SPUtil.getString(UIUtils.getContext(), Constants.SEARCH_HISTORY, "");
            SPUtil.putString(UIUtils.getContext(), Constants.SEARCH_HISTORY, keys + text + ",");
        }
        startActivity(intent);
        finish();
    }

    private View.OnClickListener mHotListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String key = ((TextView) v).getText().toString();
            goToList(key);

        }
    };

    @Override
    public void onClick(View v) {
        String text = "";
        switch (v.getId()) {
            case R.id.act_search_back:
                finish();
                break;
            case R.id.act_search_search_sure:
                text = mSearchBar.getText().toString();
                if (StringUtils.isEmpty(text)) {
                    text = mSearchBar.getHint().toString();
                }
                goToList(text);
                break;
            case R.id.act_search_clean:
                showCleanDialog();
                break;
            case R.id.dialog_search_cancel:
                mDialog.dismiss();
                break;
            case R.id.dialog_search_sure:
                SPUtil.putString(UIUtils.getContext(), Constants.SEARCH_HISTORY, "");
                mRecView.removeAllViews();
                mAdapter.notifyDataSetChanged();
                mHistoryDatas.clear();
                mDialog.dismiss();
                break;
        }
    }

    private void showCleanDialog() {
        mDialog = new AlertDialog.Builder(this)
                .setView(LayoutInflater.from(this).inflate(R.layout.dialog_search_history, null))
                .show();
        mDialog.findViewById(R.id.dialog_search_cancel).setOnClickListener(this);
        mDialog.findViewById(R.id.dialog_search_sure).setOnClickListener(this);
    }

}