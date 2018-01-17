package org.tacademy.mylayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class ProductActivity extends AppCompatActivity {

    View layout1;
    View layout2;
    View layout3;

    FrameLayout contentsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);

        contentsLayout = (FrameLayout) findViewById(R.id.contentsLayout);
    }

    public void onButton1Clicked(View v) {
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);

        Tab1 tab1 = new Tab1(this);
        contentsLayout.addView(tab1);

        Button button = new Button(getApplicationContext());
        contentsLayout.addView(button);

    }

    public void onButton2Clicked(View v) {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        layout3.setVisibility(View.GONE);
    }

    public void onButton3Clicked(View v) {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.VISIBLE);
    }

}
