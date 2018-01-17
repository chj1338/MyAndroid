package org.tacademy.mysocket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MyChatService extends Service {

    String host;
    int port;

    public MyChatService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent != null) {
            String command = intent.getStringExtra("command");
            if(command != null) {
                if(command.equals("connect")) {
                    host = intent.getStringExtra("host");
                    port = intent.getIntExtra("port", 0);

                    ChatThread thread = new ChatThread();
                    thread.start();
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    // 안드로이드에서 인터넷 통신은 무조건 쓰레드로 한다.
    class ChatThread extends Thread {
        public void run() {

            try {
                Socket socket = new Socket(host, port);
                println("서버에 연결하였습니다. : " + host + ":" + port);

                String output = " hellow!!!";
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(output);
                outstream.flush();
                println("서버로 보낸 데이터" + output);

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                Object input = instream.readObject();
                println("서버로부터 받은 데이터 : " + input);

                outstream.close();
                instream.close();
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void println(String data) {
        Log.d("ChatService", data);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("command", "show");
        intent.putExtra("data", data);
        startActivity(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
