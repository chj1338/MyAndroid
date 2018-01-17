package com.example.p048293.myphotoview;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    int resId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        processData(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processData(intent);
    }

    public void processData(Intent intent) {
//        resId = R.drawable.iron_man;
//        Bitmap bm = onSelectFromGalleryResult(intent);
        resId = intent.getIntExtra("resId", 0);

        intent.putExtra("resId", resId);
        //intent.putExtra("bm", bm);

        pager = (ViewPager)findViewById(R.id.pager);
        PhotoPagerAdapter adapter = new PhotoPagerAdapter(getSupportFragmentManager());
        adapter.addItem(PageFragment.newInstance(0, intent));
        adapter.addItem(PageFragment.newInstance(1, intent));
        pager.setAdapter(adapter);

        if(resId == 0) {
            pager.setCurrentItem(0);
        } else {
            pager.setCurrentItem(1);
        }
    }

    class PhotoPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public PhotoPagerAdapter(FragmentManager fm) {
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


    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        //Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        String realPath = cursor.getString(columnIndex);
        cursor.close();

        return realPath;
    }


    private Bitmap onSelectFromGalleryResult(Intent data) {
        Uri selectedImageUri = data.getData();
        String[] projection = { MediaStore.MediaColumns.DATA };
        //Cursor cursor = managedQuery(selectedImageUri, projection, null, null,  null);
        Cursor cursor = getContentResolver().query(selectedImageUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();

        String selectedImagePath = cursor.getString(column_index);

        Bitmap bm;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);

        final int REQUIRED_SIZE = 200;
        int scale = 1;

        while (options.outWidth / scale / 2 >= REQUIRED_SIZE  && options.outHeight / scale / 2 >= REQUIRED_SIZE)
            scale *= 2;

        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(selectedImagePath, options);

        //ImageView ivImage.setImageBitmap(bm);
        return bm;
    }

    public void onListButtonClicked(View v) {
        pager.setCurrentItem(0);
    }

    public void onViewButtonClicked(View v) {
        pager.setCurrentItem(1);
    }

}
