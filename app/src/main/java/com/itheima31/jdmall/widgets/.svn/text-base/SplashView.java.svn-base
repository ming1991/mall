package com.itheima31.jdmall.widgets;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.itheima31.jdmall.MainActivity;
import com.itheima31.jdmall.R;
import com.itheima31.jdmall.utils.BoomAnimUtils.ExplosionField;
import com.itheima31.jdmall.utils.ThreadUtils;
import com.itheima31.jdmall.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Tony on 2016/10/30.
 */

public class SplashView extends FrameLayout implements Animator.AnimatorListener {

    @InjectView(R.id.relativelayout_rockets)
    RelativeLayout mRockets;
    @InjectView(R.id.splash_plant)
    ImageView mPlant;

//    private ImageView[] mRockets;

    private int curPosition = 0;
    private ExplosionField mExplosionField;
    private int mHeight;

    public SplashView(Context context) {
        super(context);
        init();
    }

    private void init() {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater
                .from(getContext()).inflate(R.layout.splash_bg, null);
        addView(relativeLayout);
        ButterKnife.inject(this);


        mExplosionField = new ExplosionField(getContext());
        mExplosionField.addListener(this);
        mHeight = -UIUtils.dip2px(480);

//        int count = relativeLayout.getChildCount();
//        mRockets = new ImageView[count];

//        for (int i = 0; i < mRockets.length; i++) {
//            mRockets[i] = (ImageView) relativeLayout.getChildAt(i);
//        }


//        ViewCompat.animate(mRocket1).translationY(-UIUtils.dip2px(480)).setDuration(10000).start();

        postDelayed(new Runnable() {
            @Override
            public void run() {
                mExplosionField.explode(mRockets.getChildAt(curPosition));
//                mRockets[curPosition].setTranslationY();

                ObjectAnimator animator = ObjectAnimator
                        .ofFloat(mRockets.getChildAt(curPosition), "translationY", 0, mHeight);
                animator.setInterpolator(new AnticipateInterpolator(20));
                animator.setDuration(5000);
                animator.start();
                animator.addListener(SplashView.this);
//
                curPosition++;
                if (curPosition == mRockets.getChildCount()) {
                    curPosition = 0;
                    return;
                }

                postDelayed(this, 200);
            }
        }, 500);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
//        mExplosionField.explode(mRockets[curPosition]);
        mRockets.getChildAt(curPosition).setVisibility(View.INVISIBLE);
        curPosition++;

        if (curPosition == mRockets.getChildCount()) {

            ObjectAnimator animator = ObjectAnimator
                    .ofFloat(mPlant, "translationY", -mHeight, 0);
//            animator.setInterpolator(new AnticipateInterpolator(20));
            animator.setDuration(500);
            animator.start();

            ViewCompat.animate(SplashView.this).scaleX(1.5f).setDuration(2000).start();
            ViewCompat.animate(SplashView.this).scaleY(1.5f).setDuration(2000).start();
            ViewCompat.animate(SplashView.this).alpha(0).setDuration(2000).setInterpolator(new AnticipateInterpolator(10)).start();
            mPlant.setVisibility(View.VISIBLE);


            ThreadUtils.execute(new ThreadUtils.ThreadChangeListener() {
                @Override
                public void onPre() {

                }

                @Override
                public void onExecute() {
                    SystemClock.sleep(1500);
                }

                @Override
                public void onCompelete() {
                    getContext().startActivity(new Intent(getContext(), MainActivity.class));
                    ((Activity)getContext()).finish();
                }
            });

        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
