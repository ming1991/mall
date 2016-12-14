package com.itheima31.jdmall.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.adapter.AddressAdapter;
import com.itheima31.jdmall.base.BaseFragment;
import com.itheima31.jdmall.base.LoadingPager;
import com.itheima31.jdmall.bean.AddressBean;
import com.itheima31.jdmall.bean.AddressListBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.protocol.AddressProtocol;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author：wizong
 * when：created by 2016/10/25:14:15
 * explain：地址管理页面的Fragment模块
 */
public class AddressFragment extends BaseFragment {

    private AddressBean     mAddressBean;
    private ListView        mLv;
    private AddressAdapter  mAddressAdapter;
    private AddressProtocol mAddressProtocol;

    @Override
    public LoadingPager.LoadedResultEnum initData() {

        mAddressProtocol = new AddressProtocol();
        try {

            mAddressBean = mAddressProtocol.loadData(0);
            return checkResData(mAddressBean);

        } catch (IOException e) {
            e.printStackTrace();
            return LoadingPager.LoadedResultEnum.ERROR;
        }

    }

    @Override
    public View initSuccessView() {
        EventBus.getDefault().register(this);
        //        静态加载
        View inflate = View.inflate(UIUtils.getContext(), R.layout.activity_address2, null);
        mLv = (ListView) inflate.findViewById(R.id.act_address2_lv);

        mAddressAdapter = new AddressAdapter(mAddressBean.addressList, mLv);
        mLv.setAdapter(mAddressAdapter);
        mLv.setCacheColorHint(Color.WHITE);
        mLv.setOnItemClickListener(new MyOnItemClickListener());
        mLv.setOnItemLongClickListener(new MyOnItemLongListener());
        return inflate;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddressBean bean) {
        mLv.setAdapter(new AddressAdapter(bean.addressList, mLv));
        mAddressAdapter.notifyDataSetChanged();
    }


    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            AddressListBean addressListBean = mAddressBean.addressList.get(position);
            EventBus.getDefault().post(addressListBean);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class MyOnItemLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            final AddressListBean addressListBean = mAddressBean.addressList.get(position);
            final int beanId = addressListBean.id;


            new AlertDialog.Builder(getActivity())
                    .setTitle("收货地址")
                    .setMessage("是否要删除地址")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAddressBean.addressList.remove(position);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Request request;
                                    FormBody.Builder search = new FormBody.Builder();
                                    search.add("id", "" + addressListBean.id);

                                    FormBody body = search.build();

                                    request = new Request.Builder()
                                            .post(body)
                                            .url(Constants.URLS.BASEURL + "addressdelete")
                                            .addHeader("userid", "" + beanId)
                                            .build();
                                    OkHttpClient okHttpClient = new OkHttpClient();
                                    try {
                                        Response execute = okHttpClient.newCall(request).execute();
                                        if (execute.isSuccessful()) {
                                            Log.e("TAG", "==========" + position);
                                            UIUtils.getHandler().post(new Runnable() {
                                                @Override
                                                public void run() {

                                                    mAddressAdapter.notifyDataSetChanged();
                                                }
                                            });
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();

                        }
                    }).show();

            return true;
        }
    }

}
