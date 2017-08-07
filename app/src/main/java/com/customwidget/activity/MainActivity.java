package com.customwidget.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.customwidget.R;
import com.customwidget.adapter.MainAdapter;
import com.customwidget.entity.IntentEntity;
import com.customwidget.test.BottomDialogActivity;
import com.customwidget.test.ProgressBarActivity;
import com.customwidget.test.StrokeTextActivity;
import com.customwidget.test.WordWrapActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private GridView mGridView;
    private List<IntentEntity> mIntentList;

    private void initView() {
        mGridView = (GridView) findViewById(R.id.grid_view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
    }

    private void initData() {
        mIntentList = new ArrayList<>();
        mIntentList.add(new IntentEntity("底部弹出框", new Intent(this, BottomDialogActivity.class)));
        mIntentList.add(new IntentEntity("自动换行布局", new Intent(this, WordWrapActivity.class)));
        mIntentList.add(new IntentEntity("文字描边效果", new Intent(this, StrokeTextActivity.class)));
        mIntentList.add(new IntentEntity("文字居中圆角进度条", new Intent(this, ProgressBarActivity.class)));
        mIntentList.add(new IntentEntity("左右滑动选项卡", new Intent(this, SlideTabActivity.class)));
    }

    private void initAdapter() {
        mGridView.setAdapter(new MainAdapter(mIntentList));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(mIntentList.get(position).getIntent());
            }
        });
    }
}
