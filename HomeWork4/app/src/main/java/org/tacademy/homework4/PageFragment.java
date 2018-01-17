package org.tacademy.homework4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by T on 2015-07-16.
 */
public class PageFragment extends Fragment {

    SQLiteDatabase database;
    SMSAdapter smsAdapter;
    ListView listView;

    int imageId = R.drawable.sms_icon;
    int index;

    String mobile;
    String receivedDate;
    String contents;

    public static PageFragment newInstance(int index, Intent intent) {
        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        args.putString("mobile", intent.getStringExtra("mobile"));
        args.putString("receivedDate", intent.getStringExtra("receivedDate"));
        args.putString("contents", intent.getStringExtra("contents"));

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        index = args.getInt("index", 0);
        mobile = args.getString("mobile", "");
        receivedDate = args.getString("receivedDate", "");
        contents = args.getString("contents", "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView;

        if(index == 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sms, container, false);

            TextView mobileView = (TextView)rootView.findViewById(R.id.mobileView);
            TextView dateView = (TextView)rootView.findViewById(R.id.dateView);
            TextView contentsView = (TextView)rootView.findViewById(R.id.contentsView);

            mobileView.setText("보낸사람 : " + mobile);
            dateView.setText("수신일시 : " + receivedDate);
            contentsView.setText("내용 : " + contents);
        } else {
            rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sms_list, container, false);

            smsAdapter = new SMSAdapter(rootView.getContext());

            selectDB();

            listView = (ListView)rootView.findViewById(R.id.listView2);
            listView.setAdapter(smsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Log.e("SMSListActivity", "=============== : " + position);
                    SMSItem item = (SMSItem)smsAdapter.getItem(position);

                    Intent itemIntent = new Intent(getActivity(), MainActivity.class);
                    itemIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    itemIntent.putExtra("mobile", item.getMobile());
                    itemIntent.putExtra("receivedDate", item.getReceivedDate());
                    itemIntent.putExtra("contents", item.getContents());

                    startActivity(itemIntent);
                }
            });

        }

        return rootView;
    }

    public void selectDB() {
        database = MainActivity.database;

        String sql = "SELECT mobile, receivedDate, contents FROM smslist";
        Cursor cursor = database.rawQuery(sql, null);
        int count = cursor.getCount();

        for(int i=0; i<count; i++) {
            cursor.moveToNext();
            String rowMonbile = cursor.getString(0);
            String rowReceivedDate = cursor.getString(1);
            String rowContents = cursor.getString(2);

            smsAdapter.addItem(new SMSItem(rowMonbile, rowReceivedDate, rowContents, imageId));
        }

        smsAdapter.notifyDataSetChanged();
    }
}
