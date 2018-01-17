package org.tacademy.homework1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by T on 2015-07-14.
 */
public class CustomerItemView extends RelativeLayout {
    TextView nameTextView;
    TextView ageTextView;
    TextView telTextView;
    TextView addrTextView;

    public CustomerItemView(Context context) {
        super(context);

        init(context);
    }

    public CustomerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.customer_item, this, true);

        nameTextView = (TextView)findViewById(R.id.nameTextView);
        ageTextView = (TextView)findViewById(R.id.ageTextView);
        telTextView = (TextView)findViewById(R.id.telTextView);
        addrTextView = (TextView)findViewById(R.id.addrTextView);
    }

    public void setNameText(String name) {
        nameTextView.setText(name);
    }

    public void setAgeText(String age) {
        ageTextView.setText(age);
    }

    public void setTelText(String tel) {
        telTextView.setText(tel);
    }

    public void setAddrText(String addr) {
        addrTextView.setText(addr);
    }
}
