package org.tacademy.mylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SingerAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        adaptor = new SingerAdaptor(getApplicationContext());

        adaptor.addItem(new SingerItem("소녀시대",   22, "010-1000-1111", R.drawable.animal_1));
        adaptor.addItem(new SingerItem("걸스데이",   21, "010-1000-2222", R.drawable.animal_2));
        adaptor.addItem(new SingerItem("티아라",     27, "010-1000-3333", R.drawable.animal_3));
        adaptor.addItem(new SingerItem("애프터스쿨", 25, "010-1000-4444", R.drawable.animal_4));
        adaptor.addItem(new SingerItem("AOA",        24, "010-1000-5555", R.drawable.animal_5));;

        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem)adaptor.getItem(position);

                Toast.makeText(getApplicationContext(), "선택한 걸그룹 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onButton1Clicked(View v) {
        adaptor.addItem(new SingerItem("EXID", 20, "010-1000-9999", R.drawable.animal_6));
        adaptor.notifyDataSetChanged();
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
