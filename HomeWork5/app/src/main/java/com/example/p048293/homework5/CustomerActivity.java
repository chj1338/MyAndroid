package com.example.p048293.homework5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {

    public static final int REQ_CODE_MENU = 2001;

    ListView listView;
    CustomerAdaptor adaptor;

    TextView textView;
    EditText nameEditText;
    EditText ageEditText;
    EditText telEditText;
    EditText addrEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        textView = (TextView) findViewById(R.id.textView);

        nameEditText = (EditText)findViewById(R.id.nameEditText);
        ageEditText = (EditText)findViewById(R.id.ageEditText);
        telEditText = (EditText)findViewById(R.id.telEditText);
        addrEditText = (EditText)findViewById(R.id.addrEditText);

        listView = (ListView)findViewById(R.id.listView);

        processData();
    }

    public void processData() {
        adaptor = new CustomerAdaptor();

        adaptor.addItem(new CustomerItem("소녀시대",   22, "010-1111", "서울"));
        adaptor.addItem(new CustomerItem("걸스데이",   21, "010-2222", "부산"));
        adaptor.addItem(new CustomerItem("티아라",     27, "010-3333", "대구"));
        adaptor.addItem(new CustomerItem("애프터스쿨", 25, "010-4444", "인천"));
        adaptor.addItem(new CustomerItem("AOA",        24, "010-5555", "광주"));

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerItem item = (CustomerItem) adaptor.getItem(position);

                nameEditText.setText(item.getName());
                ageEditText.setText(item.getAge() + "");
                telEditText.setText(item.getTel());
                addrEditText.setText(item.getAddr());
            }
        });
    }

    public void onButton1Clicked(View v) {
        String name = nameEditText.getText().toString();
        int age = 0;
        String tel = telEditText.getText().toString();
        String addr = addrEditText.getText().toString();

        if(name == null) { name = ""; }
        if(ageEditText.getText().toString() == null) { age = 0; } else { age = Integer.parseInt(ageEditText.getText().toString()); }
        if(tel == null) { tel = ""; }
        if(addr == null) { addr = ""; }

        try {
            adaptor.addItem(new CustomerItem(name, age, tel, addr));
            adaptor.notifyDataSetChanged();
        } catch(Exception e) {
            e.getStackTrace();
        }

        listView.setSelection(listView.getCount()-1);
    }

    class CustomerAdaptor extends BaseAdapter {
        ArrayList<CustomerItem> items = new ArrayList<CustomerItem>();

        public void addItem(CustomerItem item) {
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
            CustomerItemView view;

            // convertView : 뷰어의 아이템 재사용(메모리 누수 방지를 위해)
            if(convertView == null) {
                view = new CustomerItemView((getApplicationContext()));
            } else {
                view = (CustomerItemView) convertView;
            }

            CustomerItem item = items.get(position);

            view.setNameText(item.getName());
            view.setAgeText(String.valueOf(item.getAge()));
            view.setTelText(item.getTel());
            view.setAddrText(item.getAddr());

            return view;
        }
    }


    public  void onInitButtonClicked(View v) {
        nameEditText.setText("");
        ageEditText.setText("");
        telEditText.setText("");
        addrEditText.setText("");
    }

    public void onDelButtonClicked(View v) {

    }

    public void onCloseButtonClicked(View v) {
        closeProcess();
    }

    public void closeProcess() {
        String menuNM = textView.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("menuNM", menuNM);
        intent.putExtra("success", true);
        setResult(REQ_CODE_MENU, intent);

        finish();
    }

    @Override
    protected void onPause() {
        closeProcess();

        super.onPause();
    }

}
