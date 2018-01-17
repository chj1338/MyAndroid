package org.tacademy.mysocket;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        textView = (TextView) findViewById(R.id.textView);

    }

    public void onButton1Clicked(View v) {

        RequestThread thread = new RequestThread();
        thread.start();

    }

    class RequestThread extends Thread {
        public void run() {

            String host = editText.getText().toString();
            String portStr = editText2.getText().toString();
            int port = 0;

            try {
                port = Integer.parseInt(portStr);

                Socket socket = new Socket(host, port);
                println("서버에 연결하였습니다. : " + host + ", " + port);

                String output = "안녕!";
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(output);
                outstream.flush();
                println("서버로 보낸 데이터 : " + output);

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                Object input = instream.readObject();
                println("서버부터 받은 데이터 : " + input);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void println(final String data) {
        handler.post(new Runnable() {
           public void run() {
               textView.append(data + "\n");
           }
        });

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
