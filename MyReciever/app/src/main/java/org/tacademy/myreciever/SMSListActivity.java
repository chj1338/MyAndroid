package org.tacademy.myreciever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SMSListActivity extends AppCompatActivity {

    SMSAdapter adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslist);

        adapter = new SMSAdapter(getApplicationContext());
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        processIntent(intent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("SMSListActivity", "=============== : " + position);
                SMSItem item = (SMSItem)adapter.getItem(position);

                Intent itemIntent = new Intent(getApplicationContext(), SMSActivity.class);
                itemIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                itemIntent.putExtra("mobile", item.getMobile());
                itemIntent.putExtra("recievedDate", item.getRecievedDate());
                itemIntent.putExtra("contents", item.getContents());

                startActivity(itemIntent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
        super.onNewIntent(intent);
    }

    public void processIntent(Intent intent) {
        if(intent != null) {
            String monbile = intent.getStringExtra("mobile");
            String recievedDate = intent.getStringExtra("recievedDate");
            String contents = intent.getStringExtra("contents");
            int imageId = R.drawable.sms_icon;

            adapter.addItem(new SMSItem(monbile, recievedDate, contents, imageId));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smslist, menu);
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
