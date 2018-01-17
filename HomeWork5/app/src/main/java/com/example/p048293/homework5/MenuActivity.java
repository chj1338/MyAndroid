package com.example.p048293.homework5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public static final int REQ_CODE_MENU = 2001;

    TextView textView;
    TextView textView2;

    String id;
    String rtnMenuNm = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);

        Intent intent = getIntent();
        if(intent != null) {
            id = intent.getStringExtra("id");

            textView.setText(id+"님 환영합니다.");
        }
    }

    public void onButton1Clicked(View v) {
        Intent intent = new Intent(this, CustomerActivity.class);
        startActivityForResult(intent, REQ_CODE_MENU);  // request code
    }

    public void onButton2Clicked(View v) {
        Intent intent = new Intent(this, ProductActivity.class);
        startActivityForResult(intent, REQ_CODE_MENU);  // request code
    }

    public void onButton3Clicked(View v) {
        Intent intent = new Intent(this, RevenueActivity.class);
        startActivityForResult(intent, REQ_CODE_MENU);  // request code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_CODE_MENU) {
            boolean success = data.getBooleanExtra("success", false);
            rtnMenuNm = data.getStringExtra("menuNM");
//            Toast.makeText(this, "응답을 받았습니다. : " + rtnMenuNm, Toast.LENGTH_LONG).show();
            textView2.setText("선택메뉴 : " + rtnMenuNm);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
