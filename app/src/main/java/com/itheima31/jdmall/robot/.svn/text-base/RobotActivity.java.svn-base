package com.itheima31.jdmall.robot;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.itheima31.jdmall.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RobotActivity extends AppCompatActivity {

    /**
     * 展示消息的listview
     */
    private ListView mChatView;
    /**
     * 文本域
     */
    private EditText mMsg;
    /**
     * 存储聊天消息
     */
    private List<ChatMessage> mDatas = new ArrayList();
    /**
     * 适配器
     */
    private ChatMessageAdapter mAdapter;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            ChatMessage from = (ChatMessage) msg.obj;
            mDatas.add(from);
            mAdapter.notifyDataSetChanged();
            mChatView.setSelection(mDatas.size() - 1);

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //去掉标题
        getSupportActionBar().hide();

        setContentView(R.layout.main_chatting);

        initView();

        mAdapter = new ChatMessageAdapter(this, mDatas);
        mChatView.setAdapter(mAdapter);


    }

    private void initView() {
        mChatView = (ListView) findViewById(R.id.id_chat_listView);
        mMsg = (EditText) findViewById(R.id.id_chat_msg);
        mDatas.add(new ChatMessage(ChatMessage.Type.INPUT, "您好！欢迎光临小店，我是客服小冰"));
    }

    public void sendMessage(View view) {
        final String msg = mMsg.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            Toast.makeText(this, "您还没有填写信息呢...", Toast.LENGTH_SHORT).show();
            return;
        }

        ChatMessage to = new ChatMessage(ChatMessage.Type.OUTPUT, msg);
        to.setDate(new Date());
        mDatas.add(to);

        mAdapter.notifyDataSetChanged();
        mChatView.setSelection(mDatas.size() - 1);

        mMsg.setText("");

        // 关闭软键盘
        InputMethodManager imm =
                (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }

        new Thread() {
            public void run() {
                ChatMessage from = null;
                try {
                    from = HttpUtils.sendMsg(msg);
                } catch (Exception e) {
                    from = new ChatMessage(ChatMessage.Type.INPUT, "不好意思，亲，现在比较忙，请稍等哦");
                }
                Message message = Message.obtain();
                message.obj = from;
                mHandler.sendMessage(message);
            };
        }.start();

    }
}
