package com.customwidget.adapter;

import android.view.View;
import android.widget.BaseAdapter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by yongfengnice on 8/7/2017.
 * 通用的自定义Adapter
 */

public abstract class CustomAdapter<T> extends BaseAdapter {

    protected List<T> mItemList;

    public CustomAdapter(List<T> itemList) {
        mItemList = itemList;
    }

    @Override
    public int getCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public T getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public <T> T getViewHolder(View view, Class<T> clazz) {
        T holder = (T) view.getTag();
        if (holder == null) {//反射方式实例化ViewHolder对象
            try {
                Constructor<?>[] constructors = clazz.getDeclaredConstructors();
                Constructor<?> constructor1 = constructors[0];
                constructor1.setAccessible(true);
                holder = (T) constructor1.newInstance(this, view);
                view.setTag(holder);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException("InvocationTargetException：ViewHolder必须是内部类，并且存在构造函数是ViewHolder(View view)");
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new RuntimeException("InstantiationException：ViewHolder必须是内部类，并且存在构造函数是ViewHolder(View view)");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("IllegalAccessException：ViewHolder必须是内部类，并且存在构造函数是ViewHolder(View view)");
            }

        }
        return holder;
    }

    protected class BaseViewHolder {
        private View mView;

        public BaseViewHolder(View view) {
            mView = view;
        }

        public View findViewById(int resId) {
            return mView.findViewById(resId);
        }
    }
}
