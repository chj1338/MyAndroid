package org.tacademy.mydatabase;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
    }


    public void onButton1Clicked(View v) {
        database = openOrCreateDatabase("customer.db", Activity.MODE_PRIVATE, null);

        println("데이터베이스를 열었습니다.");
    }

    public void onButton2Clicked(View v) {
        String sql = "CREATE TABLE if not exists customer(name text, age int, mobile text)";

        database.execSQL(sql);

        println("테이블을 생성했습니다.");
    }

    public void onButton3Clicked(View v) {
        String sql = "INSERT INTO customer(name, age, mobile) values (?, ?, ?)";
        Object[] params = new Object[3];
        params[0] = "소녀시대";
        params[1] = 12;
        params[2] = "010-1000-1000";

        database.execSQL(sql, params);

        println("데이터를 insert 했습니다.");
    }

    public void onButton4Clicked(View v) {
        String sql = "SELECT name, age, mobile FROM customer";
        Cursor cursor = database.rawQuery(sql, null);
        int count = cursor.getCount();
        println("데이터를 select 했습니다. 레코드수 : " + count);

        for(int i=0; i<count; i++) {
            cursor.moveToNext();
            String name = cursor.getString(0);
            int age = cursor.getInt(1);
            String mobile = cursor.getString(2);

            println((i+1) + "번째 데이터 : " + name + ", " + age + ", " + mobile);
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
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
