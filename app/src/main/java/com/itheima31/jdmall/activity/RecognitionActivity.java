package com.itheima31.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.bean.RecognitionBean;
import com.itheima31.jdmall.conf.Constants;
import com.itheima31.jdmall.utils.SPUtil;
import com.itheima31.jdmall.utils.StringUtils;
import com.itheima31.jdmall.utils.UIUtils;

import java.util.List;

import static com.itheima31.jdmall.activity.SearchActivity.KEY_TEXT;

public class RecognitionActivity extends AppCompatActivity {
    private Gson   mGson;
    private String mW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognition);
        mGson = new Gson();
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=58104686");
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, null);
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(mRecognizerDialogListener);
        //4.显示dialog，接收语音输入
        mDialog.show();


    }

    /**
     * w：语音输入的返回值，String；
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult result, boolean b) {
//            Toast.makeText(UIUtils.getContext(), result.getResultString(), Toast.LENGTH_SHORT).show();
            if (b) {
                return;
            }
            RecognitionBean resultBean = mGson.fromJson(result.getResultString(), RecognitionBean.class);
            List<RecognitionBean.WsBean> ws = resultBean.getWs();
            mW = "";
            for (int i = 0; i < ws.size(); i++) {
                List<RecognitionBean.WsBean.CwBean> cw = ws.get(i).getCw();
                for (int j = 0; j < cw.size(); j++) {
                    mW += cw.get(j).getW();
                }
            }
            Toast.makeText(UIUtils.getContext(), mW, Toast.LENGTH_SHORT).show();

            goToList(mW);
        }

        @Override
        public void onError(SpeechError error) {

        }
    };

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
}
