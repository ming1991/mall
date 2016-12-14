package com.itheima31.jdmall.activity;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.bean.AddressBean;
import com.itheima31.jdmall.bean.AddressListBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.HttpUtils;
import com.itheima31.jdmall.utils.StringUtils;
import com.itheima31.jdmall.widgets.addressselector.BottomDialog;
import com.itheima31.jdmall.widgets.addressselector.OnAddressSelectedListener;
import com.itheima31.jdmall.widgets.addressselector.model.City;
import com.itheima31.jdmall.widgets.addressselector.model.County;
import com.itheima31.jdmall.widgets.addressselector.model.Province;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.james.biuedittext.BiuEditText;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author：wizong
 * when：created by 2016/10/25:23:10
 * explain：地址添加页面
 */
public class AddressAddActivity extends AppCompatActivity implements OnAddressSelectedListener {

    @InjectView(R.id.act_address_add_et_receiver)
    BiuEditText mActAddressAddEtReceiver;
    @InjectView(R.id.act_address_add_et_phone)
    BiuEditText mActAddressAddEtPhone;

    @InjectView(R.id.act_address_add_choose_contact)
    TextView mActAddressAddChooseContact;

    @InjectView(R.id.act_address_add_area)
    LinearLayout mActAddressAddArea;

    @InjectView(R.id.act_address_add_et_code)
    BiuEditText mActAddressAddEtCode;
    @InjectView(R.id.act_address_add_et_detail)
    BiuEditText mActAddressAddEtDetail;

    @InjectView(R.id.act_address_add_iv_check)
    ImageView mActAddressAddIvCheck;

    @InjectView(R.id.act_address_add_btn_save)
    Button mActAddressAddBtnSave;

    BottomDialog dialog;
    @InjectView(R.id.act_address_add_location)
    TextView mActAddressAddLocation;

