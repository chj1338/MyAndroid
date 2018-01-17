package org.tacademy.homework1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {

    CustomerAdaptor adaptor;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        ListView listView = (ListView)findViewById(R.id.listView);

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
                CustomerItem item = (CustomerItem)adaptor.getItem(position);

                //Toast.makeText(getApplicationContext(), "선택한 걸그룹 : " + item.getName(), Toast.LENGTH_LONG).show();

                textView = (TextView)findViewById(R.id.textView);
                textView.setText("선택한 걸그룹 : " + item.getName());
            }
        });
    }

    public void onButton1Clicked(View v) {
        EditText nameEditText = (EditText)findViewById(R.id.nameEditText);
        EditText ageEditText = (EditText)findViewById(R.id.ageEditText);

        try {
            String inputedName = nameEditText.getText().toString();
            int inputedAge = Integer.parseInt(ageEditText.getText().toString());
            String inputedTel = "010-1000-1000";
            String inputedAddr = "하와이";

            adaptor.addItem(new CustomerItem(inputedName, inputedAge, inputedTel, inputedAddr));
            adaptor.notifyDataSetChanged();
        } catch(Exception e) {
            e.getStackTrace();
            textView.setText("오류가 발생했습니다.");
        }
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
            CustomerItemView view = new CustomerItemView(getApplicationContext());

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

}
