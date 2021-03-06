package com.example.p048293.homework5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RevenueActivity extends AppCompatActivity {

    public static final int REQ_CODE_MENU = 2001;

    TextView textView;

    String menuNM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        textView = (TextView)findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v) {
        //Toast.makeText(this, "돌아가기 버튼이 눌렸어요^^ : " + menuNM, Toast.LENGTH_LONG).show();

        menuNM = textView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("menuNM", menuNM);
        intent.putExtra("success", true);
        setResult(REQ_CODE_MENU, intent);

        finish();
    }
}
