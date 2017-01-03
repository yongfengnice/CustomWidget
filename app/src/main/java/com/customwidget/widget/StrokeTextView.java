package com.customwidget.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.customwidget.utils.ScreenUtils;

/**
 * Created by yongfeng on 2017/1/3.
 * Email:2499522170@qq.com
 * 文字描边效果
 */

public class StrokeTextView extends TextView {

    private TextView borderText = null;///用于描边的TextView

    public StrokeTextView(Context context) {
        this(context, null);
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        borderText = new TextView(context, attrs);
        init(context);
    }

    public void init(Context context) {
        TextPaint textPaint = borderText.getPaint();
        textPaint.setAntiAlias(true);
        textPaint.setStrokeWidth(ScreenUtils.dip2px(context,2));  //设置描边宽度
        textPaint.setStyle(Paint.Style.STROKE); //对文字只描边
        borderText.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light));  //设置描边颜色
        borderText.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        borderText.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        CharSequence tt = borderText.getText();

        //两个TextView上的文字必须一致
        if (tt == null || !tt.equals(this.getText())) {
            borderText.setText(getText());
            this.postInvalidate();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        borderText.measure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        borderText.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        borderText.draw(canvas);
        super.onDraw(canvas);
    }

}
