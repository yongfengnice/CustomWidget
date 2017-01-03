package com.customwidget.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.customwidget.R;
import com.customwidget.widget.WordWrapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordWrapActivity extends Activity {
    private Button mBtn;
    private WordWrapView mWordWrap;
    private List<String> mStringList = new ArrayList<>();

    private void initView() {
        mWordWrap = (WordWrapView) findViewById(R.id.word_wrap);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_word);
        initView();
    }

    private void initData() {
        mStringList.add("就gas电话关机啊是感觉啊");
        mStringList.add("gklasng");
        mStringList.add("麦咖喱考试大纲");
        mStringList.add("扣篮大赛克雷格看电视");
        mStringList.add("飞碟决赛拉开附近的萨科");
        mStringList.add("范德萨");
        mStringList.add("鬼斧神工");
        mStringList.add("八分饱");
        mStringList.add("百发百中表现");
        mStringList.add("特委托");
        mStringList.add("你现在你现在");
        mStringList.add("加快立法打手机");
        mStringList.add("洛克菲勒的萨");
        mStringList.add("那么的萨内");
        mStringList.add("克里夫的萨卡里");
        mStringList.add("企鹅我让我去");
        mStringList.add("搞的撒");
        mStringList.add("不想再那么");
        mStringList.add("克里夫的萨科泛利大厦克里夫");
        mStringList.add("开发了的萨科菲拉斯的");
        mStringList.add("就gas电话关机啊是感觉啊");
        mStringList.add("gklasng");
        mStringList.add("麦咖喱考试大纲");
        mStringList.add("扣篮大赛克雷格看电视");
        mStringList.add("飞碟决赛拉开附近的萨科");
        mStringList.add("范德萨");
        mStringList.add("鬼斧神工");
        mStringList.add("八分饱");
        mStringList.add("百发百中表现");
        mStringList.add("特委托");
        mStringList.add("你现在你现在");
        mStringList.add("加快立法打手机");
        mStringList.add("洛克菲勒的萨");
        mStringList.add("那么的萨内");
        mStringList.add("克里夫的萨卡里");
        mStringList.add("企鹅我让我去");
        mStringList.add("搞的撒");
        mStringList.add("不想再那么");
        mStringList.add("克里夫的萨科泛利大厦克里夫");
        mStringList.add("开发了的萨科菲拉斯的");

        mWordWrap.removeAllViews();
        TextView textView;
        Random random = new Random();
        for (int i = 0; i < mStringList.size(); i++) {
            textView = new TextView(this);
            textView.setBackgroundColor(Color.argb(255,random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            textView.setText(mStringList.get(i));
            mWordWrap.addView(textView);
        }
    }
}
