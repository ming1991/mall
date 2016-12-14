package com.itheima31.jdmall.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.utils.UIUtils;

/**
 * Created by Tony on 2016/10/29.
 */

public class FilterDialog extends Dialog implements View.OnClickListener {
    public FilterDialog(Context context) {
        super(context,R.style.ListDialogTheme);
    }

    public FilterDialog(Context context, int themeResId) {
        super(context, themeResId);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.filter_dialog, null);
        Button btnSure = (Button) inflate.findViewById(R.id.btn_sure);
        Button btnReset = (Button) inflate.findViewById(R.id.btn_reset);

        btnSure.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        setContentView(inflate);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width =  WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.gravity = Gravity.RIGHT;

        getWindow().setAttributes(attributes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sure:
                dismiss();
                break;
            case R.id.btn_reset:
                Toast.makeText(UIUtils.getContext(), "已重置", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
        }
    }
}
