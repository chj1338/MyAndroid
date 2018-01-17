package org.tacademy.mylist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by T on 2015-07-14.
 */
public class SingerItemView extends LinearLayout{
    TextView nameTextView;
    TextView ageTextView;
    TextView mobileTextView;
    ImageView imageView;

    public SingerItemView(Context context) {
        super(context);

        init(context);
    }

    public SingerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item, this, true);

        nameTextView    = (TextView)findViewById(R.id.nameTextView);
        ageTextView     = (TextView)findViewById(R.id.ageTextView);
        mobileTextView  = (TextView)findViewById((R.id.mobilTextView));
        imageView       = (ImageView)findViewById(R.id.imageView);
    }

    public void setNameText(String name) {
        nameTextView.setText(name);
    }

    public void setAgeText(String age) {
        ageTextView.setText(age);
    }

    public void setMobileTextView(String mobile) {
        mobileTextView.setText(mobile);
    }

    public void setImageView(int resId) {
        imageView.setImageResource(resId);
    }
}
