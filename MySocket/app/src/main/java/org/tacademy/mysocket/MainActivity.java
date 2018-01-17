package org.tacademy.mysocket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;

    Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        textView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        processCommand(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        if(intent != null) {
            String command = intent.getStringExtra("command");
            if(command != null) {
                if(command.equals("show")) {
                    String data = intent.getStringExtra("data");
                    println(data);
                }
            }
        }
    }

    public  void onButton1Clicked(View v) {
        String host = editText.getText().toString();
        String portStr = editText2.getText().toString();
        int port = 0;
        try {
            port = Integer.parseInt(portStr);
        }catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MyChatService.class);
        intent.putExtra("command", "connect");
        intent.putExtra("host", host);
        intent.putExtra("port", port);
        startService(intent);
    }

    // http 통신을 해서 내용을 코드상태로 화면에 뿌린다.
    public void onButton2Clicked(View v) {
        RequestThread thread = new RequestThread();
        thread.start();
    }

    public  void println(final String data) {
        handler.post(new Runnable(){
           public void run() {
               textView.append(data + "\n");
           }
        });

    }

    // 안드로이드에서 인터넷 통신은 무조건 쓰레드로 한다.
    class RequestThread extends Thread {
        public void run() {

            try {
                URL url = new URL("http://m.naver.com");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setConnectTimeout(1500);

                int resCode = conn.getResponseCode(); // 요청시작
                if(resCode == HttpURLConnection.HTTP_OK) {  // 정상 연결이면...
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while(true) {
                        String line = reader.readLine();
                        if(line == null) {
                            break;
                        }

                        println(line);

                        reader.close();
                        conn.disconnect();
                    }
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
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
