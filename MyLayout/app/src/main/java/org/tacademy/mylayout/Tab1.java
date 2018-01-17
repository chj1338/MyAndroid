package org.tacademy.mylayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by T on 2015-07-14.
 */

public class Tab1 extends LinearLayout{

    public Tab1(Context context) {
        super(context);

        init(context);
    }

    public Tab1(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tab1, this, true);


    }
}
