package com.example.p048293.homework5;

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
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
    ViewGroup rootView;

    int imageId = R.drawable.sms_icon;

    int index;
    String mobile;
    String receivedDate;
    String contents;

    String loginId;
    String loginPw;

    public static PageFragment newInstance(int newIndex, Intent intent) {
        PageFragment frag = new PageFragment();

        Bundle args = new Bundle();
        args.putInt("index", newIndex);
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
        Bundle args = getArguments();
        index = args.getInt("index", 0);
        mobile = args.getString("mobile", "");
        receivedDate = args.getString("receivedDate", "");
        contents = args.getString("contents", "");

        super.onCreate(savedInstanceState);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(index == 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sms, container, false);

            TextView mobileView = (TextView)rootView.findViewById(R.id.mobileView);
            TextView dateView = (TextView)rootView.findViewById(R.id.dateView);
            TextView contentsView = (TextView)rootView.findViewById(R.id.contentsView);

            mobileView.setText("보낸사람 : " + mobile);
            dateView.setText("수신일시 : " + receivedDate);
            contentsView.setText("내용 : " + contents);
        } else if(index == 1){
            rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sms_list, container, false);

            smsAdapter = new SMSAdapter(rootView.getContext());

            selectDB();

            listView = (ListView)rootView.findViewById(R.id.listView2);
            listView.setAdapter(smsAdapter);
            listView.setSelection(0);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SMSItem item = (SMSItem) smsAdapter.getItem(position);

                    Intent itemIntent = new Intent(getActivity(), MainActivity.class);
                    itemIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    index =  0;
                    mobile = item.getMobile();
                    receivedDate = item.getReceivedDate();
                    contents = item.getContents();

                    itemIntent.putExtra("index", index);
                    itemIntent.putExtra("mobile", mobile);
                    itemIntent.putExtra("receivedDate", receivedDate);
                    itemIntent.putExtra("contents", contents);

                    startActivity(itemIntent);
                }
            });
        } else  if(index == 2){
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

            Button loginButton = (Button)rootView.findViewById(R.id.loginButton);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText editText = (EditText)rootView.findViewById(R.id.editText);
                    EditText editText2 = (EditText)rootView.findViewById(R.id.editText2);

                    loginId = editText.getText().toString();
                    loginPw = editText2.getText().toString();

                    if(loginId == null || loginId.equals("")) {
                        Toast.makeText(getActivity(), "ID는 반드시 입력하셔야 합니다.", Toast.LENGTH_LONG).show();
                    } else {

                        Intent intent = new Intent(getActivity(), MenuActivity.class);

                        intent.putExtra("id", loginId);
                        intent.putExtra("pw", loginPw);

                        startActivity(intent);
                    }
                }
            });
        } else  if(index == 3){
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_web, container, false);

            Button buttonWeb = (Button) rootView.findViewById(R.id.buttonWeb);
            buttonWeb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebView webView = (WebView) rootView.findViewById(R.id.webView);
                    EditText urlEditText = (EditText) rootView.findViewById(R.id.editText);

                    String url = urlEditText.getText().toString();

                    if(url != null && !url.equals("") && !url.equals("http://")) {
                        webView.loadUrl(url);
                    } else {
                        Toast.makeText(getActivity(), "올바른 URL을 입력하세요.", Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            rootView = (ViewGroup) inflater.inflate(R.layout.fragment_sms, container, false);
        }

        return rootView;
    }

    public void selectDB() {
        database = MainActivity.database;

        String sql = "SELECT mobile, receivedDate, contents FROM smslist order by receivedDate desc";
        Cursor cursor = database.rawQuery(sql, null);
        int count = cursor.getCount();

        for(int i=0; i<count; i++) {
            cursor.moveToNext();
            String rowMonbile = cursor.getString(0);
            String rowReceivedDate = cursor.getString(1);
            String rowContents = cursor.getString(2);

            smsAdapter.addItem(new SMSItem(rowMonbile, rowReceivedDate, rowContents, imageId));
        }

        cursor.close();

        smsAdapter.notifyDataSetChanged();
    }

}
