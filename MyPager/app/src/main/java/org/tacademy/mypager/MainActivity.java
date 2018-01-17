package org.tacademy.mypager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager)findViewById(R.id.pager);
        SingerPagerAdapter adapter = new SingerPagerAdapter(getSupportFragmentManager());

        adapter.addItem(PageFragment.newInstance(0, "소녀시대", R.drawable.darth_vader));
        adapter.addItem(PageFragment.newInstance(1, "걸스데이", R.drawable.android_platform));
        adapter.addItem(PageFragment.newInstance(2, "티아라",   R.drawable.receptionist));
        adapter.addItem(PageFragment.newInstance(3, "애프터스쿨", R.drawable.user1));
        adapter.addItem(PageFragment.newInstance(4, "아이유", R.drawable.iron_man));

        pager.setAdapter(adapter);
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

    public void tabButton5Clicked(View v) {
        pager.setCurrentItem(4);
    }

    class SingerPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public SingerPagerAdapter(FragmentManager fm) {
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
