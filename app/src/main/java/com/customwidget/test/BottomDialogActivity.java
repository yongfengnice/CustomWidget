package com.customwidget.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.customwidget.R;
import com.customwidget.widget.BottomDialog;

public class BottomDialogActivity extends Activity {
    private BottomDialog mBottomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_dialog);
        initView();
    }

    private void initView() {
        mBottomDialog = new BottomDialog(this);
        mBottomDialog.setContentView(R.layout.view_test_bottom_dialog);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomDialog.isShowing()) {
                    mBottomDialog.dismiss();
                } else {
                    mBottomDialog.show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBottomDialog != null && mBottomDialog.isShowing()) {
            mBottomDialog.dismiss();
        } else {
            super.onBackPressed();
        }
    }
}
