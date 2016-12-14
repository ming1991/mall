package com.itheima31.jdmall.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.itheima31.jdmall.R;


public class RatioLayout extends FrameLayout {
    public static int MODE_WIDTH = 0;
    public static int MODE_HEIGHT = 1;
    public int mode;
    private float mRatio = 2.42f;

    public RatioLayout(Context context) {
        this(context,null);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setRatio(float mRatio) {
        this.mRatio = mRatio;
    }

    public RatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioLayout);
        mRatio = typedArray.getFloat(R.styleable.RatioLayout_RATIO, 2.42f);
        mode = typedArray.getInt(R.styleable.RatioLayout_MODE,MODE_WIDTH);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1.获取spec值中的宽高信息
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY && mode == MODE_WIDTH) {
            Log.e("measure", "固定宽度，调整高度");
            int totalHeight = (int) (width / mRatio);
            //2.测量当前view的孩子\\
            int childWidth = width - getPaddingLeft() - getPaddingRight(); //让当前view支持padding功能
            int childHeight = totalHeight - getPaddingBottom() - getPaddingTop();
            int childWidthSpec = MeasureSpec.makeMeasureSpec(childWidth,MeasureSpec.EXACTLY);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthSpec, childHeightSpec);

            //3.确定当前view的宽 高
            setMeasuredDimension(width,totalHeight);
        }else if (heightMode == MeasureSpec.EXACTLY && mode == MODE_HEIGHT){
            Log.e("measure", "固定高度，调整宽度");
            int totalWidth = (int) (height * mRatio);
            //2.测量当前view的孩子
            int childWidth = totalWidth - getPaddingLeft() - getPaddingRight(); //让当前view支持padding功能
            int childHeight = height - getPaddingBottom() - getPaddingTop();
            int childWidthSpec = MeasureSpec.makeMeasureSpec(childWidth,MeasureSpec.EXACTLY);
            int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
            measureChildren(childWidthSpec, childHeightSpec);
            //3.确定当前view的宽 高
            setMeasuredDimension(totalWidth,height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.e("measure", "使用原来的宽高");
        }
    }
}

