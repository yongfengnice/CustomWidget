package com.customwidget.utils;

import android.content.Context;

/**
 * Created by yongfeng on 2017/1/3.
 * Email:2499522170@qq.com
 */

public class ScreenUtils {
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
