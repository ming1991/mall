package com.itheima31.jdmall.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/10/27 19:23
 * 描述：    TODO
 */

public class CustomMarqueeTextView extends TextView {
    public CustomMarqueeTextView(Context context) {
        this(context,null);
    }

    public CustomMarqueeTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomMarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setMarqueeRepeatLimit(-1);
    }
    //是否右焦点
    @Override
    public boolean isFocused() {
        return true;
    }
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(true, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(true);
    }

}
