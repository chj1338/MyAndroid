package org.tacademy.homework4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    SMSPagerAdapter adapter;

    static SQLiteDatabase database;

    String mobile = "";
    String receivedDate = "";
    String contents = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDB();   // db 초기화 및 테이블 생성
        Intent intent = getIntent();
        processData(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //Toast.makeText(this, "리스트에서 받은 : " + intent.getStringExtra("contents"), Toast.LENGTH_LONG).show();

        processData(intent);
        super.onNewIntent(intent);
    }

    public void processData(Intent intent) {
        // database insert를 위한 데이터
        mobile = intent.getStringExtra("mobile");
        receivedDate = intent.getStringExtra("receivedDate");
        contents = intent.getStringExtra("contents");

        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new SMSPagerAdapter(getSupportFragmentManager());
        adapter.addItem(PageFragment.newInstance(0, intent));
        adapter.addItem(PageFragment.newInstance(1, intent));
        pager.setAdapter(adapter);
    }

    public void onSaveButtonClicked(View v) {
        insertDB();

        Intent intent = getIntent();
        processData(intent);

        pager.setCurrentItem(1);
    }

    public void onCloseButtonClicked(View v) {
        finish();
    }

    public void onDatabaseDropButtonClicked(View v) {
        String sql = "DROP TABLE if exists smslist";
        database.execSQL(sql);

        initDB();
    }

    public void tabButton1Clicked(View v) {
        pager.setCurrentItem(0);
    }

    public void tabButton2Clicked(View v) {
        pager.setCurrentItem(1);
    }


    class SMSPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public SMSPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    public void initDB() {
        database = openOrCreateDatabase("smslist.db", Activity.MODE_PRIVATE, null);

        String sql = "CREATE TABLE if not exists smslist(mobile text, receivedDate text, contents text)";
        database.execSQL(sql);
    }

    public void insertDB() {
        if(contents == null) { contents="내용없음";}

        String sql = "INSERT INTO smslist(mobile, receivedDate, contents) values (?, ?, ?)";
        Object[] params = new Object[3];
        params[0] = mobile;
        params[1] = receivedDate;
        params[2] = contents;

        database.execSQL(sql, params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