    private String mProvince;
    private String mCity;
    private String mCounty;
    private boolean mIsDefault = false;
    private String mRecContent;
    private String mPhoneContent;
    private String mCodeContent;
    private String mDetailContent;

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
        ButterKnife.inject(this);
        //        assert buttonBottomDialog != null;

    }


    private void initData() {
        mRecContent = mActAddressAddEtReceiver.getText().toString().trim();
        mPhoneContent = mActAddressAddEtPhone.getText().toString().trim();
        mCodeContent = mActAddressAddEtCode.getText().toString().trim();
        mDetailContent = mActAddressAddEtDetail.getText().toString().trim();

    }

    @OnClick({R.id.act_address_add_area, R.id.act_address_add_iv_check, R.id.act_address_add_btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.act_address_add_area:

                if (dialog == null) {
                    dialog = new BottomDialog(AddressAddActivity.this);
                    dialog.setOnAddressSelectedListener(AddressAddActivity.this);
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            if (dialog != null) {
                                if (!(TextUtils.isEmpty(mProvince) || TextUtils.isEmpty(mCity) || TextUtils.isEmpty(mCounty))) {

                                    mActAddressAddLocation.setText("所在地区：" + mProvince + mCity + mCounty);
                                } else {
                                    mProvince = "";
                                    mCity = "";
                                    mCounty = "";
                                }
                            }
                        }
                    });
                }
                dialog.show();
                break;
            case R.id.act_address_add_iv_check:
                if (mIsDefault) {
                    mActAddressAddIvCheck.setImageResource(R.drawable.nocheck);
                } else {
                    mActAddressAddIvCheck.setImageResource(R.drawable.ischeck);
                }
                mIsDefault = !mIsDefault;
                break;
            case R.id.act_address_add_btn_save:
                initData();
                boolean isSuccess = checkInputDataIsSuccess();
                if (isSuccess) {
                    Toast.makeText(AddressAddActivity.this, "你已填写成功：" + mProvince + mCity + mCounty, Toast.LENGTH_LONG).show();
                    postAddressInfo();
                }
                break;
        }
    }

    private void postAddressInfo() {
        final String isDefault = mIsDefault ? "1" : "0";
        final Request[] request = new Request[2];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (mIsDefault) {
                        String url = Constants.URLS.BASEURL + "addresslist";
                        Map<String, Object> paramsMap = new HashMap<>();
                        String urlParamsByMap = HttpUtils.getUrlParamsByMap(paramsMap);

                        url = url + "?" + urlParamsByMap;
                        Request.Builder getRequest = new Request.Builder().get().url(url);
                        request[0] = getRequest.addHeader("userid", "20428").build();
                        Response executeList = okHttpClient.newCall(request[0]).execute();
                        if (executeList.isSuccessful()) {
                            String tempData = executeList.body().string();
                            AddressBean addressBean = new Gson().fromJson(tempData, AddressBean.class);
                            for (int i = 0; i < addressBean.addressList.size(); i++) {
                                AddressListBean addressListBean = addressBean.addressList.get(i);
                                int isDefaultTemp = addressListBean.isDefault;
                                if (isDefaultTemp == 1) {
                                    sendPostRequest(addressListBean);
                                    //                                    SystemClock.sleep(100);
                                }
                            }
                        }
                    }

                    FormBody.Builder search = new FormBody.Builder();
                    search.add("name", mRecContent)
                            .add("id", "")
                            .add("phoneNumber", mPhoneContent)
                            .add("province", mProvince)
                            .add("city", mCity)
                            .add("addressArea", mCounty)
                            .add("addressDetail", mDetailContent)
                            .add("zipCode", mCodeContent)
                            .add("isDefault", isDefault);
                    FormBody body = search.build();

                    request[1] = new Request.Builder().post(body).url(Constants.URLS.BASEURL + "addresssave").addHeader("userid", "20428").build();

                    Response response;
                    response = okHttpClient.newCall(request[1]).execute();

                    if (response.isSuccessful()) {//服务器有响应
                        String jsonString = response.body().string();
                        Gson gson = new Gson();
                        AddressBean addressBean = gson.fromJson(jsonString, AddressBean.class);

                        EventBus.getDefault().post(addressBean);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AddressAddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }

        }).start();
        finish();

    }

    private boolean sendPostRequest(AddressListBean addressListBean) {
        Request request;
        FormBody.Builder search = new FormBody.Builder();
        search.add("name", addressListBean.name)
                .add("id", "" + addressListBean.id)
                .add("phoneNumber", addressListBean.phoneNumber)
                .add("province", addressListBean.province)
                .add("city", addressListBean.city)
                .add("addressArea", addressListBean.addressArea)
                .add("addressDetail", addressListBean.addressDetail)
                .add("zipCode", addressListBean.zipCode)
                .add("isDefault", "0");
        FormBody body = search.build();

        request = new Request.Builder().post(body).url(Constants.URLS.BASEURL + "addresssave").addHeader("userid", "20428").build();
        try {
            Response execute = okHttpClient.newCall(request).execute();
            return execute.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean checkInputDataIsSuccess() {
        if (checkString(mRecContent)) {
            Toast.makeText(AddressAddActivity.this, "收货人不能为空", Toast.LENGTH_SHORT).show();

            Snackbar.make(null, "收货人不能为空", Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!isMatch(REGEX_USERNAME, mRecContent)) {
            Toast.makeText(AddressAddActivity.this, "请正确填写收货人", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkString(mPhoneContent)) {
            Toast.makeText(AddressAddActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (!isMatch(REGEX_MOBILE_EXACT, mPhoneContent)) {
            Toast.makeText(AddressAddActivity.this, "请正确填写手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkString(mDetailContent)) {
            Toast.makeText(AddressAddActivity.this, "住哪里填一下吧", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkString(mCounty)) {
            Toast.makeText(AddressAddActivity.this, "所在地区必填", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (checkString(mCodeContent)) {
            mCodeContent = "000000";
        }
        return true;
    }

    private boolean checkString(String content) {

        return TextUtils.isEmpty(content);
    }

    @Override
    public void onAddressSelected(Province province, City city, County county) {
        //        mProvince = TextUtils.isEmpty(province.name) ? "" : province.name;
        //        mCity = TextUtils.isEmpty(city.name) ? "" : city.name;
        //        mCounty = TextUtils.isEmpty(county.name) ? "" : county.name;
        mProvince = province.name;
        mCity = city.name;
        mCounty = county.name;

        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    @OnClick(R.id.act_address_add_choose_contact)
    public void onClick() {
        Uri uri = Uri.parse("content://contacts/people");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(intent, 0);
    }

    /*
    * 跳转联系人列表的回调函数
    * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data == null) {
                    return;
                }
                //处理返回的data,获取选择的联系人信息
                Uri uri = data.getData();
                String[] contacts = getPhoneContacts(uri);
                if (contacts != null) {
                    String phoneNum = checkPhoneNum(contacts[1]);
                    mActAddressAddEtReceiver.setText(contacts[0]);
                    mActAddressAddEtPhone.setText(phoneNum);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String checkPhoneNum(String contact) {
        String phoneNum = "";
        char[] chars = contact.toCharArray();
        for (char aChar : chars) {
            if (aChar != '-' && aChar != ' ') {
                phoneNum += "" + aChar;
            }
        }
        return phoneNum;
    }

    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            //取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);
            //取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phone.close();
            }
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }

    public static final String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    public static final String REGEX_USERNAME      = "^[\\w\\u4e00-\\u9fa5]{2,16}(?<!_)$";
    public static boolean isMatch(String regex, String string) {
        return !StringUtils.isEmpty(string) && Pattern.matches(regex, string);
    }
}
