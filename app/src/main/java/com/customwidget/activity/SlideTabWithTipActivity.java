package com.customwidget.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.customwidget.R;
import com.customwidget.TabFragment;
import com.customwidget.widget.slidetab.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class SlideTabWithTipActivity extends FragmentActivity {
    private SlidingTabLayout mStTitle;
    private ViewPager mVpContent;
    private List<String> mTitleList;

    private void assignViews() {
        mStTitle = (SlidingTabLayout) findViewById(R.id.st_title);
        mVpContent = (ViewPager) findViewById(R.id.vp_content);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silde_tab);
        assignData();
        assignViews();
        initTabLayout();
    }

    private void initTabLayout() {
        mVpContent.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        //tab是否均分屏幕的宽度，可通过android:layout_weight="1"实现，更加灵活
//        mStTitle.setDistributeEvenly(true);
        mStTitle.setIndicatorWidth(60);
        mStTitle.setCustomTabView(R.layout.slide_tab_title_red_dot, R.id.tv_title);
        mStTitle.setSelectedIndicatorColors(ContextCompat.getColor(getApplicationContext(),
                android.R.color.holo_red_light));
        mStTitle.setViewPager(mVpContent); // 加载ViewPager

        mVpContent.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //mStTitle.getTabStrip().getChildAt(position)对应的tabView
                View view = mStTitle.getTabStrip().getChildAt(position).findViewById(R.id.tv_red_dot);
                if (view.getVisibility() != View.GONE) {
                    view.setVisibility(View.GONE);//隐藏对应tab的数字提示
                }
            }
        });

        int childCount = mStTitle.getTabStrip().getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i != 0) {//显示除了第一个外的数字提示
                mStTitle.getTabStrip().getChildAt(i).findViewById(R.id.tv_red_dot).setVisibility(View.VISIBLE);
            }
        }
    }

    private void assignData() {
        mTitleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 1) {
                mTitleList.add("标题栏");
            } else {
                mTitleList.add("标题栏测试");
            }
        }
    }

    private class TabFragmentAdapter extends FragmentPagerAdapter {

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TabFragment.newInstance(mTitleList.get(position));
        }

        @Override
        public int getCount() {
            return mTitleList.size();
        }

        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
