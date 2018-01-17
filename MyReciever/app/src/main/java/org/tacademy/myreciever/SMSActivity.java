package org.tacademy.myreciever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SMSActivity extends AppCompatActivity {

    public static final int REQ_CODE_SMS = 2001;

    TextView textView;
    TextView textView2;
    TextView textView3;

    String contents = "";
    String mobile = "";
    String recievedDate = "";
    int imageId = R.drawable.sms_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if(intent != null) {
            contents = intent.getStringExtra("contents");
            mobile = intent.getStringExtra("mobile");
            recievedDate = intent.getStringExtra("recievedDate");

            textView2.setText("보낸사람 : " + mobile);
            textView3.setText("수신일시 : " + recievedDate);
            textView.setText("내용 : " + contents);
        }
    }

    public void onCloseButtonClicked(View v) {
        finish();
    }

    public void onSaveButtonClicked(View v) {
        Intent intent = new Intent(this, SMSListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("contents", contents);
        intent.putExtra("mobile", mobile);
        intent.putExtra("recievedDate", recievedDate);
        intent.putExtra("imageId", imageId);

        startActivity(intent);  // request code
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sm, menu);
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
