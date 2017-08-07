package com.customwidget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yongfengnice on 8/7/2017.
 */

public class TabFragment extends Fragment {
    private TextView mTextView;

    private void assignViews(View view) {
        mTextView = (TextView) view.findViewById(R.id.text_view);
    }

    public static TabFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        assignData();
    }

    private void assignData() {
        String title = getArguments().getString("title");
        mTextView.setText(title);
    }
}
