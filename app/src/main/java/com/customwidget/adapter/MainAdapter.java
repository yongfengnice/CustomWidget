package com.customwidget.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.customwidget.R;
import com.customwidget.entity.IntentEntity;

import java.util.List;

/**
 * Created by yongfengnice on 8/7/2017.
 */

public class MainAdapter extends CustomAdapter<IntentEntity> {

    public MainAdapter(List<IntentEntity> itemList) {
        super(itemList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_main_list,parent,false);
        }

        ViewHolder holder = getViewHolder(convertView, ViewHolder.class);
        holder.mTextView.setText(mItemList.get(position).getTitle());
        return convertView;
    }

    private class ViewHolder extends BaseViewHolder{
        private TextView mTextView;

        private void assignViews() {
            mTextView = (TextView) findViewById(R.id.text_view);
        }

        public ViewHolder(View view) {
            super(view);
            assignViews();
        }
    }
}
