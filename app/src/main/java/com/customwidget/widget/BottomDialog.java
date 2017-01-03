package com.customwidget.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.customwidget.listener.SimpleAnimatorListener;

/**
 * Created by yongfeng on 2016/12/29.
 * Email:2499522170@qq.com
 * 从底部弹出选择框
 * 注意点：mContentView的高度必须是具体的值
 */
public class BottomDialog extends FrameLayout implements View.OnClickListener {
    private static final String TAG = "SlideDialog";
    private View mContentView;
    private LayoutParams mContentParams;
    private Activity mActivity;
    private ViewGroup mWindowView;
    private boolean mShowing;
    private ValueAnimator mValueAnimator;
    private int mDialogHeight;

    public BottomDialog(Activity activity) {
        this(activity, null);
    }

    public BottomDialog(Activity activity, AttributeSet attrs) {
        this(activity, attrs, 0);
    }

    public BottomDialog(Activity activity, AttributeSet attrs, int defStyleAttr) {
        super(activity, attrs, defStyleAttr);
        mActivity = activity;
        init();
    }

    private void init() {
        setVisibility(GONE);
        mWindowView = (ViewGroup) mActivity.findViewById(android.R.id.content);
        mValueAnimator = ValueAnimator.ofInt(0, 0);
        setOnClickListener(this);
    }

    public void setContentView(View contentView) {
        mContentView = contentView;
        addInnerView(mContentView);
    }

    public void setContentView(@LayoutRes int layoutId) {
        mContentView = LayoutInflater.from(mActivity).inflate(layoutId, this, false);
        addInnerView(mContentView);
    }

    private void addInnerView(View contentView) {
        mWindowView.removeView(this);
        mWindowView.addView(this);
        removeAllViews();
        addView(contentView);
        contentView.setOnClickListener(this);
        mContentParams = (LayoutParams) contentView.getLayoutParams();
        mContentParams.gravity = Gravity.BOTTOM;
        mDialogHeight = mContentParams.height;
    }

    public void show() {
        if (mShowing || mValueAnimator.isRunning()) {
            return;
        }
        setVisibility(VISIBLE);
        mShowing = true;
        startAnimator(0, mDialogHeight);
    }

    public void dismiss() {
        if (!mShowing || mValueAnimator.isRunning()) {
            return;
        }
        mShowing = false;
        startAnimator(mDialogHeight, 0);
    }

    public boolean isShowing() {
        return mShowing;
    }

    private void startAnimator(int start, int end) {
        mValueAnimator.setIntValues(start, end);
        mValueAnimator.addUpdateListener(mAnimatorListener);
        mValueAnimator.addListener(mSimpleAnimatorListener);
        mValueAnimator.start();
    }

    private ValueAnimator.AnimatorUpdateListener mAnimatorListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Object animatedValue = animation.getAnimatedValue();
            if (animatedValue instanceof Integer) {
                mContentParams.height = (int) animatedValue;
                mContentView.setLayoutParams(mContentParams);
            }
        }
    };

    private SimpleAnimatorListener mSimpleAnimatorListener = new SimpleAnimatorListener() {
        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            if (!mShowing) {
                setVisibility(GONE);
            }
        }
    };

    @Override
    public void onClick(View v) {
        if (v != mContentView) {
            dismiss();
        }
    }

//    public void show(int dialogHeight) {
//        if (mShowing || mValueAnimator.isRunning()) {
//            return;
//        }
//        mDialogHeight = dialogHeight;
//        setVisibility(VISIBLE);
//        mShowing = true;
//        startAnimator(0, mDialogHeight);
//    }

//    public void show(View anchorView, int xOff, int yOff) {
//        if (mShowing || mValueAnimator.isRunning() || anchorView == null) {
//            return;
//        }
//        int[] rect = (int[]) anchorView.getTag();
//        if (rect==null){
//            rect = new int[2];
//            anchorView.getLocationOnScreen(rect);
//        }
//        mContentParams.leftMargin = xOff < 0 ? 0 : rect[0] + xOff;
//        mContentParams.topMargin = rect[1] + anchorView.getHeight() - getStatusBarHeight() + yOff;
//        mContentParams.gravity = Gravity.NO_GRAVITY;
//        setVisibility(VISIBLE);
//        mShowing = true;
//        startAnimator(0, mDialogHeight);
//    }

//    private int mStatusBarHeight;
//    private int getStatusBarHeight() {
//        if (mStatusBarHeight<=0){
//            Rect rect = new Rect();
//            getWindowVisibleDisplayFrame(rect);
//            mStatusBarHeight = rect.top;
//        }
//        return mStatusBarHeight;
//    }
}
