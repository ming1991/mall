package com.itheima31.jdmall.fragment;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.activity.AddressActivity;
import com.itheima31.jdmall.activity.BonusActivity;
import com.itheima31.jdmall.activity.LogOutActivity;
import com.itheima31.jdmall.activity.LoginActivity;
import com.itheima31.jdmall.activity.OrderActivity;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.base.BaseController;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.base.MyBaseAdapter;
import com.itheima31.jdmall.bean.MineBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.controller.MineController;
import com.itheima31.jdmall.event.EventLogIn;
import com.itheima31.jdmall.event.EventLogOut;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.AccountCenterProtocol;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.utils.ToastUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Tony on 2016/10/21.
 */

public class MineFragment extends BaseFragment {

    @InjectView(R.id.mine_portrait_iv)
    ImageView mMinePortraitIv;
    @InjectView(R.id.mine_content_lv)
    ListView mMineContentLv;
    @InjectView(R.id.loged_in_layout)
    RelativeLayout mMineLogInLayout;
    @InjectView(R.id.mine_login_potrait)
    ImageView mMineLoginPotrait;
    @InjectView(R.id.login_account_name)
    TextView mLoginAccountName;
    @InjectView(R.id.login_bounus_tv)
    TextView mMemberLoginbounusTv;
    @InjectView(R.id.pay_waiting_iv)
    ImageView mPayWaitingIv;
    @InjectView(R.id.send_waiting_iv)
    ImageView mSendWaitingIv;
    @InjectView(R.id.receive_waiting_iv)
    ImageView mReceiveWaitingIv;
    @InjectView(R.id.assess_iv)
    ImageView mAssessIv;
    @InjectView(R.id.repair_waiting_iv)
    ImageView mRepairWaitingIv;
    @InjectView(R.id.mine_login_register_tv)
    TextView mToLogTv;
    @InjectView(R.id.login_vip_icon)
    ImageView mLoginVipIcon;

    private List<String> mDatas;
    private boolean isLogIn = true;
    private TextView mPopShow;

    public LoadingPager.LoadedResultEnum initData() {

        mDatas = new ArrayList<>();
        String[] lists = UIUtils.getStrings(R.array.mine_fragment_lv_lists);

        for (int i = 0; i < lists.length; i++) {
            mDatas.add(lists[i]);
        }

        return LoadingPager.LoadedResultEnum.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        EventBus.getDefault().register(this);
        View rootView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.mine_fragment, null);
        ButterKnife.inject(this, rootView);

        String[] userInfos = SPUtils.getString(Constants.LOGED_ACCOUNT);

