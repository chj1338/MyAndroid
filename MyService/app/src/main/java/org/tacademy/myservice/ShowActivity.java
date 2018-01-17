package org.tacademy.myservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView textView2;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);

        Intent intent = getIntent();
        processIntent(intent, textView2);
    }

    //onCreate가 처음 실행될 때는 intent를 onCreate가 받지만, 그 이후부터는 onNewIntent 가 받는다.
    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent, textView3);
        super.onNewIntent(intent);
    }


    private void processIntent(Intent intent, TextView textView) {

        TextView tempTextView = textView;

        if(intent != null) {
            String data = intent.getStringExtra("data");
            tempTextView.setText("메시지 : " + data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
