package com.itheima31.jdmall.view;

import android.view.animation.LinearInterpolator;

/**
 * Created by yangg on 2016/10/27.
 *
 * @desc
 */
public class JellyInterpolator extends LinearInterpolator {
    private float factor;

    public JellyInterpolator() {
        this.factor = 0.15f;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2, -10 * input)
                * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
    }
}

