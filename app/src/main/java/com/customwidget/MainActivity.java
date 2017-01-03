package com.customwidget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.customwidget.test.BottomDialogActivity;
import com.customwidget.test.ProgressBarActivity;
import com.customwidget.test.StrokeTextActivity;
import com.customwidget.test.WordWrapActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView mListView;
    private List<Intent> mIntentList;

    private void initView() {
        mListView = (ListView) findViewById(R.id.list_view);
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
        mIntentList.add(new Intent(this, BottomDialogActivity.class));
        mIntentList.add(new Intent(this, WordWrapActivity.class));
        mIntentList.add(new Intent(this, StrokeTextActivity.class));
        mIntentList.add(new Intent(this, ProgressBarActivity.class));
    }

    private void initAdapter() {
        mListView.setAdapter(new ArrayAdapter<Intent>(this, R.layout.item_main_list,
                R.id.text_view, mIntentList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setText(mIntentList.get(position).getComponent().getClassName());
                return textView;
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(mIntentList.get(position));
            }
        });
    }
}
