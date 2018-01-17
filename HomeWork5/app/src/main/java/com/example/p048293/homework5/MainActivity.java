package com.example.p048293.homework5;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    SMSPagerAdapter adapter;

    static SQLiteDatabase database;

    int index = 0;
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


    @Override
    protected void onNewIntent(Intent intent) {
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

        adapter.addItem(PageFragment.newInstance(0, intent));   // sms 내용보기
        adapter.addItem(PageFragment.newInstance(1, intent));   // sms 리스트
        adapter.addItem(PageFragment.newInstance(2, intent));   // 로그인
        adapter.addItem(PageFragment.newInstance(3, intent));   // Web 뷰
        pager.setAdapter(adapter);

        pager.setCurrentItem(index);
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

        sql = "DROP TABLE if exists singer";
        database.execSQL(sql);

        initDB();
    }

    public void tabButton1Clicked(View v) {
        pager.setCurrentItem(0);
    }
    public void tabButton2Clicked(View v) {
        pager.setCurrentItem(1);
    }
    public void tabButton3Clicked(View v) {
        pager.setCurrentItem(2);
    }
    public void tabButton4Clicked(View v) {
        pager.setCurrentItem(3);
    }


    public void initDB() {
        // storage open
        database = openOrCreateDatabase("mydatabase.db", Activity.MODE_PRIVATE, null);

        // smslist table
        String sql = "CREATE TABLE if not exists smslist(mobile text, receivedDate text, contents text)";
        database.execSQL(sql);

        // singer table
        sql = "CREATE TABLE if not exists singer(name text, age int, tel text, addr text)";
        database.execSQL(sql);
    }

    public void insertDB() {
        if(mobile == null) { mobile="000-0000-0000";}
        if(receivedDate == null) { receivedDate="9999-01-01";}
        if(contents == null) { contents="내용없음";}

        String sql = "INSERT INTO smslist(mobile, receivedDate, contents) values (?, ?, ?)";
        Object[] params = new Object[3];
        params[0] = mobile;
        params[1] = receivedDate;
        params[2] = contents;

        database.execSQL(sql, params);
    }
}
