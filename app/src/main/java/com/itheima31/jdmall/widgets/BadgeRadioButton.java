package com.itheima31.jdmall.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.itheima31.jdmall.utils.UIUtils;

/**
 * Created by Tony on 2016/10/24.
 */

public class BadgeRadioButton extends RadioButton {

    private Paint mPaint;

    public BadgeRadioButton(Context context) {
        super(context);
    }

    public BadgeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        String text = "10";
        int start = 0;
        int end = text.length();
        float x = getMeasuredWidth() - UIUtils.dip2px(30);
        float y = getMeasuredHeight() / 2 - UIUtils.dip2px(10);

        if (mPaint == null) {
            mPaint = new Paint();
        }

        //画圆
//        float cx = getMeasuredWidth() + 120;
//        float cy = getMeasuredHeight() -120;
//        float radius = UIUtils.dip2px(10);


        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(UIUtils.dip2px(12));
        mPaint.setStrokeWidth(UIUtils.dip2px(10));
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);




//        mPaint.setColor(Color.RED);

//        canvas.drawCircle(x,y,radius,mPaint);

        // 画字
        mPaint.setColor(Color.RED);


        canvas.drawText(text, start, end, x, y, mPaint);


    }
}
