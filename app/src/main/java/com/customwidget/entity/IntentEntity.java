package com.customwidget.entity;

import android.content.Intent;

/**
 * Created by yongfeng on 2017/1/3.
 * Email:2499522170@qq.com
 */

public class IntentEntity {
    private String mTitle;
    private Intent mIntent;

    public IntentEntity() {
    }

    public IntentEntity(String title,Intent intent) {
        mTitle = title;
        mIntent = intent;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        mIntent = intent;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