        if (userInfos[0].equals("login")) {

            mMinePortraitIv.setVisibility(View.GONE);
            mToLogTv.setVisibility(View.GONE);
            mMineLogInLayout.setVisibility(View.VISIBLE);
            String account = userInfos[2];
            String userId = userInfos[1];
            ThreadPoolProxyFactory.createNormalPoolProxy().execute(new AccountCenterTask(account, userId));

        } else {
            mMinePortraitIv.setVisibility(View.VISIBLE);
            mToLogTv.setVisibility(View.VISIBLE);
            mMineLogInLayout.setVisibility(View.GONE);
        }
        initEvent();
        return rootView;
    }

    private void initEvent() {

        mMineContentLv.setAdapter(new MineAdapter(mDatas, mMineContentLv));

        mMineContentLv.setOnItemClickListener(new MineFragmentListener());

    }


    @OnClick({R.id.pay_waiting_iv, R.id.send_waiting_iv, R.id.receive_waiting_iv, R.id.assess_iv, R.id.repair_waiting_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_waiting_iv:
                break;
            case R.id.send_waiting_iv:
                break;
            case R.id.receive_waiting_iv:
                break;
            case R.id.assess_iv:
                break;
            case R.id.repair_waiting_iv:
                break;
        }
    }

    class MineFragmentListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String[] userInfo = SPUtils.getString(Constants.LOGED_ACCOUNT);
            String response = userInfo[0];
            switch (position) {
                case 0:
                    // TODO: 2016/10/27 我的订单
                    if (response.equals("login") || response.equals("register")) {
                        startIntent(new OrderActivity());
                    } else {
                        ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "您还没有登录,无法查看", Toast.LENGTH_SHORT);
                    }
                    break;
                case 1:
                    if (response.equals("login") || response.equals("register")) {
                        startIntent(new BonusActivity());
                    } else {
                        ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "您还没有登录,无法查看", Toast.LENGTH_SHORT);
                    }

                    break;
                case 2:
                    if (response.equals("login") || response.equals("register")) {
                        Toast.makeText(UIUtils.getContext(), mDatas.get(position), Toast.LENGTH_SHORT).show();
                    } else {
                        ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "您还没有登录,无法查看", Toast.LENGTH_SHORT);
                    }
                    break;
                case 3: //账户中心
                    startActivity(new Intent(mActivity, LogOutActivity.class));
                    break;
                case 4: //地址管理
                    if (response.equals("login") || response.equals("register")) {
                        startActivity(new Intent(mActivity, AddressActivity.class));
                    } else {
                        ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "您还没有登录,无法查看", Toast.LENGTH_SHORT);
                    }
                    break;
            }
        }
    }

    public void startIntent(Activity activity) {
        Intent intent = new Intent(UIUtils.getContext(), activity.getClass());
        startActivity(intent);
    }


    @OnClick({R.id.mine_login_register_tv, R.id.mine_portrait_iv})
    public void toLog() {
        Intent intent = new Intent(mActivity, LoginActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventIn(EventLogIn event) {
        String userId = event.userid;
        String account = event.account;

        mMinePortraitIv.setVisibility(View.GONE);
        mToLogTv.setVisibility(View.GONE);
        mMineLogInLayout.setVisibility(View.VISIBLE);
     /*------ 用户中心 ------*/
        ThreadPoolProxyFactory.createNormalPoolProxy().execute(new AccountCenterTask(account, userId));

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventOut(EventLogOut event) {
        mPopShow.setVisibility(View.GONE);

        mMinePortraitIv.setVisibility(View.VISIBLE);
        mToLogTv.setVisibility(View.VISIBLE);
        mMineLogInLayout.setVisibility(View.GONE);
    }

    class AccountCenterTask implements Runnable {

        String accountName;
        String userid;

        public AccountCenterTask(String accountName, String userid) {
            this.accountName = accountName;
            this.userid = userid;
        }

        @Override
        public void run() {
            AccountCenterProtocol accountCenterProtocol = new AccountCenterProtocol(userid);
            try {
                MineBean mineBean = accountCenterProtocol.loadData(0);
                int bonus = mineBean.userInfo.bonus;

                setUserInfo(bonus);

                final int favoritesCount = mineBean.userInfo.favoritesCount;
                final int orderCount = mineBean.userInfo.orderCount;

                String value = bonus + "#" + favoritesCount + "#" + orderCount;
                SPUtils.putString(Constants.ACCOUNT_INFO, value);

                UIUtils.getHandler().post(new Runnable() {
                    @Override
                    public void run() {

                        View view = mMineContentLv.getChildAt(0);
                        mPopShow = (TextView) view.findViewById(R.id.pop_text_show);
                        if (orderCount > 0) {
                            mPopShow.setVisibility(View.VISIBLE);
                        } else {
                            mPopShow.setVisibility(View.GONE);
                        }

                        View view2 = mMineContentLv.getChildAt(2);
                        TextView popShow2 = (TextView) view2.findViewById(R.id.pop_text_show);
                        if (favoritesCount > 0) {
                            mPopShow.setVisibility(View.VISIBLE);
                        } else {
                            mPopShow.setVisibility(View.GONE);
                        }

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                String[] account_info = SPUtils.getString(Constants.ACCOUNT_INFO);
                if (account_info != null) {
                    setUserInfo(Integer.valueOf(account_info[0]));
                } else {
                    setUserInfo(0);
                }
                ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "网络出现异常", 0);

            }
        }

        private void setUserInfo(final int bonus) {
            MyApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    mLoginAccountName.setText(accountName);
                    mMemberLoginbounusTv.setText("积分 " + bonus);
                    if (bonus == 0) {
                        mLoginVipIcon.setImageResource(R.mipmap.vip0);
                    } else if (bonus > 0 && bonus < 30) {
                        mLoginVipIcon.setImageResource(R.mipmap.vip_1_29);
                    } else if (bonus >= 30 && bonus < 100) {
                        mLoginVipIcon.setImageResource(R.mipmap.vip_30_99);
                    } else if (bonus >= 100 && bonus < 1001) {
                        mLoginVipIcon.setImageResource(R.mipmap.vip_100_1000);
                    } else {
                        mLoginVipIcon.setImageResource(R.mipmap.vip_1001);
                    }
                }
            });
        }
    }

    class MineAdapter extends MyBaseAdapter<String> {

        public MineAdapter(List<String> datas, ListView listView) {
            super(datas, listView);
        }

        @Override
        public BaseController getSpecialbaseHolder(int position) {

            return new MineController();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        EventBus.getDefault().unregister(this);
    }
}
