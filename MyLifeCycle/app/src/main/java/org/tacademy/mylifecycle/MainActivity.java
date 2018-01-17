package org.tacademy.mylifecycle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;

    int score = 80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "onCreate 호출됨", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString();
                String pw = editText2.getText().toString();

                SharedPreferences pref = getSharedPreferences("user", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("id", id);
                editor.commit();
            }
        });

        SharedPreferences pref = getSharedPreferences("user", Activity.MODE_PRIVATE);
        String savedId = pref.getString("id", "");

        editText.setText(savedId);
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop 호출됨", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "OnDestory 호출됨", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause 호출됨", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("game", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("score", score);
        editor.commit();

        Toast.makeText(this, "저장된 점수 : " + score, Toast.LENGTH_LONG).show();

        super.onPause();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "onResume 호출됨", Toast.LENGTH_LONG).show();

        SharedPreferences pref = getSharedPreferences("game", Activity.MODE_PRIVATE);
        int savedScore = pref.getInt("score", -1);

        Toast.makeText(this, "복원한 점수 : " + savedScore, Toast.LENGTH_LONG).show();

        super.onResume();
    }

    @Override
    protected void onStart() {
        Toast.makeText(this, "onStart 호출됨", Toast.LENGTH_LONG).show();
        super.onStart();
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
