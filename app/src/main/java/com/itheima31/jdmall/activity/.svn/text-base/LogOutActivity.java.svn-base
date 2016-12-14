package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.base.BaseActivity;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.base.MyBaseAdapter;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.controller.MineController;
import com.itheima31.jdmall.event.EventLogOut;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.utils.ToastUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yangg on 2016/10/25.
 *
 * @desc
 */
public class LogOutActivity extends BaseActivity {

    @InjectView(R.id.mine_setting_lv)
    ListView mMineSettingLv;
    @InjectView(R.id.mine_logout_btn)
    Button mMineLogoutBtn;

    private List<String> mDatas;

    @Override
    public LoadingPager.LoadedResultEnum onInitData() {
        String[] stringArray = getResources().getStringArray(R.array.mine_fragment_lv_setting);
        mDatas = new ArrayList<>();
        for (int i = 0; i < stringArray.length; i++) {
            mDatas.add(stringArray[i]);
        }
        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View onInitSuccessView() {
        View rootView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.activity_log_out, null);
        ButterKnife.inject(this, rootView);
        initEvent();
        return rootView;
    }

    private void initEvent() {
        mMineSettingLv.setAdapter(new LogOutAdapter(mDatas, mMineSettingLv));
        mMineSettingLv.setOnItemClickListener(new MyOnItemClickListener());
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 6:
                    startActivity(new Intent(UIUtils.getContext(), RelatedActivity.class));
                    break;
            }
        }
    }

    class LogOutAdapter extends MyBaseAdapter<String> {

        public LogOutAdapter(List<String> datas, ListView listView) {
            super(datas, listView);
        }

        @NonNull
        @Override
        public BaseController getSpecialbaseHolder(int position) {
            return new MineController();
        }
    }

    @OnClick({R.id.mine_logout_btn, R.id.logout_back_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout_back_iv:
                finish();
                break;
            case R.id.mine_logout_btn:
                String[] userInfo = SPUtils.getString(Constants.LOGED_ACCOUNT);
                String response = userInfo[0];
                if (response.equals("login") || response.equals("register")) {
                    SPUtils.clearSharedPref();
                    ToastUtils.showToastInCenter(UIUtils.getContext(), 2, "注销成功", Toast.LENGTH_SHORT);

                    EventLogOut eventLogOut = new EventLogOut();
                    EventBus.getDefault().post(eventLogOut);

                    finish();
                } else {
                    ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "您还未登录,请先登录", Toast.LENGTH_SHORT);
                }
                break;
        }
    }

}
