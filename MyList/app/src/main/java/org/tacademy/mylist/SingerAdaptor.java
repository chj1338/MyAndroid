package org.tacademy.mylist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by T on 2015-07-15.
 */
public class SingerAdaptor extends BaseAdapter {
    ArrayList<SingerItem> items = new ArrayList<SingerItem>();

    Context context;

    public SingerAdaptor(Context context) {
        this.context = context;
    }

    public void addItem(SingerItem item) {
        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SingerItemView view = null;

        // convertView : 뷰어의 아이템 재사용(메모리 누수 방지를 위해)
        if(convertView == null) {
            view = new SingerItemView(context);
        } else {
            view = (SingerItemView) convertView;
        }

        SingerItem item = items.get(position);
        view.setNameText(item.getName());
        view.setAgeText(String.valueOf(item.getAge()));
        view.setMobileTextView(item.getMobile());
        view.setImageView(item.getImageId());

        return view;
    }
}
