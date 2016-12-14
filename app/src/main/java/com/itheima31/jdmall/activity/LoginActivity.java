package com.itheima31.jdmall.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.app.MyApplication;
import com.itheima31.jdmall.bean.MineBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.event.EventLogIn;
import com.itheima31.jdmall.factory.ThreadPoolProxyFactory;
import com.itheima31.jdmall.protocol.Login2Protocol;
import com.itheima31.jdmall.protocol.Register2Protocol;
import com.itheima31.jdmall.utils.SPUtils;
import com.itheima31.jdmall.utils.ToastUtils;
import com.itheima31.jdmall.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends Activity implements OnClickListener {

    @InjectView(R.id.main_btn_login)
    TextView mBtnLogin;
    @InjectView(R.id.input_layout_name)
    LinearLayout mName_layout;
    @InjectView(R.id.input_layout_psw)
    LinearLayout mPsw_layout;
    @InjectView(R.id.login_account)
    EditText mLoginAccount;
    @InjectView(R.id.login_pwd)
    EditText mLoginPwd;
    @InjectView(R.id.title_layout_register)
    TextView mTitleLayoutRegister;

    private View progress;

    private View mInputLayout;

    private float mWidth, mHeight;


    boolean isLogin = true;
    private String mAccount;
    private String mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);
    }

    @OnClick({R.id.title_layout_back, R.id.title_layout_register, R.id.main_btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_layout_back:
                if (isLogin) {
                    finish();
                } else {
                    mLoginAccount.setText("");
                    mLoginPwd.setText("");
                    mBtnLogin.setText("登录");
                    mTitleLayoutRegister.setText("注册");
                }
                isLogin = !isLogin;
                break;
            case R.id.title_layout_register:
                if (isLogin) {
                    mBtnLogin.setText("注册");
                    mTitleLayoutRegister.setText("登录");
                } else {
                    mBtnLogin.setText("登录");
                    mTitleLayoutRegister.setText("注册");
                }
                isLogin = !isLogin;
                break;
            case R.id.main_btn_login:
                mAccount = mLoginAccount.getText().toString().trim();
                mPwd = mLoginPwd.getText().toString().trim();
                if (isLogin) {
                    if (!(TextUtils.isEmpty(mAccount) || TextUtils.isEmpty(mPwd))) {
                        sendLogInRequest();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (!(TextUtils.isEmpty(mAccount) || TextUtils.isEmpty(mPwd))) {
                        sendRegisterRequest();
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private void sendRegisterRequest() {
        ThreadPoolProxyFactory.createNormalPoolProxy().execute(new RegisterTask());
    }

    private class RegisterTask implements Runnable {
        @Override
        public void run() {
            Register2Protocol register2Protocol = new Register2Protocol(mAccount, mPwd);
            try {
                MineBean mineBean = register2Protocol.loadData(0);
                checkedRegister(mineBean);
            } catch (IOException e) {
                e.printStackTrace();
                ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "网络异常", 0);
            }
        }
    }

    private void sendLogInRequest() {
        ThreadPoolProxyFactory.createNormalPoolProxy().execute(new LoginTask());
    }

    private void doAnima() {

        mWidth = mBtnLogin.getMeasuredWidth();
        mHeight = mBtnLogin.getMeasuredHeight();

        mName_layout.setVisibility(View.INVISIBLE);
        mPsw_layout.setVisibility(View.INVISIBLE);

        inputAnimator(mInputLayout, mWidth, mHeight);
    }

    public void doAnimatorInMainThread() {
        MyApplication.getHandler().post(new Runnable() {
            @Override
            public void run() {
                doAnima();
            }
        });
    }

    private void inputAnimator(final View view, float w, float h) {

        AnimatorSet set = new AnimatorSet();

        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                MarginLayoutParams params = (MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(1000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });
    }

    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(1000);
        animator3.setInterpolator(new LoginJellyInterpolator());
        animator3.start();

    }


    private class LoginTask implements Runnable {
        @Override
        public void run() {
            Login2Protocol login2Protocol = new Login2Protocol(mAccount, mPwd);
            try {
                MineBean mineBean = login2Protocol.loadData(0);
                checkedResult(mineBean);
            } catch (IOException e) {
                e.printStackTrace();
                ToastUtils.showToastInCenter(UIUtils.getContext(), 1, "网络异常", 0);
            }
        }

    }

    private void checkedRegister(MineBean mineBean) {
        String response = mineBean.response;
        if (response.equals("register")) {

            String userid = mineBean.userInfo.userid;
            // TODO:  这里的key写死了,后期如果需要优化的化,就用账户名来作为key,进行存储

            ToastUtils.showToastInCenter(UIUtils.getContext(), 2, "注册成功,请登录", 0);

            MyApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    mBtnLogin.setText("登录");
                    mTitleLayoutRegister.setText("注册");
                    mLoginAccount.setText("");
                    mLoginPwd.setText("");
                    isLogin = true;
                }
            });
//            finish();
        } else if (response.equals("error")) {
            MyApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    mLoginPwd.setText("");
                }
            });
            String error = mineBean.error;
            ToastUtils.showToastInCenter(UIUtils.getContext(), 1, error, Toast.LENGTH_SHORT);
        }

    }


    private void checkedResult(MineBean mineBean) {
        String response = mineBean.response;
        if (response.equals("login")) {
            doAnimatorInMainThread();
            String userId = mineBean.userInfo.userid;
            // TODO:   保存数据,方便回写作用
            String value = response + "#" + userId + "#" + mAccount + "#" + mPwd;
            SPUtils.putString(Constants.LOGED_ACCOUNT, value);

            EventLogIn eventLogIn = new EventLogIn();
            eventLogIn.account = mAccount;
            eventLogIn.userid = userId;
            EventBus.getDefault().post(eventLogIn);

        } else if (response.equals("error")) {
            MyApplication.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    mLoginPwd.setText("");
                }
            });
            String error = mineBean.error;

            ToastUtils.showToastInCenter(UIUtils.getContext(), 1, error, Toast.LENGTH_SHORT);
        }
    }
}
