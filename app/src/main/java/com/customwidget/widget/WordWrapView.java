package com.customwidget.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.customwidget.utils.ScreenUtils;

import static com.customwidget.utils.ScreenUtils.dip2px;

/**
 * Created by yongfeng on 2016/3/2.
 * Function: 自动换行布局
 */
public class WordWrapView extends ViewGroup {

    private int mHorizonPadding;
    private int mVerticalPadding;
    private int mSideMargin;
    private int mTextMargin;

    public WordWrapView(Context context) {
        this(context, null);
    }

    public WordWrapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WordWrapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mHorizonPadding = ScreenUtils.dip2px(context, 10);//水平方向padding
        mVerticalPadding = ScreenUtils.dip2px(context, 6);//垂直方向padding
        mSideMargin = ScreenUtils.dip2px(context, 10);//最左边和最右边的间距
        mTextMargin = ScreenUtils.dip2px(context, 10);//子view之的距离
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int autualWidth = right - left;
        int x = mSideMargin;// 横坐标开始
        int y;//纵坐标开始
        View view;
        int rows = 1;
        for (int i = 0; i < childCount; i++) {//遍历每一个孩子
            view = getChildAt(i);
            int width = view.getMeasuredWidth();//测量宽度和高度
            int height = view.getMeasuredHeight();
            x += (i == 0) ? width : width + mTextMargin;
            if (x > autualWidth) {//同一行上宽度不够，换行
                x = width + mSideMargin;  //margin
                rows++;
            }
            y = rows * (height + mTextMargin);
            view.layout(x - width, y - height, x, y);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int x = 0;//横坐标
        int y = 0;//纵坐标
        int rows = 1;//总行数
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        int actualWidth = specWidth - mSideMargin * 2;//实际宽度
        int childCount = getChildCount();
        for (int index = 0; index < childCount; index++) {
            View child = getChildAt(index);
            child.setPadding(mHorizonPadding, mVerticalPadding, mHorizonPadding, mVerticalPadding);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            x += width + mTextMargin;
            if (x > actualWidth) {//换行
                x = width;
                rows++;
            }
            y = rows * (height + mTextMargin);
        }
        setMeasuredDimension(actualWidth, y + mTextMargin);
    }

}
