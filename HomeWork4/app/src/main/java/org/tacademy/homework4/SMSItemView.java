package org.tacademy.homework4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by T on 2015-07-15.
 */
public class SMSItemView extends LinearLayout {
    TextView mobileTextView;
    TextView dateTextView;
    TextView contentsTextView;
    ImageView imageView;

    public SMSItemView(Context context) {
        super(context);

        init(context);
    }

    public SMSItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sms_item, this, true);

        mobileTextView    = (TextView)findViewById(R.id.mobilTextView);
        dateTextView      = (TextView)findViewById(R.id.dateTextView);
        contentsTextView  = (TextView)findViewById((R.id.contentsTextView));
        imageView         = (ImageView)findViewById(R.id.imageView);
    }

    public void setMobileTextView(String mobile) {
        mobileTextView.setText(mobile);
    }

    public void setDateTextView(String recievedDate) {
        dateTextView.setText(recievedDate);
    }

    public void setContentsTextView(String contents) {
        contentsTextView.setText(contents);
    }

    public void setImageView(int imageId) {
        imageView.setImageResource(imageId);
    }

}
