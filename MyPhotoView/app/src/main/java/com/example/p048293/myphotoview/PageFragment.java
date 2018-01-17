package com.example.p048293.myphotoview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by P048293 on 2015-07-27.
 */
public class PageFragment extends Fragment {

    ViewGroup rootView;

    int index;
    int resId;
    Bitmap bm;

    public static PageFragment newInstance(int index, Intent intent) {
        PageFragment frag = new PageFragment();

        int resId = intent.getIntExtra("resId", 0);
        Bitmap bm = intent.getParcelableExtra("bm");

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putInt("resId", resId);
//        args.putParcelable("bm", bm);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle args = getArguments();
        index = args.getInt("index");
        resId = args.getInt("resId");
//        bm = args.getParcelable("bm");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("onCreateView", index + "==================== resId : " + resId);

        if(index == 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.photo_list, container, false);
            GridView gridView = (GridView)rootView.findViewById(R.id.gridView);
            GridAdapter adapter = new GridAdapter();

            try {
                String[] proj = {MediaStore.MediaColumns.DATA};
                Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;    // content://media/external/images/media
/*
                File direct = new File(Environment.getExternalStorageDirectory() + "/download");    // file:///storage/sdcard/download/
                if (!direct.exists()) {
                    direct.mkdir();
                } // end of if
                Uri imageUri = Uri.fromFile(direct);
*/

                Log.e("onCreateView", index + "==================== imageUri : " + imageUri);
                Cursor cursor = getActivity().getContentResolver().query(imageUri, proj, null, null, null);
                cursor.moveToFirst();

                int count = cursor.getCount();
                for (int i = 0; i<count; i++) {
                    cursor.moveToNext();
//                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA);
                    adapter.addItem(column_index);
                }

                for (int i = 0; i < 10; i++) {
                    adapter.addItem(R.drawable.android_platform);
                    adapter.addItem(R.drawable.darth_vader);
                    adapter.addItem(R.drawable.iron_man);
                    adapter.addItem(R.drawable.receptionist);
                    adapter.addItem(R.drawable.sms_icon);
                    adapter.addItem(R.drawable.user1);
                }
            } catch (Exception e) {
                e.getStackTrace();
            }

            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent itemIntent = new Intent(getActivity(), MainActivity.class);
                    itemIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    int resId = Integer.parseInt(parent.getAdapter().getItem(position).toString());
                    Log.e("setOnItemClickListener", "==================== resId : " + resId + ", position : " + position);
                    itemIntent.putExtra("resId", resId);
                    startActivity(itemIntent);
                }
            });

        } else if(index == 1){
            rootView = (ViewGroup) inflater.inflate(R.layout.photo_view, container, false);
            ImageView imageView = (ImageView)rootView.findViewById(R.id.imageView);
            imageView.setImageResource(resId);
            //imageView.setImageResource(R.drawable.iron_man);
            //imageView.setImageBitmap(bm);
        } else {
            rootView = (ViewGroup) inflater.inflate(R.layout.photo_list, container, false);
        }

        return rootView;
    }


    class GridAdapter extends BaseAdapter {
        ArrayList<Integer> items = new ArrayList<Integer>();

        public void addItem(int item){
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView view = new ImageView(getActivity());

            int item = items.get(position);
            view.setImageResource(item);

            return view;
        }
    }


}
