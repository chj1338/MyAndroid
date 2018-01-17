package org.tacademy.myreciever;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by T on 2015-07-15.
 */
public class SMSAdapter extends BaseAdapter {
    //ArrayList<SMSItem> items = new ArrayList<SMSItem>();
    ArrayList<SMSItem> items = MainActivity.items;

    Context context;

    public SMSAdapter(Context context) {
        this.context = context;
    }

    public void addItem(SMSItem item) {
//        items.add(item);
        MainActivity.items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        //return items.get(position);
        return MainActivity.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SMSItemView view = null;

        // convertView : 뷰어의 아이템 재사용(메모리 누수 방지를 위해)
        if(convertView == null) {
            view = new SMSItemView(context);
        } else {
            view = (SMSItemView) convertView;
        }

        SMSItem item = items.get(position);
        view.setMobileTextView(item.getMobile());
        view.setDateTextView(item.getRecievedDate());
        view.setContentsTextView(item.getContents());
        view.setImageView(item.getImageId());

        return view;
    }
}
