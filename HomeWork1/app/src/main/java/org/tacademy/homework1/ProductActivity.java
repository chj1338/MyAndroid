package org.tacademy.homework1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    public static final int REQ_CODE_MENU = 2001;

    TextView textView;

    String menuNM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        textView = (TextView)findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v) {

        menuNM = textView.getText().toString();

//        Toast.makeText(this, "돌아가기 버튼이 눌렸어요^^ : " + menuNM, Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra("menuNM", menuNM);
        intent.putExtra("success", true);
        setResult(REQ_CODE_MENU, intent);

        finish();
    }
}
